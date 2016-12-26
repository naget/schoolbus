package com.schoolbus.model;

import lombok.Data;



/**
 * Created by t on 2016/12/16.
 */
@Data
public class Tdata<T> {
    int code;
    T message;
    public Tdata(){}
    public Tdata(int code,T data)
    {
        this.message=data;
        this.code=code;
    }
}
