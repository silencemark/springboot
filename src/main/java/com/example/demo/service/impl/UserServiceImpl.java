package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements IUserService {

    @Resource
<<<<<<< HEAD
    public UserMapper mapper;

    @Override
    public User getUserByName(String username) {
        return mapper.getUserByName(username);
=======
    public UserMapper userMapper;

    @Override
    public User getUserByName(String username) {
        return userMapper.getUserByName(username);
>>>>>>> origin/master
    }
}
