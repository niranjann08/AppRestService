package com.app.repositories;

import org.springframework.stereotype.Repository;

import com.app.entities.Novel;

@Repository
public interface NovelRepository extends ProductBaseRepository<Novel> {

}
