package com.kexifa.entity.check;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/7/10 15:34
 */
@Data
public class CheckRes implements Serializable {
    private  CheckHeader checkHeader;
    private List<CheckRecord> records;
    private  Integer peaCount;
    private Boolean success;
}
