package com.cg.css.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.css.exceptionhandling.InvalidCardNumberException;
import com.cg.css.model.CreditCardDetails;
import com.cg.css.repository.CreditCardDetailsRepository;
import com.cg.css.repository.CreditCardsRepository;

@Service
@Transactional
public class CreditCardDetailsService {
	@Autowired
	private CreditCardDetailsRepository creditCardDetailsRepo;

	@Autowired
	private CreditCardsRepository creditCardsRepo;

	/**
	 * This method is to display CreditCardDetails.
	 * 
	 * @param creditCardDetails
	 * @return returns the saved details of credit card.
	 */
	public CreditCardDetails addCreditCardDetails(CreditCardDetails creditCardDetails) {
		int period = creditCardsRepo.findPeriod(creditCardDetails.getType());
		Date expiryDate = Date.valueOf(LocalDate.now().plusYears(period));
		creditCardDetails.setExpiryDate(expiryDate);
		System.out.println(expiryDate);
		Random rnd1 = new Random();
	    int number1 = rnd1.nextInt(99999999);
	    
	     Random rnd2 = new Random();
	    int number2 = rnd2.nextInt(99999999);
	     String value = String.valueOf(number1)+String.valueOf(number2);
	     Long creditCardNumber = Long.parseLong(value);
	     creditCardDetails.setCreditCardNumber(creditCardNumber);
		return creditCardDetailsRepo.save(creditCardDetails);
	}

	public CreditCardDetails getCreditCardDetails(Long cardNumber) throws InvalidCardNumberException {
		
		CreditCardDetails cardNumberExists = creditCardDetailsRepo.findByCreditCardNumber(cardNumber);
		System.out.println(cardNumberExists);
		if(cardNumberExists == null) {
			throw new InvalidCardNumberException("Invalid Card Number");
		}
		else {
			return cardNumberExists;
		}
	}

	public List<CreditCardDetails> getAllCardsByUserId(int id) {
		
		return creditCardDetailsRepo.findByUserId(id);
	}
}
