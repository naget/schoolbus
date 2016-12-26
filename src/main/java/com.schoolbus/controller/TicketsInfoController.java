package com.schoolbus.controller;

import com.schoolbus.config.GlobleCounts;
import com.schoolbus.model.Tdata;
import com.schoolbus.model.Tickets;
import com.schoolbus.model.TicketsPaper;
import com.schoolbus.repository.TicketsPaperRepository;
import com.schoolbus.repository.TicketsRepository;
import com.schoolbus.utils.DateUtils;
import com.schoolbus.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by t on 2016/11/26.
 */
@Controller
@RequestMapping("/info")
public class TicketsInfoController {
    @Autowired
    TicketsRepository ticketsRepository;
    @Autowired
    TicketsPaperRepository ticketsPaperRepository;

    public String getcurrentUserName(){
        return  SecurityContextHolder.getContext().getAuthentication().getName();
    }
    @RequestMapping(value = "/showInfo")
    @ResponseBody
    public Tdata<List<Tickets>> infoShow()
    {

      //  AtomicInteger synchronized
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date= DateUtils.getTimesmorning();
        Date date1=DateUtils.getTimesnight();

        List<Tickets> ticketses=ticketsRepository.findBytime(new Timestamp(date.getTime()),new Timestamp(date1.getTime()));
        return new Tdata<>(GlobleCounts.WORK_SUCCEED,ticketses);
    }
    @RequestMapping(value = "/showOneInfo",method = RequestMethod.GET)
    @ResponseBody
    public  Tdata<Tickets> showOneInfo()
    {

        //  AtomicInteger synchronized
//        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        Date date= DateUtils.getTimesmorning();
        Date date1=DateUtils.getTimesnight();
        Tickets nowTickets=new Tickets();

        List<Tickets> ticketses=ticketsRepository.findBytime(new Timestamp(date.getTime()),new Timestamp(date1.getTime()));
        for (Tickets tickets :
                ticketses) {
            if (tickets.getIsLeave() == 1){
                continue;
            }
            if (tickets.getIsLeave()!=1)
            {
                nowTickets=tickets;
                break;
            }

        }
        return new Tdata<>(GlobleCounts.WORK_SUCCEED,nowTickets);
    }

    /**
     * 具体哪天的车票

     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/showSpecialInfo",method = RequestMethod.POST)
    @ResponseBody
    public Tdata<List<Tickets>> infoShow(@RequestBody String json) throws Exception
    {
        Map<String,Object> map=JsonUtils.strJson2Map(json);
        String time=map.get("time").toString();
//        Date date=new Date(time);
//        date=DateUtils.getDaysmorning(date);
//        System.out.println("第一个时间为"+time+"第二个时间为"+new Date(time+1000*60*60*24));
        Long time2=Long.valueOf(time)+1000*60*60*24;
        List<Tickets> ticketses = ticketsRepository.findBytime(new Timestamp(Long.valueOf(time)),new Timestamp(time2));
        return new Tdata<>(GlobleCounts.WORK_SUCCEED,ticketses);

    }
    @RequestMapping("/ticketsBuy")
    @ResponseBody
    public void buyTickets()
    {


    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/sale",method = RequestMethod.POST,produces = "application/text;charset=utf-8")
    @ResponseBody
    public String sale(@RequestBody String json) throws Exception
    {
        Map<String,Object> map= JsonUtils.strJson2Map(json);
        Long id=Long.valueOf(map.get("id").toString());
        String number=map.get("number").toString();
        Tickets tickets=ticketsRepository.findById(id);
        if (tickets!=null)
        {
            if (tickets.getIsLeave()==1)
            {
                return "已发车";
            }
            else if (tickets.getSoldTickets()>=30)
            {
                return "票已售完";
            }
            else {
                Integer soldTickets = tickets.getSoldTickets();
                Integer leftTickets = tickets.getLeftTickets();
                tickets.setSoldTickets(soldTickets + Integer.valueOf(number));
                tickets.setLeftTickets(leftTickets - Integer.valueOf(number));
                if (tickets.getSoldTickets()>=tickets.getTotleTickets())
                {
                    tickets.setIsLeave(1);
                }
                TicketsPaper ticketsPaper=new TicketsPaper();
                ticketsPaper.setId(tickets.getId());
                ticketsPaper.setType(tickets.getType());
                ticketsPaper.setBuyTime(new Timestamp(System.currentTimeMillis()));
                ticketsPaper.setPrice(tickets.getPrice());
                ticketsPaper.setUserName(getcurrentUserName());
                if (ticketsRepository.save(tickets)!=null&&ticketsPaperRepository.save(ticketsPaper)!=null)
                {

                    return "买票成功";
                }
                else
                {
                    return "买票失败，请重试";
                }
            }
        }
        else {
            return "此车票不存在";
        }


    }

}
