package com.app.repositories;

import org.springframework.stereotype.Repository;

import com.app.entities.Comic;

@Repository
public interface ComicRepository extends ProductBaseRepository<Comic> {

}
