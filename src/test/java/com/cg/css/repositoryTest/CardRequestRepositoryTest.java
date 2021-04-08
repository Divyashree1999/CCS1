package com.cg.css.repositoryTest;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

//import com.cg.css.CardRequestTests;
import com.cg.css.model.CardRequest;
import com.cg.css.repository.CardRequestRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CardRequestRepositoryTest {

	Logger logger = LoggerFactory.getLogger(CardRequestRepositoryTest.class);

	@Autowired
	private CardRequestRepository cardRequestRepo;

	/**
	 * Test for adding card request sucessfully
	 **/
	@Test
	public void addCardRequest() {
		CardRequest cardRequest = cardRequestData();
		CardRequest saved = cardRequestRepo.save(cardRequest);

		assertNotNull(saved);
	}

	/**
	 * Test to check if the updateRequest() throws EntityNotFound exception when
	 * passed request id which is not present in the database
	 **/
	@Test(expected = EntityNotFoundException.class)
	public void testUpdateRequest_EntityNotFoundException() {
		CardRequest cardRequest1 = cardRequestData();
		CardRequest cardRequest2 = cardRequestData();
		cardRequest2.setUserId(10);

		cardRequestRepo.save(cardRequest1);
		cardRequestRepo.save(cardRequest2);

		CardRequest updatedRequest = cardRequestRepo.getOne(3);
		logger.warn("Value of cardRequestRepo " + updatedRequest);
	}

	/**
	 * Test to check if the updateRequest() updates successfully
	 **/
	@Test
	public void updateCardRequestStatus() {
		CardRequest cardRequest = cardRequestData();

		assertFalse(cardRequest.getStatus() == "Approved");
		cardRequestRepo.save(cardRequest);

		cardRequest.setStatus("Approved");
		cardRequestRepo.saveAndFlush(cardRequest);

		assertTrue(cardRequest.getStatus() == "Approved");

	}

	/**
	 * Test to check viewAllCardRequests() returns all the records in the table
	 **/
	@Test
	public void viewAllCardRequests() {
		CardRequestRepositoryTest test = new CardRequestRepositoryTest();

		CardRequest cardRequest1 = test.cardRequestData();
		CardRequest cardRequest2 = test.cardRequestData();
		cardRequest2.setUserId(10);
		cardRequest2.setRequestId(10);

		cardRequestRepo.save(cardRequest1);
		cardRequestRepo.save(cardRequest2);

		List<CardRequest> requests = cardRequestRepo.findAll();
		logger.info("Size of cardRequestRepo " + requests);
		assertTrue(requests.size() == 2);

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
