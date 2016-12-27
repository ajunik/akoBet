package com.akoBet.services;

import com.akoBet.entity.News;
import com.akoBet.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arek on 27.12.2016.
 */
@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Override
    public List<News> findAll() {
        return newsRepository.findAll();
    }

    @Override
    public News findById(Long id) {
        return newsRepository.findOne(id);
    }

    @Override
    public News create(News news) {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public News edit(News news) throws RuntimeException {
        return newsRepository.saveAndFlush(news);
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        newsRepository.delete(id);
    }
}
