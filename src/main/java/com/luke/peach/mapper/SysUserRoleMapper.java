package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.peach.model.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户角色访问层
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    /**
     * 获取角色绑定的用户数
     *
     * @param roleId 角色ID
     */
    int countUsersForRole(Long roleId);
}
