package com.cg.css.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.css.controller.CardRequestController;
import com.cg.css.exceptionhandling.NoCardsFoundException;
import com.cg.css.model.CreditCards;
import com.cg.css.repository.CreditCardsRepository;

@Transactional
@Service
public class CreditCardsService {

	Logger logger = LoggerFactory.getLogger(CardRequestController.class);

	@Autowired
	CreditCardsRepository creditCardsRepository;

	/**
	 * This method adds credit card details into the CreditCards table
	 * @param creditCards
	 * @return object of saved creditCards
	 **/
	public CreditCards addCreditCard(CreditCards creditCards) {
		return creditCardsRepository.save(creditCards);
	}

	/**
	 * This method fetches all credit card details from the CreditCards table
	 * @return list of Credit Cards
	 **/
	public List<CreditCards> viewAllCreditCards() {
		return creditCardsRepository.findAll();
	}

	/**
	 * This method fetches only credit card details based on the salary from the CreditCards table
	 * @param salary
	 * @return list og eligible Credit Cards
	 **/
	public List<CreditCards> viewEligibleCreditCards(double salary) throws NoCardsFoundException {
		List<CreditCards> eligibleCards = creditCardsRepository.findByMinSalaryLessThanEqual(salary);
		if(eligibleCards.isEmpty()) {
			logger.error("No Eligible cards"+eligibleCards.toString());
			throw new NoCardsFoundException("No Cards Found!"); 
		}
		return eligibleCards;
	}

}
