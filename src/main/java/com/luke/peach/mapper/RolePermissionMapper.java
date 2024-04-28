package com.luke.peach.mapper;

import com.luke.peach.entity.RolePermissionDO;
import com.luke.peach.entity.unionkey.RolePermissionKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RolePermissionMapper extends JpaRepository<RolePermissionDO, RolePermissionKey>, JpaSpecificationExecutor<RolePermissionDO> {
}
