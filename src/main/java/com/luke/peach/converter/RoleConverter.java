package com.luke.peach.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luke.peach.common.model.Option;
import com.luke.peach.model.entity.SysRole;
import com.luke.peach.model.form.RoleForm;
import com.luke.peach.model.vo.RolePageVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 角色对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface RoleConverter {

    Page<RolePageVO> entity2Page(Page<SysRole> page);

    @Mappings({
            @Mapping(target = "value", source = "id"),
            @Mapping(target = "label", source = "name")
    })
    Option entity2Option(SysRole role);


    List<Option> entities2Options(List<SysRole> roles);

    SysRole form2Entity(RoleForm roleForm);

    RoleForm entity2Form(SysRole entity);
}