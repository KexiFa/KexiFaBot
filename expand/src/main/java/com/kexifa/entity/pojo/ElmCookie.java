package com.kexifa.entity.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/25 9:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("elm_cookie")
public class ElmCookie implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private  Integer id;
    private  String qq;
    private  String cookie;
    private  String userId;
}



