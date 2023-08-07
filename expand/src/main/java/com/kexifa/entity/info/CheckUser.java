package com.kexifa.entity.info;

import lombok.Data;
import org.python.antlr.ast.Str;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/27 14:56
 */
@Data
public class CheckUser implements Serializable {
    private String userId;
    private  String  phone;
    private Integer beanNum;
    private Integer beanIncrementNum;
    private Integer beanDecrementNum;
    private String leYuanbNum;
}
