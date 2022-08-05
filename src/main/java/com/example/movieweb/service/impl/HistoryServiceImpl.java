package com.example.movieweb.service.impl;

import com.example.movieweb.dao.HistoryDao;
import com.example.movieweb.dao.impl.HistoryDaoImpl;
import com.example.movieweb.entity.History;
import com.example.movieweb.entity.User;
import com.example.movieweb.entity.Video;
import com.example.movieweb.service.HistoryService;
import com.example.movieweb.service.VideoService;

import java.sql.Timestamp;
import java.util.List;

public class HistoryServiceImpl implements HistoryService {

    private HistoryDao historyDao;
    private VideoService videoService;

    public HistoryServiceImpl() {
        this.historyDao = new HistoryDaoImpl();
        this.videoService = new VideoServiceImpl();
    }

    @Override
    public List<History> findByUser(String username) {
        return this.historyDao.findByUser(username);
    }

    @Override
    public List<History> findByUserAndIsLiked(String username) {
        return this.historyDao.findByUserAndIsLiked(username);
    }

    @Override
    public History findByUserIdAndVideoId(Integer userId, Integer videoId) {
        return this.historyDao.findByUserIdAndVideoId(userId,videoId);
    }

    @Override
    public History create(User user, Video video) {
        History history = this.findByUserIdAndVideoId(user.getId(), video.getId());
        if (history == null){
            history = new History();
            history.setUser(user);
            history.setVideo(video);
            history.setViewedDate(new Timestamp(System.currentTimeMillis()));
            history.setLiked(Boolean.FALSE);
          return this.historyDao.saveOrUpdate(history);
        }
        return history;
    }

    @Override
    public boolean updateLikeOrUnlike(User user, String videoHref) {
        Video video = this.videoService.findByHref(videoHref);
        History history = this.findByUserIdAndVideoId(user.getId(), video.getId());

        if(history.getLiked()){
            history.setLiked(Boolean.FALSE);
            history.setLikedDate(null);
        }else {
            history.setLiked(Boolean.TRUE);
            history.setLikedDate(new Timestamp(System.currentTimeMillis()));
        }

        return this.historyDao.saveOrUpdate(history) != null ? true : false;
    }
}
