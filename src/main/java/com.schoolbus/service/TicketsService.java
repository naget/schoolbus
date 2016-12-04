package com.schoolbus.service;

import com.schoolbus.model.Tickets;
import com.schoolbus.repository.TicketsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by t on 2016/12/1.
 */
@Service
public class TicketsService {
    @Autowired
    TicketsRepository ticketsRepository;
    public String saleTickets(String start,int number) throws Exception
    {
        long start1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(start).getTime();
        Tickets ticket = ticketsRepository.findByStart(new Timestamp(start1));
        if (ticket!=null)
        {

        }
        return "OLK";
    }
}
