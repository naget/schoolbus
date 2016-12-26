package com.schoolbus.controller;

import com.schoolbus.config.GlobleCounts;
import com.schoolbus.model.Role;
import com.schoolbus.model.Tdata;
import com.schoolbus.model.User;
import com.schoolbus.repository.RoleRepository;
import com.schoolbus.repository.UserRepository;
import com.schoolbus.service.UserService;
import com.schoolbus.utils.JsonUtils;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/create",method = RequestMethod.GET)//注册
    @ResponseBody
    public Tdata<String> UserSign(@RequestBody String json, HttpServletRequest request)
    {
        String message=null;
        Map<String,Object> map= JsonUtils.strJson2Map(json);
        String code=map.get("code").toString();
        String name=map.get("name").toString();
        String pwd=map.get("password").toString();
        HttpSession session = request.getSession();
        String servercode=session.getAttribute("validateCode").toString();
        System.out.println(servercode);
        System.out.println(map.get("code"));
        if (!(servercode.equalsIgnoreCase(code)))
        {
         message="验证码不正确";
            return new Tdata<>(GlobleCounts.WRONG_CODE,message);
        }
        else {
            if (!StringUtils.isEmpty(userRepository.findByName(name))) {
             message = name + "已经被注册";
                return new Tdata<>(GlobleCounts.SIGNED_PHONENUMBER, message);
            } else {
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

                message = name + "注册成功！来不及解释，赶快上车吧！";
                return new Tdata<>(GlobleCounts.SIGN_SUCCEED,message);
            }
        }
    }


//    @RequestMapping(value = "/create",method = RequestMethod.POST)//注册
//    @ResponseBody
//    public String UserSign(@RequestParam("username")String name,
//                           @RequestParam("password")String pwd,
//
//                           @RequestParam("code") String code,HttpServletRequest request)
//    {
//        HttpSession session = request.getSession();
//        String servercode=session.getAttribute("validateCode").toString();
//        System.out.println(servercode);
//        System.out.println(code);
//        if (!(servercode.equalsIgnoreCase(code)))return "验证码不正确";
//        else {
//            if (!StringUtils.isEmpty(userRepository.findByName(name)))
//                return name + "已经被注册";
//            else {
//                System.out.println();
//                User user = new User();
//                Role role = roleRepository.findByName("USER");
//                role.setName("USER");
//                List<Role> roles = new ArrayList<>();
//                roles.add(role);
//                user.setRoles(roles);
//
//                user.setPassword(pwd);
//                user.setName(name);
//                user.setStatus(0);
//
//                userService.save(user);
//                return name + "注册成功！来不及解释，赶快上车吧！";
//            }
//        }
//    }





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
    @RequestMapping(value = "")
    public String goindex()
    {
        return "index";
    }


    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public Tdata<String> logout(String name)
    {
        User user = userRepository.findByName(name);
        String message=null;
        if (user.getStatus()==1)
        {
            if (userRepository.setStatus0(user)==1)
            {
                message = "已退出";
                return new Tdata<>(GlobleCounts.SIGN_SUCCEED,message);
            }
            else
            {
                message = "请重试";
                return new Tdata<>(GlobleCounts.LOGOUT_FAIL,message);
            }
        }
        else
        {
            message ="您还未登录";
            return new Tdata<>(GlobleCounts.NO_LOGIN,message);
        }

    }
    @RequestMapping(value = "/search")
    public String search()
    {
        return "search";
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
//    @RequestMapping("/create")
//    public String create()
//    {
//        return "create";
//    }
}
