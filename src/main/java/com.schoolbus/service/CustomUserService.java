package com.schoolbus.service;

import com.schoolbus.model.User;
import com.schoolbus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by t on 2016/11/23.
 */
@Service

public class CustomUserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String name) {
        System.out.println("开始查询用户"+name);
        User user=userRepository.findByName(name);
        System.out.println(user);
        if (user==null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return user;
    }


}
