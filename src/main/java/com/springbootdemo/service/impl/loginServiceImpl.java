package com.springbootdemo.service.impl;

import com.springbootdemo.model.User;
import com.springbootdemo.service.loginService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class loginServiceImpl  implements loginService {

    @Override
    public String login(){
        String demo ="登出";
        return demo;
    }
    @Override
    public void logout(User user){

    }
}
