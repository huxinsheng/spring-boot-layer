package com.learn.sbl.pojo.core;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Huxinsheng
 */
@Data
public class MenuTree {
    private Serializable id;
    private String title;
    private String icon;
    private String href;
    private String spread;
    private List<MenuTree> children;

}
