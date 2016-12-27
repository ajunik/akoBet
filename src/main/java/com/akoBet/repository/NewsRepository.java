package com.akoBet.repository;

import com.akoBet.entity.News;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Arek on 27.12.2016.
 */
@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

    @Query("SELECT n FROM News n LEFT JOIN FETCH n.author ORDER BY n.updatedDate DESC")
    List<News> findLatest5(Pageable pageable);
}
