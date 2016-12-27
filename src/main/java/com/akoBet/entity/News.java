package com.akoBet.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arek on 27.12.2016.
 */
public class News {

    private Long id;
    private String title;
    private String content;
    private User author;
    private String createdDate;
    private String updatedDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Transient
    String pattern = "dd/MM/yyyy";
    @Transient
    SimpleDateFormat format = new SimpleDateFormat(pattern);

    @PrePersist
    protected void onCreate() {
        createdDate = format.format(new Date());
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = format.format(new Date());
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
}
