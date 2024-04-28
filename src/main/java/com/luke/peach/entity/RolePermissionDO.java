package com.luke.peach.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
/**
 * <p>
 * 角色权限关系表
 * </p>
 *
 * @author luke
 * @since 2024-04-26
 */
@TableName("sec_role_permission")
public class RolePermissionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色主键
     */
    private Long roleId;

    /**
     * 权限主键
     */
    private Long permissionId;

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public String toString() {
        return "RolePermissionDO{" +
        ", roleId=" + roleId +
        ", permissionId=" + permissionId +
        "}";
    }
}
