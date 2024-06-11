package com.luke.peach.converter;


import com.luke.peach.model.entity.SysMenu;
import com.luke.peach.model.form.MenuForm;
import com.luke.peach.model.vo.MenuVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * 菜单对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface MenuConverter {

    MenuVO entity2Vo(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    MenuForm convertToForm(SysMenu entity);

    @Mapping(target = "params", ignore = true)
    SysMenu convertToEntity(MenuForm menuForm);

}