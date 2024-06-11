package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.peach.model.bo.RolePermsBO;
import com.luke.peach.model.entity.SysRoleMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色菜单访问层
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    /**
     * 获取角色拥有的菜单ID集合
     *
     * @param roleId 角色ID
     * @return 菜单ID集合
     */
    List<Long> listMenuIdsByRoleId(Long roleId);

    /**
     * 获取权限和拥有权限的角色列表
     */
    List<RolePermsBO> getRolePermsList(String roleCode);
}
