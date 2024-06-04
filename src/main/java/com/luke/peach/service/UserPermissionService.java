package com.luke.peach.service;

import com.luke.peach.entity.PermissionDO;

import java.util.List;

/**
 * @author ：luke
 * @date ：Created in 2024/6/4 5:52 PM
 * @description：
 * @modified By：
 */

public interface UserPermissionService {

    /**
     * 根据用户id查找所属权限
     * @param userId
     * @return
     */
    List<PermissionDO> selectByUserId(Long userId);
}
