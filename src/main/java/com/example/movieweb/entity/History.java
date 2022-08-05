package com.example.movieweb.entity;

import javax.persistence.*;
import java.sql.Timestamp;
@Entity
@Table(name = "`history`")
public class History {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "`user`", referencedColumnName = "id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "`video`", referencedColumnName = "id")
    private Video video;

    @Column(name = "`viewedDate`",nullable = false, columnDefinition = "datetime default now()")
    private Timestamp viewedDate;

    @Column(name = "liked",nullable = false,columnDefinition = "boolean default false")
    private Boolean liked;

    @Column(name = "`likedDate`")
    private Timestamp likedDate;

    public History() {
    }

    public History(Integer id, User user, Video video, Timestamp viewedDate, Boolean liked, Timestamp likedDate) {
        this.id = id;
        this.user = user;
        this.video = video;
        this.viewedDate = viewedDate;
        this.liked = liked;
        this.likedDate = likedDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Timestamp getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(Timestamp viewedDate) {
        this.viewedDate = viewedDate;
    }

    public Boolean getLiked() {
        return liked;
    }

    public void setLiked(Boolean liked) {
        this.liked = liked;
    }

    public Timestamp getLikedDate() {
        return likedDate;
    }

    public void setLikedDate(Timestamp likedDate) {
        this.likedDate = likedDate;
    }
}
