package com.schoolbus.repository;

import com.schoolbus.model.Tickets;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by t on 2016/11/26.
 */
public interface TicketsRepository extends JpaRepository<Tickets,Long>{
//    @Query("select t from Tickets t where t.time=?1")
//    List<Tickets> findBytime(String time);
    @Query("SELECT t from Tickets t where t.isToday=1")
    List<Tickets> findToday();
    @Query("select t from Tickets t where t.start>=?1 and t.start<=?2")
    List<Tickets> findBytime(Timestamp timestamp,Timestamp timestamp2);
    @Query("select t from  Tickets t where t.start=?1")
    Tickets findByStart(Timestamp timestamp);
}
