package com.learn.sbl.page;

import com.baomidou.mybatisplus.mapper.*;

public class PageHelper {
    public PageHelper() {
    }

    public static <T> PageWrapper<T> getAllPageData(BaseMapper<T> baseMapper, Page page) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getCurrPage());
        dbPage.setSize(page.getPageSize());
        Wrapper wrapper = Condition.EMPTY;
        SqlHelper.fillWrapper(dbPage, wrapper);
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setCurrPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotalRecords(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setDataList(dbPage.getRecords());
        return paginationBean;
    }

    public static <T> PageWrapper<T> getPageData(BaseMapper<T> baseMapper, Page page, T paramter) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getCurrPage());
        dbPage.setSize(page.getPageSize());
        Wrapper wrapper = new EntityWrapper<>(paramter);
        SqlHelper.fillWrapper(dbPage, wrapper);
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setCurrPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotalRecords(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setDataList(dbPage.getRecords());
        return paginationBean;
    }

    public static <T> PageWrapper<T> getPageData(BaseMapper<T> baseMapper, Page page, Wrapper wrapper) {
        com.baomidou.mybatisplus.plugins.Page<T> dbPage = new com.baomidou.mybatisplus.plugins.Page<>();
        dbPage.setCurrent(page.getCurrPage());
        dbPage.setSize(page.getPageSize());
        SqlHelper.fillWrapper(dbPage, wrapper);
        dbPage.setRecords(baseMapper.selectPage(dbPage, wrapper));
        Page resPage = new Page();
        resPage.setCurrPage(dbPage.getCurrent());
        resPage.setPageSize(dbPage.getSize());
        resPage.setTotalRecords(dbPage.getTotal());
        resPage.setTotalPage(dbPage.getPages());
        PageWrapper<T> paginationBean = new PageWrapper<>(resPage);
        paginationBean.setDataList(dbPage.getRecords());
        return paginationBean;
    }
}
