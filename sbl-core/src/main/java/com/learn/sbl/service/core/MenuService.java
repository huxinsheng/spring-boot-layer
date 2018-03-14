package com.learn.sbl.service.core;

import com.baomidou.mybatisplus.enums.SqlLike;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.learn.sbl.mapper.core.MenuMapper;
import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.page.Page;
import com.learn.sbl.page.PageHelper;
import com.learn.sbl.page.PageWrapper;
import com.learn.sbl.pojo.core.MenuPojo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 */
@Service
public class MenuService {

    @Autowired
    private MenuMapper menuMapper;

    public PageWrapper<MenuModel> getMenuPageData(Page page, String menuName) {
        MenuPojo menu = new MenuPojo();

        Wrapper wrapper = new EntityWrapper<>(menu);
        if (!StringUtils.isEmpty(menuName)) {
            wrapper.like("name", menuName, SqlLike.RIGHT);
        }
        wrapper.orderBy("seq", true);
        PageWrapper<MenuPojo> dbResult = PageHelper.getPageData(menuMapper, page, wrapper);
        PageWrapper<MenuModel> result = new PageWrapper<>(dbResult.getPage());
        List<MenuModel> pageList = new ArrayList<>();
        for (MenuPojo menuPojo : dbResult.getDataList()) {
            MenuModel menuModel = new MenuModel();
            BeanUtils.copyProperties(menuPojo, menuModel);
            pageList.add(menuModel);
        }
        result.setDataList(pageList);
        return result;
    }

    public List<MenuModel> getFirstMenus() {
        return null;
    }
}
