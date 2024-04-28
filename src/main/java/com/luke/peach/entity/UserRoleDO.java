package com.luke.peach.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * <p>
 * 用户角色关系表
 * </p>
 *
 * @author luke
 * @since 2024-04-26
 */
@TableName("sec_user_role")
public class UserRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户主键
     */
    private Long userId;

    /**
     * 角色主键
     */
    private Long roleId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    @Override
    public String toString() {
        return "UserRoleDO{" +
        ", userId=" + userId +
        ", roleId=" + roleId +
        "}";
    }
}
