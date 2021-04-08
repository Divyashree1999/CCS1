package com.cg.css.servicetest;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.css.model.CreditCardDetails;
import com.cg.css.repository.CreditCardDetailsRepository;
import com.cg.css.service.CreditCardDetailsService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CreditCardDetailsServiceTest {
	@MockBean
	private CreditCardDetailsRepository creditcarddetailsJPARepository;

	@Autowired
	private CreditCardDetailsService credicarddetailsService;

	@Test
	public void testaddCreditCardDetails() {
		CreditCardDetails creditcarddetails = new CreditCardDetails();
		creditcarddetails.setType("Gold");
		creditcarddetails.setUserId(1);
		creditcarddetails.setIssueDate(Date.valueOf(LocalDate.now()));
		Mockito.when(creditcarddetailsJPARepository.save(creditcarddetails)).thenReturn(creditcarddetails);
		assertThat(credicarddetailsService.addCreditCardDetails(creditcarddetails)).isEqualTo(creditcarddetails);
	}
}
