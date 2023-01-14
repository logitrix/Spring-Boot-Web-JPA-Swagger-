package com.trustbridge.restaurant.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.trustbridge.restaurant.bean.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	long deleteByReferenceNumber(@Param("reference_number") String referenceNumber);

	List<Order> findByReferenceNumber(@Param("reference_number") String referenceNumber);

}
