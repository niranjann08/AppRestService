package com.app.repositories;

import org.springframework.stereotype.Repository;

import com.app.entities.TextBook;

@Repository
public interface TextBookRepository extends ProductBaseRepository<TextBook> {

}
