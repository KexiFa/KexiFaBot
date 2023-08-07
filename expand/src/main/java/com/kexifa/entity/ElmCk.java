package com.kexifa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/29 17:33
 */
@Data
public class ElmCk  implements Serializable {
    private  String value;
    private  String _id;
    private  String created;
    private  String status;
    private  String position;
    private  String name;
    private  String remarks;
}
