package com.example.movieweb.service;

import com.example.movieweb.entity.User;

import javax.servlet.ServletContext;

public interface EmailService {
    boolean sendMail(ServletContext context, User recipient,String type);
}
