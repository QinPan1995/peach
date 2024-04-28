package com.luke.peach.mapper;

import com.luke.peach.entity.UserRoleDO;
import com.luke.peach.entity.unionkey.UserRoleKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRoleMapper extends JpaRepository<UserRoleDO, UserRoleKey>, JpaSpecificationExecutor<UserRoleDO> {

        }