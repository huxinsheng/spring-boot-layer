package com.learn.sbl.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author kevin
 */
public class PageWrapper<T> implements Serializable {
    private Page page;
    private List<T> dataList;

    public Page getPage() {
        return this.page;
    }

    public PageWrapper() {
    }

    public PageWrapper(Page page) {
        this.page = page;
    }

    public List<T> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<T> dataList) {
        this.dataList = dataList;
    }
}