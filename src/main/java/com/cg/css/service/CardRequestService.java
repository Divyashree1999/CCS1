package com.cg.css.service;

import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.css.controller.CardRequestController;
import com.cg.css.model.CardRequest;
import com.cg.css.repository.CardRequestRepository;

@Transactional
@Service
public class CardRequestService {

	Logger logger = LoggerFactory.getLogger(CardRequestController.class);

	@Autowired
	CardRequestRepository cardRequestRepository;

	/**
	 * This method adds the new card request details into to CardRequest table
	 * @param cardRequest
	 * @return object of saved CardRequest
	 **/
	public CardRequest addCardRequest(CardRequest cardRequest) {
		return cardRequestRepository.save(cardRequest);
	}

	/**
	 * This method fetches all the cardRequests from CardRequest table
	 * @return list of all Card Requests
	 **/
	public List<CardRequest> viewAllRequest() {
		List<CardRequest> cardRequestsList = cardRequestRepository.findAll();
		return cardRequestsList;
	}

	/**
	 * This method updates the request status from "Pending" to "Approved" or
	 * "Rejected"
	 * @param requestId, status
	 * @return object of updated Card Request 
	 **/
	public CardRequest updateRequest(int requestId, String status) throws EntityNotFoundException {
		CardRequest request = cardRequestRepository.getOne(requestId);
		request.setStatus(status);
		logger.info("updated request"+request.toString());
		return cardRequestRepository.saveAndFlush(request);
	}

	/**
	 * This method returns fetches the record using requestId 
	 * @param requestId
	 * @return
	 * @throws EntityNotFoundException
	 */
	public CardRequest viewSingleCardRequest(int requestId) throws EntityNotFoundException{
			return cardRequestRepository.getOne(requestId);
	}
}