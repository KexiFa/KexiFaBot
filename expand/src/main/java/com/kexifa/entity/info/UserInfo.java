package com.kexifa.entity.info;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/10 15:51
 */
@Data
public class UserInfo implements Serializable {
    private String username;
    private String mobile;
    private String id;
    private String avatar;
}
