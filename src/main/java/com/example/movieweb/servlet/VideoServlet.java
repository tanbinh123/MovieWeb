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

@WebServlet("/video")
public class VideoServlet extends HttpServlet {

    private VideoService videoService;
    private HistoryService historyService;

    public VideoServlet() {
        this.videoService = new VideoServiceImpl();
        this.historyService = new HistoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String actionParam = request.getParameter("action");
        String href = request.getParameter("id");

        switch (actionParam) {
            case "watch":
                doGetWatch(request, response, href);
                break;
            case "like":
                doGetLike(request, response, href);
                break;
            default:
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }


    private void doGetWatch(HttpServletRequest request, HttpServletResponse response, String href) throws ServletException, IOException {
        Video video = this.videoService.findByHref(href);

        User currentUser = (User) request.getSession().getAttribute(SessionAttr.CURRENT_USER);

        if (currentUser !=null){
            History history = this.historyService.create(currentUser,video);
            request.setAttribute("flagLikedBtn",history.getLiked());
        }

        request.setAttribute("video",video);

        request.getRequestDispatcher("/views/user/video-detail.jsp").forward(request,response);
    }

    private void doGetLike(HttpServletRequest request, HttpServletResponse response, String href) throws ServletException, IOException {
        response.setContentType("application/json");
        User currentUser = (User) request.getSession().getAttribute(SessionAttr.CURRENT_USER);
        boolean result = this.historyService.updateLikeOrUnlike(currentUser,href);
        if (result){
            response.setStatus(204); // succeed but no response data
        }else {
            response.setStatus(400);
        }
   }
}
