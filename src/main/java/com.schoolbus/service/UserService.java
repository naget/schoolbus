package com.schoolbus.service;

import com.schoolbus.model.User;
import com.schoolbus.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by t on 2016/11/25.
 */
@Service
public class UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository userRepository;
    public void save(User user)
    {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setPassword(user.getPassword());

        userRepository.save(user);
    }
}
