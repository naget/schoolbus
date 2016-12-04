package com.schoolbus.config;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by t on 2016/11/23.
 */
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addViewControllers(ViewControllerRegistry registry)
    {
        registry.addViewController("index.html").setViewName("index");
        registry.addViewController("login.html").setViewName("login");
        registry.addViewController("hello.html").setViewName("hello");
        registry.addViewController("ad.html").setViewName("ad");
    }
}
