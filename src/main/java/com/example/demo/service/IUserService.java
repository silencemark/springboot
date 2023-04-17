package com.example.demo.service;

import com.example.demo.entity.User;

//@Mapper
public interface IUserService {
    User getUserByName(String username);
}
