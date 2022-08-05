package com.example.movieweb.dao.impl;


import com.example.movieweb.dao.AbstractDao;
import com.example.movieweb.dao.HistoryDao;
import com.example.movieweb.entity.History;

import java.util.List;

public class HistoryDaoImpl extends AbstractDao<History> implements HistoryDao {

    @Override
    public List<History> findByUser(String username) {
        String jpql = "SELECT h FROM History h WHERE h.user.username = ?0 AND h.video.active = TRUE ORDER BY h.viewedDate DESC";
        return super.findMany(History.class, jpql, username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        String jpql = "SELECT h FROM History h WHERE h.user.username = ?0 AND h.liked = true AND h.video.active = TRUE ORDER BY h.viewedDate DESC";
        return super.findMany(History.class, jpql, username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        String jpql = "SELECT h FROM History h WHERE h.user.id = ?0 AND h.video.id = ?1 AND h.video.active = TRUE";
        return super.findOne(History.class,jpql,userId,videoId);
    }

}
