package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.peach.entity.PermissionDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;

import java.util.List;
@Mapper
public interface PermissionMapper extends BaseMapper<PermissionDO>{

    /**
     * 根据角色列表查询权限列表
     *
     * @param ids 角色id列表
     * @return 权限列表
     */
    @Select(value = "SELECT DISTINCT sec_permission.* FROM sec_permission,sec_role,sec_role_permission WHERE sec_role.id = sec_role_permission.role_id AND sec_permission.id = sec_role_permission.permission_id AND sec_role.id IN (#{ids})")
    List<PermissionDO> selectByRoleIdList(@Param("ids") List<Long> ids);
}
