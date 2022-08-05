package com.example.movieweb.service.impl;

import com.example.movieweb.dao.UserDao;
import com.example.movieweb.dao.impl.UserDaoImpl;
import com.example.movieweb.entity.User;
import com.example.movieweb.entity.Video;
import com.example.movieweb.service.UserService;
import net.bytebuddy.implementation.bytecode.Throw;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao;

    public UserServiceImpl() {
        this.dao = new UserDaoImpl();
    }

    @Override
    public User findById(Integer id) {
        return this.dao.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return this.dao.findByEmail(email);
    }

    @Override
    public User findByUsername(String username) {
        return this.dao.findByUsername(username);
    }

    @Override
    public User login(String username, String password) {
        return this.dao.findByUsernameAndPassword(username,password);
    }

    @Override
    public User resetPassword(String email) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<User> findAll(int page, int pageSize) {
        return this.dao.findAll(page,pageSize);
    }

    @Override
    public User register(String username, String password, String email) {
        User user = new User();

        if (this.findByUsername(username) != null && this.findByEmail(email) != null){
            System.out.println("Thông tin user đã tồn tại");
            return null ;
        }
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setAdmin(Boolean.FALSE);
        user.setActive(Boolean.TRUE);

        return this.dao.saveOrUpdate(user);
    }

    @Override
    public User update(User user) {
        if (this.dao.findById(user.getId())==null){
            return null;
        }
        return this.dao.saveOrUpdate(user);
    }

    @Override
    public User delete(String username) {
        User user = this.findByUsername(username);
        user.setActive(Boolean.FALSE);
        return this.update(user);
    }

}
