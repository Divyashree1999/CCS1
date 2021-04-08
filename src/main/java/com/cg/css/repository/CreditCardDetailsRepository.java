package com.cg.css.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.css.model.CreditCardDetails;

@Repository
public interface CreditCardDetailsRepository extends JpaRepository<CreditCardDetails, Integer> {

//	@Query("Select ccd.type from CreditCardDetails ccd where ccd.creditCardNumber= :cardNumber")
//	public String findCardType(@Param("cardNumber")Long cardNumber);
	
//	List<CreditCardDetails> findByCreditCardNumber(Long cardNumber);
	
	CreditCardDetails findByCreditCardNumber(Long cardNumber);

	List<CreditCardDetails> findByUserId(int id);
}
