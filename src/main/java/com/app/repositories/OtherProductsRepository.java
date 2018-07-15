package com.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.entities.OtherProducts;

@Repository
public interface OtherProductsRepository extends
		JpaRepository<OtherProducts, Long> {

}
