package com.joshua.application.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joshua.application.model.CartItem;

@Repository
public interface CartRepository extends JpaRepository<CartItem, Long> {

}
