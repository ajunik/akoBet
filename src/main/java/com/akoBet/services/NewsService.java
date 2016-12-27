package com.akoBet.services;

import com.akoBet.entity.News;

import java.util.List;

/**
 * Created by Arek on 27.12.2016.
 */
public interface NewsService {

    List<News> findAll();

    News findById(Long id);

    News create(News News);

    News edit(News News) throws RuntimeException;

    void deleteById(Long id);
}
