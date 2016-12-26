package com.schoolbus.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by t on 2016/11/23.
 */
@Entity
@Data
public class Tickets {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd-HH-MM")
    Timestamp start;
    int type;
    Integer totleTickets;
    Integer soldTickets;
    Integer leftTickets;
    Integer isLeave;
    double price;



}
