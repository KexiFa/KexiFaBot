package com.kexifa.entity.check;

import lombok.Data;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/10 15:34
 */
@Data
public class CheckHeader  implements Serializable {
    public  String  code;
    public  String  message;
}
