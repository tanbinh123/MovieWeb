package com.example.movieweb.service;

import com.example.movieweb.entity.History;
import com.example.movieweb.entity.User;
import com.example.movieweb.entity.Video;

import java.util.List;

public interface HistoryService {
    List<History> findByUser(String username);

    List<History> findByUserAndIsLiked(String username);

    History findByUserIdAndVideoId(Integer userId, Integer videoId);

    History create(User user, Video video);

    boolean updateLikeOrUnlike(User user,String videoHref);
}
