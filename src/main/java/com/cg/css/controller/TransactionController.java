package com.cg.css.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.css.exceptionhandling.InvalidCardNumberException;
import com.cg.css.model.Transaction;
import com.cg.css.service.TransactionService;

@CrossOrigin("*")
@RestController
@RequestMapping("/transaction")
public class TransactionController {

	@Autowired
	private TransactionService service;

	@PostMapping("/creditTransaction")
	private Transaction makeCreditTransaction(@RequestParam("amount") double amount,
			@RequestParam("cardNumber") Long cardNumber) throws Exception {
		return service.creditTransaction(cardNumber, amount);
	}

	@PostMapping("/debitTransaction")
	private Transaction makeDebitTransaction(@RequestParam("amount") double amount,
			@RequestParam("cardNumber") Long cardNumber) throws Exception {
		return service.debitTransaction(cardNumber, amount);
	}

	@GetMapping("/creditDetails")
	public double getCreditDetailsOfPreviousMonth(@RequestParam("cardNumber") Long cardNumber) {
		return service.getCreditDetailsOfPreviousMonth(cardNumber);
	}

	@GetMapping("/debitDetails")
	public double getDebitDetailsOfPreviousMonth(@RequestParam("cardNumber") Long cardNumber) {
		return service.getDebitDetailsOfPreviousMonth(cardNumber);
	}

	@GetMapping("/rewardPoints")
	public double getRewardPointsOfPreviousMonth(@RequestParam("cardNumber") Long cardNumber) {
		return service.getRewardPointsOfPreviousMonth(cardNumber);
	}
	
	@GetMapping("/customStatement")
	public List<Transaction> getCustomStatement(@RequestParam("cardNumber") String cardNumber,
			@RequestParam("fromDate") String fromDate,
			@RequestParam("toDate") String toDate) throws NumberFormatException, InvalidCardNumberException {
		System.out.println(fromDate.getClass().getSimpleName());
		System.out.println(fromDate+" "+toDate);
		System.out.println("custom ststement");

		return service.getCustomStatement(Long.parseLong(cardNumber),Date.valueOf(fromDate), Date.valueOf(toDate));
	}
	
	@GetMapping("/monthlyStatement")
	public List<Transaction> getMonthlyStatement(@RequestParam("cardNumber") String cardNumber) throws NumberFormatException, InvalidCardNumberException {
		System.out.println(cardNumber);
		return service.getMonthlyStatement(Long.parseLong(cardNumber));
	}
}
