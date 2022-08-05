package com.example.movieweb.dao.impl;

import com.example.movieweb.dao.AbstractDao;
import com.example.movieweb.dao.UserDao;
import com.example.movieweb.entity.User;

import java.util.List;

public class UserDaoImpl extends AbstractDao<User> implements UserDao {


    @Override
    public User findById(Integer id) {
        return super.finById(User.class, id);
    }

    @Override
    public User findByEmail(String email) {
        String jpql = "SELECT u FROM User u WHERE u.email = ?0 ";
        return super.findOne(User.class, jpql, email);
    }

    @Override
    public User findByUsername(String username) {
        String jpql = "SELECT u FROM User u WHERE u.username = ?0 ";
        return super.findOne(User.class, jpql, username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        String jpql = "SELECT u FROM User u WHERE u.username = ?0 AND u.password = ?1";
        return super.findOne(User.class, jpql, username, password);
    }

    @Override
    public List<User> findAll() {
        return super.findByAll(User.class, true, null, null);
    }

    @Override
    public List<User> findAll(int page, int pageSize) {
        return super.findByAll(User.class, true, page, pageSize);
    }

}
