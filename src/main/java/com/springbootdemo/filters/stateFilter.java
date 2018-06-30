package com.springbootdemo.filters;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;

public class stateFilter  extends HttpServlet {
    private ServletRequest request;
    private ServletResponse response;
    private FilterChain chain;

    public void  doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {
        this.request = request;
        this.response = response;
        this.chain = chain;

        // 输出站点名称
        System.out.println("站点网址：http://www.runoob.com");

        // 把请求传回过滤链
        chain.doFilter(request,response);
    }
}
