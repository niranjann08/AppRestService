package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.Product;

@Repository
public interface ProductBaseRepository<T extends Product> extends
		JpaRepository<T, Long> {

}
