package com.luanxin.controller.login;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luanxin.core.config.SpringContext;
import com.luanxin.service.LoginService;
import com.luanxin.service.impl.LoginServiceImpl;

@RequestMapping
@RestController
public class LoginController {

    @RequestMapping("/doLogin")
    public String doLogin() {
        LoginService loginService = SpringContext.getBean(LoginServiceImpl.class);
        return String.valueOf(loginService.doLogin());
    }

}
