package com.luke.peach.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.luke.peach.entity.PermissionDO;
import com.luke.peach.entity.RoleDO;
import com.luke.peach.entity.RolePermissionDO;
import com.luke.peach.mapper.PermissionMapper;
import com.luke.peach.mapper.RoleMapper;
import com.luke.peach.mapper.RolePermissionMapper;
import com.luke.peach.service.UserPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：luke
 * @date ：Created in 2024/6/4 5:54 PM
 * @description：
 * @modified By：
 */

@Service
public class UserPermissionServiceImpl implements UserPermissionService {

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private PermissionMapper permissionDao;

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    /**
     * 根据用户id查找所属权限
     *
     * @param userId
     * @return
     */
    @Override
    public List<PermissionDO> selectByUserId(Long userId) {
        List<RoleDO> roles = roleDao.selectByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            throw new RuntimeException("用户未分配角色");
        }
        List<Long> roleIds = roles.stream().map(RoleDO::getId).collect(Collectors.toList());
        List<RolePermissionDO> rolePermissionDOS = rolePermissionMapper.selectByRoleIds(roleIds);
        if (CollectionUtil.isEmpty(rolePermissionDOS)) {
            throw new RuntimeException("用户未分配权限");
        }
        List<PermissionDO> permissionDOS = permissionDao.selectByIds(rolePermissionDOS.stream().map(RolePermissionDO::getPermissionId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(permissionDOS)) {
            throw new RuntimeException("用户未分配权限");
        }
        return permissionDOS;
    }
}
