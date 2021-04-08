package com.cg.css.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.css.exceptionhandling.InvalidCardNumberException;
import com.cg.css.model.CreditCardDetails;
import com.cg.css.service.CreditCardDetailsService;

@CrossOrigin("*")
@RestController
@RequestMapping("/creditCardDetails")
public class CreditCardDetailsController {

	@Autowired
	private CreditCardDetailsService creditCardDetailsService;

	/**
	 * This is used add the CreditCardDetails.
	 * 
	 * @param creditCardDetails
	 * @return returns "NOT FOUND" if CreditCardDetails is null,else it displays
	 *         status as"OK".
	 */
	@PostMapping("/addDetails")
	public ResponseEntity<CreditCardDetails> addCreditCardDetails(@RequestBody CreditCardDetails creditCardDetails) {
		CreditCardDetails addCreditCardDetails = creditCardDetailsService.addCreditCardDetails(creditCardDetails);

		if (addCreditCardDetails == null) {
			return new ResponseEntity<CreditCardDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<CreditCardDetails>(addCreditCardDetails, HttpStatus.OK);
	}
	
	@GetMapping("/getDetails")
	public CreditCardDetails getCreditCardDetails(String cardNumber) throws NumberFormatException, InvalidCardNumberException{
		return creditCardDetailsService.getCreditCardDetails(Long.parseLong(cardNumber));
	}
	
	@GetMapping("/getAllCardsByUserId")
	public List<CreditCardDetails> getAllCardsByUserId(@RequestParam("userId") String userId){
		return creditCardDetailsService.getAllCardsByUserId(Integer.parseInt(userId));
	}

}
