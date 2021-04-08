package com.cg.css.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.cg.css.model.CardRequest;
import com.cg.css.repository.CardRequestRepository;
import com.cg.css.service.CardRequestService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardRequestServiceTest {

	@Mock
	CardRequestRepository repository;

	@InjectMocks
	CardRequestService service;

	/**
	 * Test to check if card is added successfully
	 **/
	@Test
	public void addCardRequest() {
		CardRequestServiceTest test = new CardRequestServiceTest();
		CardRequest cardRequest = test.cardRequestData();

		Mockito.when(repository.save(cardRequest)).thenReturn(cardRequest);
		assertThat(service.addCardRequest(cardRequest)).isEqualTo(cardRequest);
	}

	/**
	 * Test to check viewAllRequests() when the CardRequest table is empty
	 **/
	@Test
	public void testviewAllRequests_whenTableIsEmpty() {

		List<CardRequest> cardRequestList = new ArrayList<>();

		Mockito.when(repository.findAll()).thenReturn(cardRequestList);
		assertThat(service.viewAllRequest()).isEqualTo(cardRequestList);

	}

	/**
	 * Test to check all card requests are returned
	 **/
	@Test
	public void viewAllCardRequests() {
		CardRequestServiceTest test = new CardRequestServiceTest();
		CardRequest cardRequest1 = test.cardRequestData();
		CardRequest cardRequest2 = test.cardRequestData();
		cardRequest2.setUserId(10);
		cardRequest2.setRequestId(10);

		List<CardRequest> cr = new ArrayList<>();
		cr.add(cardRequest1);
		cr.add(cardRequest2);

		Mockito.when(repository.findAll()).thenReturn(cr);
		assertThat(service.viewAllRequest()).isEqualTo(cr);
	}
	
	@Test
	public void cardRequestNotFoundException() {
		Mockito.when(repository.getOne(48)).thenReturn(null);
		assertThrows(EntityNotFoundException.class, ()->{
			service.viewSingleCardRequest(48);
		});
	}

	public CardRequest cardRequestData() {
		CardRequest cardRequest = new CardRequest();
		cardRequest.setRequestId(5);
		cardRequest.setUserId(3);
		cardRequest.setStatus("Pending");
		cardRequest.setRequestDate(Date.valueOf(LocalDate.now()));
		cardRequest.setType("Gold");

		return cardRequest;
	}
}
