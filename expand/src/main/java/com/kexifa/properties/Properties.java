package com.kexifa.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/30 15:57
 */
@Component
@ConfigurationProperties(prefix = "ql")
@Data
public class Properties {
    private  String qlUrl;
    private  String clientId;
    private  String clientSecret;
    //保存用
    private  String token;

}
