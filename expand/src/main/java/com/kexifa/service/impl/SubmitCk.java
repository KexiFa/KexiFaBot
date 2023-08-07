package com.kexifa.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.kexifa.service.ILoginService;
import com.zhuangxv.bot.annotation.FriendMessageHandler;
import com.zhuangxv.bot.annotation.GroupMessageHandler;
import com.zhuangxv.bot.annotation.TempMessageHandler;
import com.zhuangxv.bot.core.Friend;
import com.zhuangxv.bot.core.Group;
import com.zhuangxv.bot.core.Member;
import com.zhuangxv.bot.core.TempFriend;
import com.zhuangxv.bot.message.MessageChain;
import com.zhuangxv.bot.message.support.TextMessage;
import lombok.SneakyThrows;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/3 15:39
 */
@Service
public class SubmitCk {
    @Autowired
    private ILoginService loginService;

    @SneakyThrows
    @FriendMessageHandler(regex = "USERID=\\d+")
    public  MessageChain friendSubmit(String msg, MessageChain messages, Friend friend){
        Response response = loginService.dologin(msg, String.valueOf(friend.getUserId()));
        MessageChain messageChain = new MessageChain();
        TextMessage textMessage = new TextMessage();
        if (response.code()==200){
            textMessage.setText("提交/更新成功，可以发送‘饿死了’进行查询");
        }else {
            textMessage.setText("提交失败，请拿ck去联系管理员 ");
        }
        messageChain.add(textMessage);
        return messageChain;
    }

    @GroupMessageHandler(regex = "USERID=\\d+")
    public  MessageChain groupSubmit(Group group, Member member, String  msg){
        Response response = loginService.dologin(msg, String.valueOf(member.getUserId()));
        MessageChain messageChain = new MessageChain();
        TextMessage textMessage = new TextMessage();
        if (response.code()==200){
            textMessage.setText("提交/更新成功，可以发送‘饿死了’进行查询");
        }else {
            textMessage.setText("提交失败，请拿ck去联系管理员 ");
        }
        messageChain.add(textMessage);
        return messageChain;
    }
    @TempMessageHandler(regex = "USERID=\\d+")
    public  MessageChain tempSubmit(TempFriend tempFriend, String  msg){
        Response response = loginService.dologin(msg, String.valueOf(tempFriend.getUserId()));
        MessageChain messageChain = new MessageChain();
        TextMessage textMessage = new TextMessage();
        if (response.code()==200){
            textMessage.setText("提交/更新成功，可以发送‘饿死了’进行查询");
        }else {
            textMessage.setText("提交失败，请拿ck去联系管理员 ");
        }
        messageChain.add(textMessage);
        return messageChain;
    }


}
