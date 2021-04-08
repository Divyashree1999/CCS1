package com.cg.css.repository;

import org.springframework.stereotype.Repository;

import com.cg.css.model.CardRequest;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface CardRequestRepository extends JpaRepository<CardRequest, Integer> {

}
