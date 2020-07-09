package com.javaboy.securitydb.mapper;

import com.javaboy.securitydb.entity.Role;
import com.javaboy.securitydb.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author ChangAn
 * @date 2020/7/8 14:38
 */
@Mapper
public interface UserMapper {

    //根据用户名查询user对象
    User loadUserByUsername(String username);

    //根据用户id查询用户具有的角色
    List<Role> getUserRolesById(Integer id);
}
