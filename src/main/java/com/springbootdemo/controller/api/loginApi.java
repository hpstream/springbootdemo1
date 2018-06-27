package com.springbootdemo.controller.api;




import com.springbootdemo.service.loginService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(description = "登录相关的api")
@RestController
@RequestMapping("api/login")
public class loginApi {
    private final loginService service;
    public loginApi(loginService service) {
        this.service = service;
    }
    @GetMapping
    public  String login(){
        return service.login();
    }

}
