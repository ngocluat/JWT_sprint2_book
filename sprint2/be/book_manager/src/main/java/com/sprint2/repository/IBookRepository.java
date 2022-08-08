package com.sprint2.repository;

import com.sprint2.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book, Long> {


    Page<Book> findAllByDiscountNullAndAndBookNameContainingAndBookTranslatorContaining(String name, String translator,
                                                                                                                                   Pageable pageable);
    Page<Book> findAllByDiscountNotNull(Pageable pageable);

}
