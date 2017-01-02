package com.akoBet.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Arek on 27.12.2016.
 */
@Entity
@Table(name = "NEWS")
public class News {

    @Id
    @GeneratedValue(generator = "news_id", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "news_id", sequenceName = "news_id_seq")
    private Long id;
    @Size(min = 1, max = 30)
    private String title;
    @Size(min = 1, max = 10000)
    private String content;
    private String author;
    private String photo;
    private String createdDate;
    private String updatedDate;


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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

    @Override
    public String toString() {
        return title + " " + content;
    }
}
