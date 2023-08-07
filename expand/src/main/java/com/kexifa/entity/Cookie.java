package com.kexifa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/30 10:55
 */
@Data
public class Cookie implements Serializable {
    private  String value;
    private  String _id;
    private  String name;
    private  String remarks;
}
