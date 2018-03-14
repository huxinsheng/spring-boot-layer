package com.learn.sbl.mapper.core;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.learn.sbl.model.core.MenuModel;
import com.learn.sbl.model.core.MenuTree;
import com.learn.sbl.pojo.core.MenuPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author kevin
 */
@Mapper
@Component
public interface MenuMapper extends BaseMapper<MenuPojo> {

    List<MenuModel> selectByCondition(Map<String,Object> params);

    List<MenuModel> selectByParent(@Param("parent") String parent);

    MenuModel selectByMenuName(@Param("id") String id, @Param("name") String name);

    MenuModel selectByUrl(@Param("url") String url);

    Integer insertMenu(MenuPojo pojo);

    int updateMenu(MenuPojo pojo);

    Integer deleteById(@Param("id") String id);

    void deleteByParent(@Param("id") String id);

    List<MenuTree> findByUserIdAndParent(@Param("userId") String id, @Param("parent") String parentId);
}
