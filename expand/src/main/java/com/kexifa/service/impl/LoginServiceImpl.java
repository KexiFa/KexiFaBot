package com.kexifa.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kexifa.constant.Constants;
import com.kexifa.entity.Auth;
import com.kexifa.entity.Cookie;
import com.kexifa.entity.ElmCk;
import com.kexifa.entity.Result;
import com.kexifa.entity.check.CheckRecord;
import com.kexifa.entity.check.CheckRes;
import com.kexifa.entity.info.CheckUser;
import com.kexifa.entity.info.UserInfo;
import com.kexifa.entity.pojo.ElmCookie;
import com.kexifa.properties.Properties;
import com.kexifa.service.IElmCookieService;
import com.kexifa.service.ILoginService;
import com.kexifa.utils.HttpUtils;
import lombok.SneakyThrows;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import okhttp3.Response;
import org.python.antlr.ast.Str;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/30 16:03
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Autowired
    private Properties properties;

    @Autowired
    private IElmCookieService iElmCookieService;

    @Override
    public Response dologin(String ck, String qq) {
        //拿到token  （Bearer 712e3d8e-7953-4228-b4a6-f8166e8a13ed）类型的
        String token = getToken();
        List<ElmCk> elmCks = getAllEnvs();
        String pattern = "USERID=\\d+";
        String userId = "";
        ElmCk elmCk = null;
        Matcher matcher = Pattern.compile(pattern).matcher(ck);
        if (matcher.find()) {
            userId = ck.substring(matcher.start() + 7, matcher.end());
        }
        Response response;
        if (elmCks.size() > 0) {
            for (int i = 0; i < elmCks.size(); i++) {
                if (elmCks.get(i).getName().equals("elmck") && elmCks.get(i).getValue().contains(userId)) {
                    elmCk = elmCks.get(i);
                }
            }
        }

        if (null == elmCk) {
            response = addEnvs(ck, qq);
        } else {
            response = updateEnvs(elmCk, ck, qq);
        }
        saveOrUpdate(ck, qq, userId);
        return response;
    }


    public boolean saveOrUpdate(String ck, String qq, String userId) {
        ElmCookie elmCookie = new ElmCookie();
        elmCookie.setCookie(ck);
        elmCookie.setQq(qq);
        elmCookie.setUserId(userId);
        //直接绑定的谁发送的ck
        ElmCookie cookie = iElmCookieService.getOne(new LambdaQueryWrapper<ElmCookie>()
                .eq(ElmCookie::getUserId, userId));
        //如果有一样的，修改qq号即可,根据elm用户id去查找
        if (null != cookie) {
            cookie.setQq(qq);
            cookie.setCookie(ck);
            return iElmCookieService.saveOrUpdate(cookie);
        } else {
            //新ck，直接保存即可，
            return iElmCookieService.saveOrUpdate(elmCookie);
        }
    }

    /*
     * @Description: 查验ck的有效性
     * @param ck
     * @return: boolean
     * @Author: kexifa
     * @Date: 2023/7/10 15:32
     */
    public static CheckUser checkCk(String ck) {
        HashMap<String, String> map = new HashMap<>();
        map.put("Cookie", ck);
        String s = HttpUtils.get(Constants.CHECK_URL, map);
        CheckRes checkRes = JSONObject.parseObject(s, CheckRes.class);
        String s1 = HttpUtils.get(Constants.INFO_URL, map);
        UserInfo userInfo = JSONObject.parseObject(s1, UserInfo.class);
        if (checkRes.getSuccess()==null){
            return  null;
        }
        //拿出所有的吃货豆记录
        List<CheckRecord> recordList = checkRes.getRecords();
        //获取当前的吃货豆数量
        int increment = 0;
        int decrement =0;
        DateTime beginOfDay = DateUtil.beginOfDay(new Date());
        DateTime endOfDay = DateUtil.endOfDay(new Date());
        for (CheckRecord record: recordList) {
            if (record.getCreatedTime().before(endOfDay)&&record.getCreatedTime().after(beginOfDay)){
                if (record.getBizType().equals("USE")||record.getBizType().equals("OVERDUE")){
                    decrement+=record.getCount();
                }else {
                    increment+=record.getCount();
                }
            }
        }

        CheckUser checkUser = new CheckUser();
        checkUser.setUserId(userInfo.getId());
        checkUser.setPhone(userInfo.getMobile());
        checkUser.setBeanNum(checkRes.getPeaCount());
        checkUser.setBeanIncrementNum(increment);
        checkUser.setBeanDecrementNum(decrement);
        return checkUser;
    }

    public static CheckUser checkLeYUanB(String ck) {
        CheckUser checkUser = checkCk(ck);
        if (checkUser==null){
            return  null;
        }else {
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("cookie", ck);
            String res = HttpUtils.post(Constants.LEYUANB_URL, paramMap);
            Object content = JSONObject.parseObject(res).get("content");
            Object num = JSONObject.parseObject(content.toString()).get("NUM");
            checkUser.setLeYuanbNum(String.valueOf(num));
            return checkUser;
        }
    }



    /*
     * @Description: 获取token
     * @param
     * @return: java.lang.String
     * @Author: kexifa
     * @Date: 2023/6/30 16:21
     */
    public String getToken() {
        String url = properties.getQlUrl() + Constants.TOKEN + "client_id=" + properties.getClientId() + "&client_secret=" + properties.getClientSecret();
        String s = HttpUtils.get(url);
        Result<Auth> result = JSONObject.parseObject(s, Result.class);
        Auth auth = JSONObject.parseObject(String.valueOf(result.getData()), Auth.class);
        String authorization = Constants.TOKEN_TYPE + auth.getToken();
        properties.setToken(authorization);
        return authorization;
    }

    /*
     * @Description: 获取所有的变量
     * @param null
     * @return:
     * @Author: kexifa
     * @Date: 2023/6/30 16:21
     */
    public List<ElmCk> getAllEnvs() {
        String url = properties.getQlUrl() + Constants.ENVS;
        Map<String, String> map = new HashMap<>();
        map.put("Authorization", properties.getToken());
        String res = HttpUtils.get(url, map);
        Result<ElmCk> result = JSONObject.parseObject(res, Result.class);
        List<ElmCk> elmCk = JSONArray.parseArray(String.valueOf(result.getData()), ElmCk.class);
        return elmCk;
    }

    /*
     * @Description: 新增变量
     * @param
     * @return: boolean
     * @Author: kexifa
     * @Date: 2023/6/30 16:26
     */
    @SneakyThrows
    public Response addEnvs(String ck, String qq) {
        Date date = new Date();
        System.out.println("为新变量,账号为" + ck);
        String url = properties.getQlUrl() + Constants.ENVS;
        ElmCk elmCk1 = new ElmCk();
        elmCk1.setValue(ck);
        elmCk1.setRemarks(date.getTime() + "/" + qq);
        elmCk1.setName("elmck");
        Response response = HttpUtils.httpPost(url, properties.getToken(), Arrays.asList(elmCk1));
        return response;
//        //等待处理
//        if (response.code()==200){
//            return "新增成功";
//        }else {
//            return "新增失败请检查ck";
//        }
    }

    /*
     * @Description: 修改变量
     * @param
     * @return: boolean
     * @Author: kexifa
     * @Date: 2023/6/30 16:26
     */
    @SneakyThrows
    public Response updateEnvs(ElmCk elm, String ck, String qq) {
        Date date = new Date();
        System.out.println("已经有账号，通过id去修改账号，账号为" + elm.toString());
        elm.setValue(ck);
        elm.setRemarks(date.getTime() + "/" + qq);
        String url = properties.getQlUrl() + Constants.ENVS;
        Cookie cookie = new Cookie();
        BeanUtils.copyProperties(elm, cookie);
        Response response = HttpUtils.httpPut(url, properties.getToken(), cookie);
        return response;
//        if (response.code()==200){
//            return "更新成功";
//        }else {
//            return response.message();
//        }
    }
}
