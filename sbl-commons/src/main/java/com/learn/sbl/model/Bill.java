package com.learn.sbl.model;

import lombok.Data;

/**
 * @author kevin
 */
@Data
public class Bill {

    private String code;

    private String prefix;

    private String dateFormat;

    private int num;
}
