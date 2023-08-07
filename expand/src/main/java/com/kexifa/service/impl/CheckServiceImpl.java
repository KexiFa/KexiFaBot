package com.kexifa.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.kexifa.entity.info.CheckUser;
import com.kexifa.entity.pojo.ElmCookie;
import com.kexifa.service.IElmCookieService;
import com.zhuangxv.bot.annotation.FriendMessageHandler;
import com.zhuangxv.bot.annotation.GroupMessageHandler;
import com.zhuangxv.bot.annotation.TempMessageHandler;
import com.zhuangxv.bot.core.Friend;
import com.zhuangxv.bot.core.Group;
import com.zhuangxv.bot.core.Member;
import com.zhuangxv.bot.core.TempFriend;
import com.zhuangxv.bot.message.MessageChain;
import com.zhuangxv.bot.message.support.AtMessage;
import com.zhuangxv.bot.message.support.TextMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.kexifa.service.impl.LoginServiceImpl.checkLeYUanB;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/27 14:31
 */
@Service
public class CheckServiceImpl {
    @Resource
    private  IElmCookieService elmCookieService;


    @FriendMessageHandler(regex = "饿死了")
    public MessageChain friendCheck(String msg, MessageChain messages, Friend friend) {
        MessageChain messageChain = new MessageChain();

        List<String> result = check(String.valueOf(friend.getUserId()));
        if (result.size()==0){
            messageChain.add(new TextMessage("没有ck，或者ck已失效，请更新ck"));
        }else {
            for (String res:result) {
                messageChain.add(new TextMessage(res));
                messageChain.add(new TextMessage("\n"));
            }
        }
        return messageChain;
    }

    @GroupMessageHandler(regex = "饿死了")
    public MessageChain groupCheck(Group group, Member member, String msg) {
        MessageChain messageChain = new MessageChain();

        List<String> result = check(String.valueOf(member.getUserId()));

        if (result.size()==0){
            messageChain.add(new TextMessage("没有ck，或者ck已失效，请更新ck"));
        }else {
            for (String res:result) {
                messageChain.add(new TextMessage(res));
                messageChain.add(new TextMessage("\n"));
            }
        }
        return messageChain;
    }

    @TempMessageHandler(regex = "饿死了")
    public MessageChain tempCheck(TempFriend tempFriend, String msg) {
        MessageChain messageChain = new MessageChain();
        List<String> result = check(String.valueOf(tempFriend.getUserId()));
        if (result.size()==0){
            messageChain.add(new AtMessage(String.valueOf(tempFriend.getUserId())));
            messageChain.add(new TextMessage("没有ck，或者ck已失效，请更新ck"));
        }else {
            for (String res:result) {
                messageChain.add(new TextMessage(res));
                messageChain.add(new TextMessage("\n"));
            }
        }
        return messageChain;
    }


    public static  String convert(CheckUser checkUser) {
        if (checkUser==null){
            return "您有一个账号已经失效，请注意三天登录一下，需要过期了再登录才会自动延期";
        }else {
            String mid = checkUser.getPhone().substring(3,7);
            String ma  = checkUser.getPhone().replace(mid,"****");
            return "用户id: " + checkUser.getUserId() + "\n" +
                    "手机号: " +  ma + "\n" +
                    "吃货豆: " + checkUser.getBeanNum() + "\n" +
                    "总乐园币: " + checkUser.getLeYuanbNum() + "\n"+
                    "今日收入吃货豆: " + checkUser.getBeanIncrementNum() + "\n" +
                    "今日使用/过期吃货豆: " + checkUser.getBeanDecrementNum() + "\n";
        }
    }


    public  List<String> check(String qq){
        List<String> result = new ArrayList<String>();
        List<ElmCookie> elmCookies = elmCookieService.list(new LambdaQueryWrapper<ElmCookie>()
                .eq(ElmCookie::getQq, qq));
        for (ElmCookie elmcookie: elmCookies
        ) {
            CheckUser checkUser = checkLeYUanB(elmcookie.getCookie());
            String s = convert(checkUser);
            result.add(s);
        }
        return result;
    }
}
