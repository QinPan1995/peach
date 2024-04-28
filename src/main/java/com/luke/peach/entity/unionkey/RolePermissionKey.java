package com.luke.peach.entity.unionkey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 角色-权限联合主键
 * </p>
 *
 * @author ：luke
 * @date ：Created in 2024/4/12 10:11 AM
 */
@Data
@Embeddable
public class RolePermissionKey implements Serializable {
    private static final long serialVersionUID = 6850974328279713855L;

    /**
     * 角色id
     */
    @Column(name = "role_id")
    private Long roleId;

    /**
     * 权限id
     */
    @Column(name = "permission_id")
    private Long permissionId;
}
