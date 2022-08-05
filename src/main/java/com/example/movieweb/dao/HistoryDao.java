package com.example.movieweb.dao;

import com.example.movieweb.entity.History;

import java.util.List;

public interface HistoryDao {

    List<History> findByUser(String username);

    List<History> findByUserAndIsLiked(String username);

    History findByUserIdAndVideoId(Integer userId, Integer videoId);

    History saveOrUpdate(History history);

    History delete(History history);
}
