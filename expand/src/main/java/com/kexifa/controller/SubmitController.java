package com.kexifa.controller;

import com.kexifa.entity.vo.CKVo;
import com.kexifa.service.ILoginService;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/3 17:14
 */
@RestController
@Slf4j
public class SubmitController {
    @Autowired
    private ILoginService loginService;

    @PostMapping("submit")
    public String submit(@RequestBody  CKVo ckVo) {
        Response response = loginService.dologin(ckVo.getCk(), ckVo.getQq());
        if (response.code() == 200) {
            return "操作成功";
        } else {
            return "更新or新增失败，请重新抓包提交";
        }
    }
}
