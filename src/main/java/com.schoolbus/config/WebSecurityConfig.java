package com.schoolbus.config;

import com.schoolbus.service.CustomUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Created by t on 2016/11/23.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Bean
    UserDetailsService customUserService()
    {
        return new CustomUserService();
    }
    @Bean public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }
    @Override
    public void configure(WebSecurity web){
        web.ignoring().antMatchers("/*.js");
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserService());
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());

        auth.eraseCredentials(false);//不删除凭据，以便记住用户
    }

//    private CsrfTokenRepository csrfTokenRepository()
//    {
//        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
//        repository.setSessionAttributeName("_csrf");
//        return repository;
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
                .antMatchers("/code","/create","/**/*.html","/*.css","/**/*.css","/**/*.jpg","/**/*.png").permitAll()
                .anyRequest().authenticated()//所有请求需要认证登录后才能访问
                .and()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/index",true)
                .failureUrl("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .and().csrf().disable();
//        http.csrf()
//                .csrfTokenRepository(csrfTokenRepository());


    }

}
