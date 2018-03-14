package com.learn.sbl.page;

import java.io.Serializable;

public class Page implements Serializable {
    private int pageSize = 10;
    private int totalPage;
    private int currPage = 1;
    private int totalRecords;

    public Page() {
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPage() {
        return this.totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrPage() {
        return this.currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getTotalRecords() {
        return this.totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Page{");
        sb.append("pageSize=").append(this.pageSize);
        sb.append(", totalPage=").append(this.totalPage);
        sb.append(", currPage=").append(this.currPage);
        sb.append(", totalRecords=").append(this.totalRecords);
        sb.append('}');
        return sb.toString();
    }
}
