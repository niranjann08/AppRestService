package com.app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.app.entities.AppUser;
import com.app.entities.Payments;

@Repository
public interface PaymentsRepository extends JpaRepository<Payments, Long> {
	@Query("select p from Payments p where p.user=:user and p.paymentStatus = 'PENDING'")
	public List<Payments> findPendingPaymentsPerUser(@Param("user") AppUser user);
}
