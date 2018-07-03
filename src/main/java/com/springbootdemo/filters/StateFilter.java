package com.springbootdemo.filters;

import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StateFilter implements Filter {
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(StateFilter.class);


    /**
     * 封装，不需要过滤的list列表
     */
    protected static List<Pattern> patterns = new ArrayList<Pattern>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;

        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());

        // 当为js,css,html,可以直接访问，接口会被拦截，需要session
        if (!url.startsWith("/api")) {

            chain.doFilter(httpRequest, httpResponse);
        } else{
            HttpSession session = httpRequest.getSession(false);

            if(session != null){
                String sessionId = session.getId();
                logger.warn(sessionId);
            } else{
                httpResponse.sendRedirect("/");
                logger.warn("重定向到主页");
            }

//            HttpSession session = httpRequest.getSession();

        }
        chain.doFilter(httpRequest, httpResponse);



    }

    @Override
    public void destroy() {

    }


    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    private boolean isInclude(String url) {
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }

}