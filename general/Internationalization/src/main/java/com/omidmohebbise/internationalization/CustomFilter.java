package com.omidmohebbise.internationalization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
@Component
@Order(1)
public class CustomFilter implements Filter {

    @Autowired RequestHeader requestHeader;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        requestHeader.setAcceptLanguage(req.getHeader("Accept-Language"));
        chain.doFilter(request, response);
    }



}
