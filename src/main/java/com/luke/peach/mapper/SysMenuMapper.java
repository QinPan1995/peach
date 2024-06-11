package com.luke.peach.mapper;

/**
 * 菜单持久接口层
 *
 * @author luke
 * @since 2024/6/11
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.peach.model.bo.RouteBO;
import com.luke.peach.model.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Set;

@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<RouteBO> listRoutes();

    /**
     * 获取角色权限集合
     *
     * @param roles
     * @return
     */
    Set<String> listRolePerms(Set<String> roles);
}
