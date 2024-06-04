package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luke.peach.entity.RolePermissionDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RolePermissionMapper extends BaseMapper<RolePermissionDO> {

    default List<RolePermissionDO> selectByRoleIds(List<Long> roleIds){
        LambdaQueryWrapper<RolePermissionDO> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RolePermissionDO::getRoleId,roleIds);
        return selectList(wrapper);
    }
}
