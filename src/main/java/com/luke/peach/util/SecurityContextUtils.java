package com.luke.peach.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.luke.peach.constant.SecurityConstants;
import com.luke.peach.vo.UserPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author ：luke
 * @date ：Created in 2024/6/7 11:31 AM
 * @description：
 * @modified By：
 */


public class SecurityContextUtils {

    /**
     * 获取当前登录人信息
     *
     * @return SysUserDetails
     */
    public static UserPrincipal getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return new UserPrincipal();
        }
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserPrincipal) {
            return (UserPrincipal) authentication.getPrincipal();
        }
        return new UserPrincipal();
    }

    /**
     * 获取用户ID
     *
     * @return Long
     */
    public static Long getUserId() {
        Long userId = Convert.toLong(getUser().getId());
        return userId;
    }

    /**
     * 获取部门ID
     *
     * @return
     */
//    public static Long getDeptId() {
//        return Convert.toLong(getUser().getDeptId());
//    }

    /**
     * 获取数据权限范围
     *
     * @return DataScope
     */
//    public static Integer getDataScope() {
//        return Convert.toInt(getUser().getDataScope());
//    }


    /**
     * 获取用户角色集合
     *
     * @return 角色集合
     */
    public static Set<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            if (CollectionUtil.isNotEmpty(authorities)) {
                return authorities.stream().filter(item -> item.getAuthority().startsWith("ROLE_"))
                        .map(item -> StrUtil.removePrefix(item.getAuthority(), "ROLE_"))
                        .collect(Collectors.toSet());
            }
        }
        return Collections.EMPTY_SET;
    }

    /**
     * 是否超级管理员
     * <p>
     * 超级管理员忽视任何权限判断
     *
     * @return
     */
    public static boolean isRoot() {
        Set<String> roles = getRoles();
        return roles.contains(SecurityConstants.ROOT_ROLE_CODE);
    }
}
