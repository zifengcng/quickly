package com.lynx.quickly.myspringboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;

/**
 * UrlFilter
 */
@Component
public class UrlFilter implements Filter {

    public static final Logger LOGGER = LoggerFactory.getLogger(UrlFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println(request.getRemoteHost());
        chain.doFilter(request, response);
    }
}
