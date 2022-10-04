package com.itheima.consumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderInfo {
    private Integer id;
    private String buyerName;
    private String buyerTel;
    private String address;
    private Date createDate;
    private Date updateTime;
}
