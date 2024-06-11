package com.luke.peach.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luke.peach.model.bo.UserBO;
import com.luke.peach.model.entity.SysUser;
import com.luke.peach.model.form.UserForm;
import com.luke.peach.model.vo.UserInfoVO;
import com.luke.peach.model.vo.UserPageVO;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

    @Mappings({
            @Mapping(target = "genderLabel", expression = "java(com.luke.peach.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.luke.peach.common.enums.GenderEnum.class))")
    })
    UserPageVO bo2PageVo(UserBO bo);

    Page<UserPageVO> bo2PageVo(Page<UserBO> bo);

    UserForm entity2Form(SysUser entity);

    @InheritInverseConfiguration(name = "entity2Form")
    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(SysUser entity);

}
