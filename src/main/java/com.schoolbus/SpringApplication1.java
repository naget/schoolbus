package com.schoolbus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * Created by t on 2016/11/23.
 */
@SpringBootApplication
@ServletComponentScan
public class SpringApplication1 {

    public static void main(String[] args)  {
        SpringApplication.run(SpringApplication1.class, args);
    }
}
