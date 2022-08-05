package com.example.movieweb.servlet;

import com.example.movieweb.constant.Email;
import com.example.movieweb.constant.SessionAttr;
import com.example.movieweb.entity.User;
import com.example.movieweb.service.EmailService;
import com.example.movieweb.service.UserService;
import com.example.movieweb.service.impl.EmailServiceImpl;
import com.example.movieweb.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet({"/login", "/logout", "/register","/forgot-password"})
public class UserServlet extends HttpServlet {

    private UserService userService;
    private EmailService emailService;

    public UserServlet() {
        this.userService = new UserServiceImpl();
        this.emailService = new EmailServiceImpl();
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getServletPath();
        switch (path) {
            case "/login":
                this.login(request, response);
                break;
            case "/logout":
                this.logout(request, response);
                break;
            case "/register":
                this.register(request, response);
                break;
            default:
                break;
        }
    }


    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();

        if (method.equalsIgnoreCase("GET")) {
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
        }
        if (method.equalsIgnoreCase("POST")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");

            User user = userService.login(username, password);

            System.out.println(user);

            if (user != null) {
                request.getSession().setAttribute(SessionAttr.CURRENT_USER, user);
                response.sendRedirect("home");
            } else {
                response.sendRedirect("login");
            }
        }
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute(SessionAttr.CURRENT_USER);
        response.sendRedirect("home");
    }

    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        if (method.equalsIgnoreCase("GET")) {
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
        }
        if (method.equalsIgnoreCase("POST")) {
            String username = request.getParameter("username");
            String email = request.getParameter("email");
            String password = request.getParameter("password");
            String cfmPass = request.getParameter("cfmPass");

            User user = this.userService.register(username, password, email);
            if (user != null) {
                this.emailService.sendMail(this.getServletContext(),user, Email.EMAIL_WELCOME);
                request.getSession().setAttribute(SessionAttr.CURRENT_USER, user);
                response.sendRedirect("home");
            } else {
                response.sendRedirect("register");
            }
        }
    }

}
