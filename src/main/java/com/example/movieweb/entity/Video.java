package com.example.movieweb.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "`video`")
public class Video {
    @Id
    @Column(name = "`id`")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "`title`", nullable = false)
    private String title;

    @Column(name = "`href`", nullable = false, unique = true)
    private String href;
    @Column(name = "`poster`")
    private String poster;

    @Column(name = "`views`", nullable = false, columnDefinition = "int default 0")
    private Integer views;

    @Column(name = "`shares`", nullable = false, columnDefinition = "int default 0")
    private Integer shares;

    @Column(name = "`description`")
    private String description;

    @Column(name = "`createDate`", nullable = false, columnDefinition = "datetime default now()")
    private Timestamp createDate;

    @Column(name = "`active`", nullable = false, columnDefinition = "boolean default true")
    private Boolean active;

    @OneToMany(mappedBy = "video")
    List<History> histories;

    public Video() {
    }

    public Video(Integer id, String title, String href, String poster, Integer views, Integer shares, String description, Timestamp createDate, Boolean active, List<History> histories) {
        this.id = id;
        this.title = title;
        this.href = href;
        this.poster = poster;
        this.views = views;
        this.shares = shares;
        this.description = description;
        this.createDate = createDate;
        this.active = active;
        this.histories = histories;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<History> getHistories() {
        return histories;
    }

    public void setHistories(List<History> histories) {
        this.histories = histories;
    }
}
