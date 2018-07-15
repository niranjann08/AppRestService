package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Novel;

@Repository
public interface NovelRepository extends JpaRepository<Novel, Long> {

}
