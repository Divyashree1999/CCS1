package com.cg.css.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.css.exceptionhandling.DebitAmountMoreThanBalanceException;
import com.cg.css.exceptionhandling.InvalidCardNumberException;
import com.cg.css.exceptionhandling.ReachedSwipingLimitException;
import com.cg.css.model.CreditCards;
import com.cg.css.model.Transaction;
import com.cg.css.repository.TransactionRepository;

@Transactional
@Service
public class TransactionService {

	@Autowired
	private TransactionRepository repository;
	
	
	Logger logger = LoggerFactory.getLogger(TransactionService.class);

	/**
	 * This method performs credit transactions
	 **/
	public Transaction creditTransaction(Long cardNumber, double amount) throws Exception {

		Transaction transaction = new Transaction();
		transaction.setCardNumber(cardNumber);
		double balance = updateBalance("credit", amount, cardNumber);
		logger.info("balance :"+Double.toString(balance));
		transaction.setCreditAmt(amount);
		double redeemPnts = calculateRedeemPoints(amount);
		logger.info("redeem Points"+Double.toString(redeemPnts));
		transaction.setRedeemPoints(redeemPnts);
		transaction.setBalanceAmt(balance);
		return repository.save(transaction);
	}

	/**
	 * This method performs debit transactions
	 **/
	public Transaction debitTransaction(Long cardNumber, double amount) throws Exception {

		Transaction transaction = new Transaction();
		transaction.setCardNumber(cardNumber);
		double balance = updateBalance("debit", amount, cardNumber);
		logger.info("balance :"+Double.toString(balance));
		transaction.setDebitAmt(amount);
		double redeemPnts = calculateRedeemPoints(amount);
		logger.info("redeem Points"+Double.toString(redeemPnts));
		transaction.setRedeemPoints(redeemPnts);
		transaction.setBalanceAmt(balance);
		return repository.save(transaction);

	}

	/**
	 * This method is called by creditTransactions and debitTransactions for
	 * caluclating the balance
	 **/
	public double updateBalance(String type, double amount, Long cardNumber)
			throws ReachedSwipingLimitException, DebitAmountMoreThanBalanceException {
		Transaction transaction;
		List<Transaction> transactions = repository.findByCardNumberOrderByTransactionIdDesc(cardNumber);
		logger.info(transactions.toString());

		// Checking whether the given user has performed any transactions in the past
		if (transactions.isEmpty()) {
			transaction = null;
		} else {
			transaction = transactions.get(0);
		}

		if (transaction == null && type.equals("credit")) {
			return amount;
		}else if(transaction == null && type.equals("debit")){
			throw new DebitAmountMoreThanBalanceException("Debited amount more than amount to be debited!");
		}else if (type.equals("credit")) {
			
			double swipingLimit = 40000;
			
			// Checking whether user is exceeding swiping limit
			if ((transaction.getBalanceAmt() + amount) >= swipingLimit) {
				logger.error("Cannot withdraw! Swiping limit exceeded!");
				throw new ReachedSwipingLimitException("Cannot withdraw! Swiping limt exceeded!");
			}else
				return transaction.getBalanceAmt() + amount;
		} else {

			// checking whether debit amount is greater than balance amount
			if ((transaction.getBalanceAmt() - amount) <= 0) {
				logger.error("Debited amount more than amount to be debited!");
				throw new DebitAmountMoreThanBalanceException("Debited amount more than amount to be debited!");
			}else
				return transaction.getBalanceAmt() - amount;
		}
	}

	/**
	 * This method is called by creditTransactions and debitTransactions for caluclating the redeem points
	 **/
	private double calculateRedeemPoints(double amount) {
		double points = 0;
		if (amount >= 100) {
			points = (amount / 100) * 5;
			logger.info("reward points:"+ Double.toString(points));
		}
		return points;
	}

	/**
	 * This method retrieves credit transaction details of previous month from Transaction table
	 **/
	public double getCreditDetailsOfPreviousMonth(Long cardNumber) {
		Date to = Date.valueOf(LocalDate.now());
		Date from = Date.valueOf(LocalDate.now().minusDays(30));
		logger.info(to.toString()+" "+from.toString());
		return repository.sumOfCreditTransactions(from, to, cardNumber);
	}

	/**
	 * This method retrieves debit transaction details of previous month from Transaction table
	 **/
	public double getDebitDetailsOfPreviousMonth(Long cardNumber) {
		Date to = Date.valueOf(LocalDate.now());
		Date from = Date.valueOf(LocalDate.now().minusDays(30));
		logger.info(to.toString()+" "+from.toString());
		return repository.sumOfDebitTransactions(from, to, cardNumber);
	}

	/**
	 * This method retrieves credit transaction details of previous month from Transaction table
	 **/
	public double getRewardPointsOfPreviousMonth(Long cardNumber) {
		Date to = Date.valueOf(LocalDate.now());
		Date from = Date.valueOf(LocalDate.now().minusDays(30));
		logger.info(to.toString()+" "+from.toString());
		return repository.sumOfRewardPoints(from, to, cardNumber);
	}

	public List<Transaction> getCustomStatement(Long cardNumber, Date from, Date to) throws InvalidCardNumberException {
		return repository.getStatement(from, to, cardNumber);
	}

	public List<Transaction> getMonthlyStatement(Long cardNumber) throws InvalidCardNumberException {
		Date to = Date.valueOf(LocalDate.now());
		Date from = Date.valueOf(LocalDate.now().minusDays(30));
		
		return  repository.getStatement(from, to, cardNumber );
		
	}
}
