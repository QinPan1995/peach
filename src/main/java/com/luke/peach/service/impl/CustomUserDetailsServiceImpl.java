package com.luke.peach.service.impl;

import com.luke.peach.entity.PermissionDO;
import com.luke.peach.entity.RoleDO;
import com.luke.peach.entity.UserDO;
import com.luke.peach.mapper.PermissionMapper;
import com.luke.peach.mapper.RoleMapper;
import com.luke.peach.mapper.UserMapper;
import com.luke.peach.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：luke
 * @date ：Created in 2024/4/26 6:02 PM
 * @description：自定义UserDetails查询
 * @modified By：
 */

@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserMapper userDao;

    @Autowired
    private RoleMapper roleDao;

    @Autowired
    private PermissionMapper permissionDao;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        UserDO user = userDao.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        List<RoleDO> roles = roleDao.selectByUserId(user.getId());
        List<Long> roleIds = roles.stream().map(RoleDO::getId).collect(Collectors.toList());
        List<PermissionDO> permissions = permissionDao.selectByRoleIdList(roleIds);
        return UserPrincipal.create(user, roles, permissions);
    }
}
