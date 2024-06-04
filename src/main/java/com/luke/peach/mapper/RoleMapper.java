package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.luke.peach.entity.RoleDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<RoleDO> {
    /**
     * 根据用户id 查询角色列表
     *
     * @param userId 用户id
     * @return 角色列表
     */
    @Select(value = "SELECT sec_role.* FROM sec_role,sec_user,sec_user_role WHERE sec_user.id = sec_user_role.user_id AND sec_role.id = sec_user_role.role_id AND sec_user.id = #{userId}")
    List<RoleDO> selectByUserId(@Param("userId") Long userId);

    default List<RoleDO> selectByIds(List<Long> ids){
        LambdaQueryWrapper<RoleDO> wrapper = Wrappers.lambdaQuery();
        wrapper.in(RoleDO::getId,ids);
        return selectList(wrapper);
    }
}
