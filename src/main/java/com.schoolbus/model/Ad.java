package com.schoolbus.model;

import lombok.Data;

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
public class Ad {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String content;
    Timestamp time;
    public Ad(String content)
    {
        this.content=content;
        this.time = new Timestamp(System.currentTimeMillis());
    }
    public Ad(){}
}
