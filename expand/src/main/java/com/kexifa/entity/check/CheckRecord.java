package com.kexifa.entity.check;

import lombok.Data;

import java.util.Date;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/10 15:34
 */
@Data
public class CheckRecord {
    private  String peaId;
    private  String  userId ;
    private  String  title ;
    private  String   bizType;
    private  String  bizId ;
    private  Integer  count;
    private  String  optType ;
    private  String   status;
    private  Date   beginDate;
    private Date createdTime;
    private  String   endDate;
    private  String   monthDate;
    private  String   transDetail;
    private  String   bizSubType;
    private  String   bizExt;
}
