package com.learn.sbl.model.core;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author kevin
 */
@Data
public class StatusTypeModel {
    /**
     * 主键id
     */
    private Integer id;
    /**
     * 状态类型名称
     */
    private String name;
    /**
     * 状态类型值
     */
    private String value;
    /**
     * 状态类型描述
     */
    private String desc;

    public String toJsonstring() {
        return JSON.toJSONString(this);
    }
}
