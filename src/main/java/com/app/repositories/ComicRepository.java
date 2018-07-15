package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Comic;

@Repository
public interface ComicRepository extends JpaRepository<Comic, Long> {

}
