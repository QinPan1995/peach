package com.luke.peach.service;

import com.luke.peach.entity.PermissionDO;
import com.luke.peach.entity.RoleDO;
import com.luke.peach.entity.UserDO;
import com.luke.peach.mapper.RoleMapper;
import com.luke.peach.mapper.UserMapper;
import com.luke.peach.vo.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 自定义UserDetails查询
 * </p>
 *
 * @author luke
 * @date Created in 2024-05-10 10:29
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserPermissionService userPermissionService;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmailOrPhone) throws UsernameNotFoundException {
        UserDO user = userMapper.findByUsernameOrEmailOrPhone(usernameOrEmailOrPhone, usernameOrEmailOrPhone, usernameOrEmailOrPhone).orElseThrow(() -> new UsernameNotFoundException("未找到用户信息 : " + usernameOrEmailOrPhone));
        List<RoleDO> roles = roleMapper.selectByUserId(user.getId());
        List<PermissionDO> permissions = userPermissionService.selectByUserId(user.getId());
        return UserPrincipal.create(user, roles, permissions);
    }
}
