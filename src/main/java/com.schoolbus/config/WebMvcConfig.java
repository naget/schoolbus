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
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/index1").setViewName("index1");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/ad").setViewName("ad");
        registry.addViewController("/create").setViewName("create");
        registry.addViewController("/search").setViewName("search");
    }
}
