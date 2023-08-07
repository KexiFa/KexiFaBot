package com.kexifa.controller;

import com.kexifa.service.ILoginService;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/30 15:16
 */
@Controller
public class LoginController {


    @Autowired
    private ILoginService loginService;

    @RequestMapping("")
    public String submit() {
        return "submit";
    }

    @RequestMapping("/dologin")
    public String login(HttpServletRequest request) {
        String ck = request.getParameter("ck");
        String qq = request.getParameter("qq");
        Response response = loginService.dologin(ck, qq);
        if (response.code() == 200) {
            return "success";
        } else {
            return "fail";
        }
    }


}
