package com.luke.peach.converter;

import com.luke.peach.entity.UserDO;
import com.luke.peach.vo.UserInfoVO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * 用户对象转换器
 *
 * @author luke
 * @since 2024/6/6
 */
@Mapper(componentModel = "spring")
public interface UserConverter {

//    @Mappings({
//            @Mapping(target = "genderLabel", expression = "java(com.youlai.system.common.base.IBaseEnum.getLabelByValue(bo.getGender(), com.youlai.system.common.enums.GenderEnum.class))")
//    })
//    UserPageVO bo2PageVo(UserBO bo);
//
//    Page<UserPageVO> bo2PageVo(Page<UserBO> bo);
//
//    UserForm entity2Form(SysUser entity);
//
//    @InheritInverseConfiguration(name = "entity2Form")
//    SysUser form2Entity(UserForm entity);

    @Mappings({
            @Mapping(target = "userId", source = "id")
    })
    UserInfoVO toUserInfoVo(UserDO entity);

    //SysUser importVo2Entity(UserImportVO vo);

}
