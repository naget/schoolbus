package com.schoolbus.controller;

import com.schoolbus.model.Tickets;
import com.schoolbus.repository.TicketsRepository;
import com.schoolbus.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;
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
    @ResponseBody
    public  List<Tickets> infoShow()
    {

      //  AtomicInteger synchronized
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date= DateUtils.getTimesmorning();
        Date date1=DateUtils.getTimesnight();

        List<Tickets> ticketses=ticketsRepository.findBytime(new Timestamp(date.getTime()),new Timestamp(date1.getTime()));
        return ticketses;
    }
    @RequestMapping("/showSpecialInfo")
    @ResponseBody
    public List<Tickets> infoShow(@RequestParam("time")long time) throws Exception
    {
        Date date=new Date(time);
        date=DateUtils.getDaysmorning(date);
        List<Tickets> ticketses = ticketsRepository.findBytime(new Timestamp(date.getTime()),new Timestamp(date.getTime()+1000*60*60*24));
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
