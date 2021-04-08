package com.cg.css.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.css.exceptionhandling.NoCardsFoundException;
import com.cg.css.model.CreditCards;
import com.cg.css.service.CreditCardsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/creditCards")
public class CreditCardsController {

	@Autowired
	CreditCardsService creditCardsService;

	/**
	 * This method is to add the credit card details into the CreditCards table
	 * @param creditCard
	 * @return saved object creditCard
	 **/
	@PostMapping("/addCreditCard")
	public CreditCards addCreditCard(@RequestBody @Valid CreditCards creditCard) {
		return creditCardsService.addCreditCard(creditCard);
	}

	/**
	 * This method is to view all the credit cards along with it's details present
	 * in the CreditCards table
	 * @return list of all the credit cards with details
	 **/
	@GetMapping("/getAllCreditCards")
	public List<CreditCards> viewAllCreditCards() {
		System.out.println("STS");
		return creditCardsService.viewAllCreditCards();
	}

	/**
	 * This method is to display all the eligible cards from the CreditCards table
	 * @param salary
	 * @return list of eligible cards with details
	 * @throws NoCardsFoundException 
	 **/
	@GetMapping("/getEligibleCards")
	public List<CreditCards> viewEligibleCards(@RequestParam("income") String income) throws NoCardsFoundException {
		return creditCardsService.viewEligibleCreditCards(Double.parseDouble(income));

	}
}
