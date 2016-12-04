package com.schoolbus.controller;

import com.schoolbus.model.Tickets;
import com.schoolbus.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Created by t on 2016/11/26.
 */
@Controller
@RequestMapping("/info")
public class TicketsInfoController {
    @Autowired
    TicketsRepository ticketsRepository;
    @RequestMapping(value = "/showInfo")
    public  List<Tickets> infoShow()
    {

      //  AtomicInteger synchronized
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
//        Date date=new Date();
//        String time=dateFormat.format(date);
        List<Tickets> ticketses=ticketsRepository.findToday();
        return ticketses;
    }
    @RequestMapping("/showSpecialInfo")
    @ResponseBody
    public List<Tickets> infoShow(@RequestParam("time")String string) throws Exception
    {
        long time = new SimpleDateFormat("yyyy-MM-dd").parse(string).getTime();
        List<Tickets> ticketses = ticketsRepository.findBytime(new Timestamp(time),new Timestamp(time+1000*60*60*24));
        return ticketses;
    }
    @RequestMapping("/ticketsBuy")
    @ResponseBody
    public void buyTickets()
    {


    }
    @PreAuthorize("hasRole('ADMIN')")
    @RequestMapping("/sale")
    @ResponseBody
    public String sale()
    {
        
        return "已购票";
    }

}
