package com.learn.sbl.action.core;

import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.page.Page;
import com.learn.sbl.page.PageWrapper;
import com.learn.sbl.result.Result;
import com.learn.sbl.service.core.MenuService;
import com.learn.sbl.web.BaseController;
import com.learn.sbl.web.contants.ReqContants;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kevin
 */
@Controller
@RequestMapping("menu")
public class MenuController extends BaseController{
    private static final String VIEW_PREFIX = "core/";

    @Autowired
    MenuService menuService;

    /**
     * index
     *
     * @return
     */
    @GetMapping(ReqContants.REQ_INDEX)
    public String index() {
        return VIEW_PREFIX + "menu_index";
    }

    @ApiOperation(value = "获取菜单分页列表", notes = "菜单管理时获取菜单分页列表")
    @ResponseBody
    @PostMapping(ReqContants.REQ_QUERY_PAGE_DATA)
    public Result queryPageData(Page page, String menuName) {
        PageWrapper<MenuModel> pageData = menuService.getMenuPageData(page, menuName);
        Result result = new Result(pageData.getPage());
        result.setDataList(pageData.getDataList());
        return result;
    }

    @GetMapping(ReqContants.REQ_ADD_DATA_DIALOG)
    public String addDataDialog(Model model) {
        //查询一级菜单
        model.addAttribute(_data, menuService.getFirstMenus());
        return VIEW_PREFIX + "menu_add";
    }
}
