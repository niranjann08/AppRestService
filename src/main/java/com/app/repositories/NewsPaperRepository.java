package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.NewsPaper;

@Repository
public interface NewsPaperRepository extends JpaRepository<NewsPaper, Long> {

}
