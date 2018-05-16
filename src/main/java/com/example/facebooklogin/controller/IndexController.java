package com.example.facebooklogin.controller;

import com.example.facebooklogin.service.AuthFlowServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

    private AuthFlowServiceImpl authFlowService;

    @GetMapping(value = "/")
    public ModelAndView getMainPage() {
        return new ModelAndView("redirect:" + authFlowService.createFacebookAuthorizationURL());
    }

    @GetMapping("/test")
    public String getCode(@RequestParam("code") String code){
        authFlowService.createFacebookAccessToken(code);
        return "main";
    }


    @Autowired
    public void setAuthFlowService(AuthFlowServiceImpl authFlowService) {
        this.authFlowService = authFlowService;
    }
}
