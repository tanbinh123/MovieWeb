package com.example.movieweb.dao;

import com.example.movieweb.entity.User;

import java.util.List;

public interface UserDao {
    User findById(Integer id);

    User findByEmail(String email);

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);

    List<User> findAll();

    List<User> findAll(int page, int pageSize);

    User saveOrUpdate(User user);

    User delete(User user);
}
