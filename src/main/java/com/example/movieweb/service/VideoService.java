package com.example.movieweb.service;

import com.example.movieweb.entity.Video;

import java.util.List;

public interface VideoService {
    Video findById(Integer id);

    Video findByHref(String href);

    List<Video> findAll();

    List<Video> findAll(int page, int pageSize);

    Video create(Video video);

    Video update(Video video);

    Video delete(String href);
}
