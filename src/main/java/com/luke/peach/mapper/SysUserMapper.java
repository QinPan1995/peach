package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luke.peach.model.bo.UserBO;
import com.luke.peach.model.dto.UserAuthInfo;
import com.luke.peach.model.entity.SysUser;
import com.luke.peach.model.form.UserForm;
import com.luke.peach.model.query.UserPageQuery;
import com.luke.peach.plugin.mybatis.annotation.DataPermission;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户持久层
 *
 * @author luke
 * @since 2024/6/11
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 获取用户分页列表
     *
     * @param page
     * @param queryParams 查询参数
     * @return
     */
    @DataPermission(deptAlias = "u")
    Page<UserBO> listPagedUsers(Page<UserBO> page, UserPageQuery queryParams);

    /**
     * 获取用户表单详情
     *
     * @param userId 用户ID
     * @return
     */
    UserForm getUserFormData(Long userId);

    /**
     * 根据用户名获取认证信息
     *
     * @param username
     * @return
     */
    UserAuthInfo getUserAuthInfo(String username);

}
