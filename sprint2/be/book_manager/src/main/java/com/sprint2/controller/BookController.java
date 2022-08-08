package com.sprint2.controller;

import com.sprint2.model.Book;
import com.sprint2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/manager-book")
public class BookController {


    @Autowired
    IBookService iBookService;

    @GetMapping("")
    public ResponseEntity<Page<Book>> getPageSupplier(@RequestParam Optional<String> searchName,
                                                      @RequestParam(defaultValue = "0") int page,
                                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                                      @RequestParam Optional<String> sort,
                                                      @RequestParam Optional<String> dir
    ) {
        Pageable pageable;
        String sortVal = sort.orElse("");
        String dirVal = dir.orElse("");
        if ("".equals(sortVal)) {
            pageable = PageRequest.of(page, pageSize);
        } else {
            if (dirVal.equals("asc")) {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).ascending());
            } else if (dirVal.equals("desc")) {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).ascending());
            }
        }

        String valueName = searchName.orElse("");
        Page<Book> books = iBookService.getAllBook(pageable);
        if (!books.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<Book> getSupplier(@PathVariable("id") Long id) {
        Book entity = iBookService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping("dont-discount")
    public ResponseEntity<Page<Book>> getBookDontDiscount(@RequestParam Optional<String> searchName,
                                                          @RequestParam Optional<String> searchTranslator,
                                                          @RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "8") Integer pageSize,
                                                          @RequestParam Optional<String> sort,
                                                          @RequestParam Optional<String> dir
    ) {
        Pageable pageable;

        String sortVal = sort.orElse("");
        String dirVal = dir.orElse("");
        if ("".equals(sortVal)) {
            pageable = PageRequest.of(page, pageSize);
        } else {
            if (dirVal.equals("asc")) {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).ascending());
            } else if (dirVal.equals("desc")) {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).descending());
            } else {
                pageable = PageRequest.of(page, pageSize, Sort.by(sortVal).ascending());
            }
        }
        String valuesearchName = searchName.orElse("");

        String valuesearchTranslator = searchTranslator.orElse("");
        Page<Book> books = iBookService.getBookDontDiscount(valuesearchName,
                valuesearchTranslator,
              pageable);
        if (!books.hasContent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(books, HttpStatus.OK);
        }
    }


}
