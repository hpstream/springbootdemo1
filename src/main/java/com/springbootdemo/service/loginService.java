package com.springbootdemo.service;

import com.springbootdemo.model.User;

public interface loginService {

    public String  login();

    public void logout(User user);
}
