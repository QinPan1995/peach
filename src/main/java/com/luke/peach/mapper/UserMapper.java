package com.luke.peach.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luke.peach.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 根据用户名、邮箱、手机号查询用户
     *
     * @param username 用户名
     * @param email    邮箱
     * @param phone    手机号
     * @return 用户信息
     */
    default Optional<UserDO> findByUsernameOrEmailOrPhone(String username, String email, String phone){
        return Optional.ofNullable(selectOne(new com.baomidou.mybatisplus.core.conditions.query.QueryWrapper<UserDO>()
                .eq(username != null, "username", username)
                .or().eq(email != null, "email", email)
                .or().eq(phone != null, "phone", phone)));
    };

    /**
     * 根据用户名列表查询用户列表
     *
     * @param usernameList 用户名列表
     * @return 用户列表
     */
    List<UserDO> findByUsernameIn(List<String> usernameList);
}
