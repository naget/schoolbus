package com.schoolbus.controller;

import com.schoolbus.model.Role;
import com.schoolbus.model.User;
import com.schoolbus.repository.RoleRepository;
import com.schoolbus.repository.UserRepository;
import com.schoolbus.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
    @RequestMapping(value = "/create",method = RequestMethod.POST)//注册
    @ResponseBody
    public String UserSign(@RequestParam("username")String name,
                           @RequestParam("password")String pwd,

                            @RequestParam("code") String code,HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        String servercode=session.getAttribute("validateCode").toString();
        System.out.println(servercode);
        System.out.println(code);
        if (!(servercode.equals(code)))return "验证码不正确";
        else {
            if (!StringUtils.isEmpty(userRepository.findByName(name)))
                return name + "已经被注册";
            else {
                System.out.println();
                User user = new User();
                Role role = roleRepository.findByName("USER");
                role.setName("USER");
                List<Role> roles = new ArrayList<>();
                roles.add(role);
                user.setRoles(roles);

                user.setPassword(pwd);
                user.setName(name);
                user.setStatus(0);

                userService.save(user);
                return name + "注册成功！来不及解释，赶快上车吧！";
            }
        }
    }





//    @RequestMapping(value = "/login" )
//    @ResponseBody
//    public String login (@RequestParam(value = "name",required = false
//    ) String username,
//                        @RequestParam(value = "password",required = false) String ) throws Exception
//    {
////        if(userRepository.findByName(username)==null)
////        {
////            System.out.println("username的值是"+username);
////            return "请注册信息之后再上车！";
////        }
////        else {
//
//           User user = userRepository.findByName(username);
//            if(passwordEncoder.matches(pwd,user.getPwd()))
//            {
//                userRepository.setStatus1(user);
//                return "登陆成功";
//            }
//            else {
//                return "密码错误！";
//            }
////        }
//    }
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public String logout(String name)
    {
        User user = userRepository.findByName(name);

        if (user.getStatus()==1)
        {
            if (userRepository.setStatus0(user)==1)
                return "已退出";
            else
                return "请重试";
        }
        else return "您还未登录";

    }
    @RequestMapping(value = "/login")

    public String login()
    {
        return "login";
    }

    @RequestMapping("/index")

    public String index()
    {
        return "index";
    }
    @RequestMapping("/hello")

    public String error()
    {
        return "hello";
    }
    @RequestMapping("/create")
    public String create()
    {
        return "create";
    }
}
