package com.example.movieweb.service;

import com.example.movieweb.entity.User;

import java.util.List;

public interface UserService {
    User findById(Integer id);

    User findByEmail(String email);

    User findByUsername(String username);

    User login(String username, String password);

    User resetPassword(String email);

    List<User> findAll();

    List<User> findAll(int page, int pageSize);

    User register(String username, String password, String email);

    User update(User user);

    User delete(String username);
}
