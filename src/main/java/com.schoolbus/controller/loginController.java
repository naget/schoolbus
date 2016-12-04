package com.schoolbus.controller;

import com.schoolbus.model.Role;
import com.schoolbus.model.User;
import com.schoolbus.repository.RoleRepository;
import com.schoolbus.repository.UserRepository;
import com.schoolbus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by t on 2016/11/23.
 */
@Controller
@RequestMapping("/")
public class loginController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RoleRepository roleRepository;
    @RequestMapping("/create")//注册
    @ResponseBody
    public String UserSign(@RequestParam("phoneNumber")String phoneNumber,
                           @RequestParam("pwd")String pwd,
                           @RequestParam("name") String name)
    {
        if (!StringUtils.isEmpty(userRepository.findByPhoneNumber(phoneNumber)))
            return phoneNumber+"已经被注册";
        else
        {
            User user=new User();
            Role role = roleRepository.findByName("USER");
            role.setName("USER");
            List<Role> roles=new ArrayList<>();
            roles.add(role);
            user.setRoles(roles);
            user.setName(name);
            user.setPwd(pwd);
            user.setPhoneNumber(phoneNumber);
            user.setStatus(0);

            userService.save(user);
            return phoneNumber+"注册成功！来不及解释，赶快上车吧！";
        }
    }





    @RequestMapping("/login")
    @ResponseBody
    public String login (@RequestParam("phoneNumber") String phoneNumber,
                        @RequestParam("pwd") String pwd) throws Exception
    {
        if(userRepository.findByPhoneNumber(phoneNumber)==null)
        {
            return "请注册信息之后再上车！";
        }
        else {
            User user = userRepository.findByPhoneNumber(phoneNumber);
            if(passwordEncoder.matches(pwd,user.getPwd()))
            {
                userRepository.setStatus1(user);
                return "登陆成功";
            }
            else {
                return "密码错误！";
            }
        }
    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(String phoneNumber)
    {
        User user = userRepository.findByPhoneNumber(phoneNumber);

        if (user.getStatus()==1)
        {
            if (userRepository.setStatus0(user)==1)
                return "已退出";
            else
                return "请重试";
        }
        else return "您还未登录";

    }
    @RequestMapping("")

    public String Text()
    {
        return "ad";
    }
}
