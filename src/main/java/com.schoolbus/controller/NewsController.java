package com.schoolbus.controller;

import com.schoolbus.config.GlobleCounts;
import com.schoolbus.model.Ad;
import com.schoolbus.model.Tdata;
import com.schoolbus.repository.AdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by t on 2016/12/2.
 */
@Controller
@RequestMapping("/news")
public class NewsController {
    @Autowired
    AdRepository adRepository;
    @RequestMapping("/fromAdmin")
//    @ResponseBody
    public String receiveNews(@RequestParam String content)
    {
        Ad ad=new Ad(content);
        adRepository.save(ad);
        return "redirect:/index";
    }
    @RequestMapping(value = "/toUser",method = RequestMethod.GET)
    @ResponseBody
    public Tdata<Ad> sendToUsers()
    {
        Ad ad=adRepository.findAll().get(0);
        return new Tdata<>(GlobleCounts.WORK_SUCCEED,ad);
    }
}
