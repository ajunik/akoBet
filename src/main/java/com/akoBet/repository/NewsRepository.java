package com.akoBet.repository;

import com.akoBet.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Arek on 27.12.2016.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
