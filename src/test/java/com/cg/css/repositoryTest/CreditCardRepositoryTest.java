package com.cg.css.repositoryTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.css.model.CreditCards;
import com.cg.css.repository.CreditCardsRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CreditCardRepositoryTest {

	@Autowired
	private CreditCardsRepository creditCardsRepository;

	/**
	 * Test to check if credit card object was added successfully into the
	 * creditCards Table
	 **/
	@Test
	public void addCreditCards() {
		CreditCards creditCards = new CreditCards();
		creditCards.setCardId(1);
		creditCards.setMinSalary(25000);
		creditCards.setPeriod(2);
		creditCards.setSwipingLimit(20000);

		CreditCards saved = creditCardsRepository.save(creditCards);
		assertNotNull(saved);
	}
	
	/**
	 * Test to check if all the eligible cards are being returned 
	 */
	@Test
	public void viewEligibleCards() {
		CreditCardRepositoryTest test = new CreditCardRepositoryTest();

		CreditCards creditCards1 = test.cardDetails();
		CreditCards creditCards2 = test.cardDetails();
		creditCards2.setMinSalary(30000);
		
		creditCardsRepository.save(creditCards1);
		creditCardsRepository.save(creditCards2);

		List<CreditCards> requests = creditCardsRepository.findByMinSalaryLessThanEqual(20000);
		assertTrue(requests.size() == 0);
	}

	/**
	 * Test to check if viewAllCards() returns all the card records present in the
	 * creditCards table
	 */
	@Test
	public void viewAllCreditCards() {
		CreditCardRepositoryTest test = new CreditCardRepositoryTest();

		CreditCards creditCards1 = test.cardDetails();
		CreditCards creditCards2 = test.cardDetails();

		creditCardsRepository.save(creditCards1);
		creditCardsRepository.save(creditCards2);

		List<CreditCards> requests = creditCardsRepository.findAll();
		assertTrue(requests.size() == 2);
	}
	
	public CreditCards cardDetails() {
		CreditCards creditCards = new CreditCards();
		creditCards.setCardId(1);
		creditCards.setCardName("Gold");
		creditCards.setMinSalary(25000);
		creditCards.setPeriod(2);
		creditCards.setSwipingLimit(20000);
		return creditCards;
	}

}
