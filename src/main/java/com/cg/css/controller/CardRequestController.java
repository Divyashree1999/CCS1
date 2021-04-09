package com.cg.css.controller;

import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.css.model.CardRequest;
import com.cg.css.service.CardRequestService;

/**
 * This is the Controller Class for CardRequest model Class.It maps the incoming
 * requests with the action methods
 **/
/**
 * 
 * @author Divyashree
 *
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/cardRequest")
public class CardRequestController {

	Logger logger = LoggerFactory.getLogger(CardRequestController.class);

	@Autowired
	CardRequestService cardRequestService;

	/**
	 * This method accepts and validates the parameter and then persists it into the
	 * CardRequest table
	 **/
	@PostMapping("/addCardRequest")
	public CardRequest addCardRequest(@RequestBody CardRequest cardRequest) {
		logger.info("CardType: " + cardRequest.getCardType()+"UserId" + cardRequest.getUserId());
		return cardRequestService.addCardRequest(cardRequest);
	}

	/**
	 * This method is to fetch all Card Requests from cardRequest table
	 **/
	@GetMapping("/viewAllRequests")
	public List<CardRequest> viewAllRequests() {
		return cardRequestService.viewAllRequest();
	}

	/**
	 * This method is called by Admin to update the card request's status "
	 **/
	@PutMapping("/updateRequest")
	public CardRequest updateRequest(@RequestParam("requestId") int requestId, @RequestParam("status") String status) {
		return cardRequestService.updateRequest(requestId, status);
	}
	
	@GetMapping("/viewSingleRequest")
	public CardRequest viewSingleCardRequest(@RequestParam("id") int id) throws EntityNotFoundException{
		return cardRequestService.viewSingleCardRequest(id);
	}	
}
