package com.trustbridge.restaurant.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.trustbridge.restaurant.bean.Order;
import com.trustbridge.restaurant.bean.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
