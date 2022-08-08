package com.sprint2.controller;

import com.sprint2.model.Book;
import com.sprint2.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    IBookService iBookService;


    @GetMapping("/test/random/{id}")
    public ResponseEntity<Book> testaaa(@PathVariable("id") Long id) {
        Book entity = iBookService.findById(id);
        if (entity == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }




}
