package com.sprint2.service.impl;

import com.sprint2.model.Book;
import com.sprint2.repository.IBookRepository;
import com.sprint2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {

    IBookRepository iBookRepository;

    // Setter Injection:
    @Autowired
    public void setiBookRepository(IBookRepository iBookRepository) {
        this.iBookRepository = iBookRepository;
    }

    @Override
    public Page<Book> getAllBook(Pageable pageable) {
        return iBookRepository.findAllByDiscountNotNull(pageable);
    }

    //
    @Override
    public Page<Book> getBookDontDiscount(String name, String translator, Pageable pageable) {
        return iBookRepository.findAllByDiscountNullAndAndBookNameContainingAndBookTranslatorContaining(name, translator, pageable);
    }

    @Override
    public Book findById(Long id) {
        return iBookRepository.findById(id).orElse(null);
    }
}
