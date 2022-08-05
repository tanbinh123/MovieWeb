package com.example.movieweb.servlet;

import com.example.movieweb.constant.SessionAttr;
import com.example.movieweb.entity.History;
import com.example.movieweb.entity.User;
import com.example.movieweb.entity.Video;
import com.example.movieweb.service.HistoryService;
import com.example.movieweb.service.VideoService;
import com.example.movieweb.service.impl.HistoryServiceImpl;
import com.example.movieweb.service.impl.VideoServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet({"/home", "/favorites", "/history"})
public class HomeServlet extends HttpServlet {

    private VideoService videoService;
    private HistoryService historyService;

    public HomeServlet() {
        this.videoService = new VideoServiceImpl();
        this.historyService = new HistoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String path = request.getServletPath();

        switch (path) {
            case "/home":
                this.doGetHome(request, response);
                break;
            case "/favorites":
                this.doGetFavorites(request, response);
                break;
            case "/history":
                this.doGetHistory(request, response);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }

    private void doGetHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Video> videos = this.videoService.findAll();

        System.out.println(videos);

        request.setAttribute("videos", videos);

        request.getRequestDispatcher("/views/user/index.jsp").forward(request, response);
    }

    private void doGetFavorites(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(SessionAttr.CURRENT_USER);

        if (user == null){
            response.sendRedirect("login");
        }
        List<History> histories = this.historyService.findByUserAndIsLiked(user.getUsername());
        List<Video> videos = new ArrayList<>();

        histories.forEach(v -> videos.add(v.getVideo()));

        request.setAttribute("videos", videos);

        request.getRequestDispatcher("/views/user/favorites.jsp").forward(request, response);
    }

    private void doGetHistory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute(SessionAttr.CURRENT_USER);

        if (user == null){
            response.sendRedirect("login");
        }
        List<History> histories = this.historyService.findByUser(user.getUsername());
        List<Video> videos = new ArrayList<>();

        histories.forEach(v -> videos.add(v.getVideo()));

        request.setAttribute("videos", videos);

        request.getRequestDispatcher("/views/user/history.jsp").forward(request, response);
    }
}
