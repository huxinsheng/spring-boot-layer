package com.learn.sbl.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author HuXinsheng
 */
@Data
public abstract class BaseModel implements Serializable{

    private Integer seqNo;

    private Date createTime;

    private Date updateTime;

    private String creator;

    private String updateBy;

    public abstract String toJsonstring();
}
