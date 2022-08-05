package com.example.movieweb.dao;


import com.example.movieweb.entity.Video;

import java.util.List;

public interface VideoDao {

    Video findById(Integer id);

    Video findByHref(String href);

    List<Video> findAll();

    List<Video> findAll(int page, int pageSize);

    Video saveOrUpdate(Video video);

    Video delete(Video Video);
}
