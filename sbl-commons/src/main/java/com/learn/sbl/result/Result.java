package com.learn.sbl.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.learn.sbl.page.Page;

import java.io.Serializable;
import java.util.List;

public class Result implements Serializable {

    private static final String SUCCESS_CODE = "000000";
    private static final String SUCCESS_MSG = "成功";
    private static final String ERROR_CODE = "E00000";
    private String code = "000000";
    private String message = "成功";
    private List<?> dataList;
    private Object dataObject;
    private int pageSize = 10;
    private int totalPage;
    private int currPage = 1;
    private int totalRecords;

    public Result() {
    }

    public Result(Page page) {
        this.pageSize = page.getPageSize();
        this.totalPage = page.getTotalPage();
        this.currPage = page.getPageSize();
        this.totalRecords = page.getTotalRecords();
    }

    public void isError(String message) {
        this.setCode("E00000");
        this.setMessage(message);
    }

    public void isSuccess(String message) {
        this.setCode("00000");
        this.setMessage("成功");
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<?> getDataList() {
        return this.dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public Object getDataObject() {
        return this.dataObject;
    }

    public void setDataObject(Object dataObject) {
        this.dataObject = dataObject;
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
}
