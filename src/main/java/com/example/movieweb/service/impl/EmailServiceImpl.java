package com.example.movieweb.service.impl;

import com.example.movieweb.constant.Email;
import com.example.movieweb.entity.User;
import com.example.movieweb.service.EmailService;
import com.example.movieweb.utility.SendMailUtil;

import javax.servlet.ServletContext;

public class EmailServiceImpl implements EmailService {

    private static final String EMAIL_WELCOME_SUBJECT = "Welcome to Online Entertainment";
    private static final String EMAIL_FORGOT_PASSWORD = "Online Entertainment - New password";

    @Override
    public boolean sendMail(ServletContext context, User recipient, String type) {
        String host = context.getInitParameter("host");
        String port = context.getInitParameter("port");
        String user = context.getInitParameter("user");
        String pass = context.getInitParameter("pass");
        try {
            StringBuffer content = new StringBuffer();
            StringBuffer subject = new StringBuffer();
            switch (type) {
                case Email.EMAIL_WELCOME:
                    subject.append(EMAIL_WELCOME_SUBJECT);
                    content.append("Dear ").append(recipient.getUsername()).append(", hope you have a good time!");
                    break;
                case Email.EMAIL_FORGOT:
                    subject.append(EMAIL_FORGOT_PASSWORD);
                    content.append("Dear ").append(recipient.getUsername()).append(", your new password here: ").append(recipient.getPassword());
                    break;
                default:
                    subject.append("Online Entertainment");
                    content.append("Maybe this email is wrong, don't care about it");
                    break;
            }
            SendMailUtil.sendEmail(host, port, user, pass, recipient.getEmail(), subject.toString(), context.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
