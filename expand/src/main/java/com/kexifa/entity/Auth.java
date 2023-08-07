package com.kexifa.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/29 17:09
 */
@Data
public class Auth implements Serializable {
    private  String token;
    private  String token_type;
    private  String expiration;
}
