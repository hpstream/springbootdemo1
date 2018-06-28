package com.springbootdemo.service;

import com.springbootdemo.model.User;

public interface loginService {

    String login();

    void logout(User user);
}
