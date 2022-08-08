package com.sprint2.service;

import com.sprint2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IBookService {

    Page<Book> getAllBook(Pageable pageable);

    Page<Book> getBookDontDiscount(String name, String translator, Pageable pageable);

    Book findById(Long id);
}
