package com.example.movieweb.service.impl;

import com.example.movieweb.dao.VideoDao;
import com.example.movieweb.dao.impl.VideoDaoImpl;
import com.example.movieweb.entity.Video;
import com.example.movieweb.service.VideoService;

import java.util.List;

public class VideoServiceImpl implements VideoService {

    private VideoDao dao;

    public VideoServiceImpl() {
        this.dao = new VideoDaoImpl();
    }

    @Override
    public Video findById(Integer id) {
        return this.dao.findById(id);
    }

    @Override
    public Video findByHref(String href) {
        return this.dao.findByHref(href);
    }

    @Override
    public List<Video> findAll() {
        return this.dao.findAll();
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        return this.dao.findAll(page,pageSize);
    }

    @Override
    public Video create(Video video) {
        video.setId(null);
        video.setActive(Boolean.TRUE);
        video.setViews(0);
        video.setShares(0);
        return this.dao.saveOrUpdate(video);
    }

    @Override
    public Video update(Video video) {
        if (this.dao.findById(video.getId())==null){
            return null;
        }
        return this.dao.saveOrUpdate(video);
    }


    @Override
    public Video delete(String href) {
        Video video = this.findByHref(href);
        video.setActive(Boolean.FALSE);
        return this.update(video);
    }
}
