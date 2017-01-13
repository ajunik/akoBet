package com.akoBet.services;

import com.akoBet.entity.Bookmaker;
import com.akoBet.repository.BookmakerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Arek on 27.12.2016.
 */
@Service
public class BookmakerServiceImpl implements BookmakerService {

    @Autowired
    BookmakerRepository bookmakerRepository;

    @Override
    public List<Bookmaker> findAll() {
        return bookmakerRepository.findAll();
    }

    @Override
    public Bookmaker save(Bookmaker bookmaker) {
        return bookmakerRepository.saveAndFlush(bookmaker);
    }

    @Override
    public Bookmaker findById(Long id) {
        return bookmakerRepository.findOne(id);
    }

    @Override
    public void deleteById(Long id) throws RuntimeException {
        bookmakerRepository.delete(id);
    }
}
