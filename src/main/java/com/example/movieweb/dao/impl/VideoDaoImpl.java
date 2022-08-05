package com.example.movieweb.dao.impl;

import com.example.movieweb.dao.AbstractDao;
import com.example.movieweb.dao.VideoDao;
import com.example.movieweb.entity.Video;

import java.util.List;

public class VideoDaoImpl extends AbstractDao<Video> implements VideoDao {
    
    @Override
    public Video findById(Integer id) {
        return super.finById(Video.class,id);
    }

    @Override
    public Video findByHref(String href) {
        String jpql = "SELECT v FROM Video v WHERE v.href = ?0 ";
        return super.findOne(Video.class, jpql, href);
    }

    @Override
    public List<Video> findAll() {
        return super.findByAll(Video.class, true, null, null);
    }

    @Override
    public List<Video> findAll(int page, int pageSize) {
        return super.findByAll(Video.class, true, page, pageSize);
    }

}
