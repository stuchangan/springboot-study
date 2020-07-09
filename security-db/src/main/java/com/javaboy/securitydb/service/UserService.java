package com.javaboy.securitydb.service;

import com.javaboy.securitydb.entity.User;
import com.javaboy.securitydb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author ChangAn
 * @date 2020/7/8 14:38
 */
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserMapper userMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("用户不存在！");
        }
        user.setRoles(userMapper.getUserRolesById(user.getId()));
        return user;
    }
}
