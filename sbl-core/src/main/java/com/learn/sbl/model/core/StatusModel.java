package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author kevin
 */
@Data
public class StatusModel {
    /**
     * 状态id
     */
    private Integer id;
    /**
     * 状态名称
     */
    private String name;
    /**
     * 状态值
     */
    private String value;
    /**
     * 状态类型
     */
    private Integer type;
    /**
     * 数据状态
     */
    private boolean status;
    /**
     * 状态描述
     */
    private String desc;

    /**
     * 状态类型实体
     */
    private StatusTypeModel statusType;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
