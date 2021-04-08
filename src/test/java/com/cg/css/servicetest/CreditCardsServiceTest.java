package com.cg.css.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.css.exceptionhandling.NoCardsFoundException;
import com.cg.css.model.CreditCards;
import com.cg.css.repository.CreditCardsRepository;
import com.cg.css.service.CreditCardsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardsServiceTest {

	Logger logger = LoggerFactory.getLogger(CreditCardsServiceTest.class);

	@Mock
	CreditCardsRepository repository;

	@InjectMocks
	CreditCardsService service;

	@Test
	public void addCreditCards() {
		CreditCards creditCards = new CreditCards();
		creditCards.setCardId(1);
		creditCards.setCardName("Gold");
		creditCards.setMinSalary(25000);
		creditCards.setPeriod(2);
		creditCards.setSwipingLimit(20000);

		Mockito.when(repository.save(creditCards)).thenReturn(creditCards);
		assertThat(service.addCreditCard(creditCards)).isEqualTo(creditCards);
	}

	/**
	 * Tests if exception occurs while no eligible cards are found
	 **/
	@Test(expected = NoCardsFoundException.class)
	public void testviewEligibleCards_whenNoEligibleCardsFound() throws NoCardsFoundException {
		List<CreditCards> creditCards = new ArrayList<>();
		creditCards.removeAll(creditCards);
		logger.info("viewEligibleCards() creditCards :" + creditCards.toString());

		Mockito.when(repository.findByMinSalaryLessThanEqual(20000)).thenReturn(creditCards);
		assertThat(service.viewEligibleCreditCards(20000)).isEqualTo(creditCards);
	}

	@Test
	public void viewAllCreditCards() {

		CreditCards creditCards1 = new CreditCards();
		creditCards1.setCardId(1);
		creditCards1.setCardName("Gold");
		creditCards1.setMinSalary(25000);
		creditCards1.setPeriod(2);
		creditCards1.setSwipingLimit(20000);

		CreditCards creditCards2 = new CreditCards();
		creditCards2.setCardId(2);
		creditCards2.setCardName("Platinum");
		creditCards2.setMinSalary(55000);
		creditCards2.setPeriod(2);
		creditCards2.setSwipingLimit(50000);

		List<CreditCards> crList = new ArrayList<>();
		crList.add(creditCards1);
		crList.add(creditCards2);

		Mockito.when(repository.findAll()).thenReturn(crList);
		assertThat(service.viewAllCreditCards()).isEqualTo(crList);
	}

	@Test
	public void viewEligibleCreditCards() throws NoCardsFoundException {

		CreditCards creditCards1 = new CreditCards();
		creditCards1.setCardId(1);
		creditCards1.setCardName("Gold");
		creditCards1.setMinSalary(25000);
		creditCards1.setPeriod(2);
		creditCards1.setSwipingLimit(20000);

		CreditCards creditCards2 = new CreditCards();
		creditCards2.setCardId(2);
		creditCards2.setCardName("Platinum");
		creditCards2.setMinSalary(55000);
		creditCards2.setPeriod(2);
		creditCards2.setSwipingLimit(50000);

		List<CreditCards> crList = new ArrayList<>();
		crList.add(creditCards1);
		crList.add(creditCards2);

		Mockito.when(repository.findByMinSalaryLessThanEqual(55000)).thenReturn(crList);
		assertThat(service.viewEligibleCreditCards(55000)).isEqualTo(crList);
	}
}
