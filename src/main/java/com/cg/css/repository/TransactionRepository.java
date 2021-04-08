package com.cg.css.repository;

import org.springframework.stereotype.Repository;

import com.cg.css.model.Transaction;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByCardNumberOrderByTransactionIdDesc(Long cardNumber);

	Transaction findByCardNumber(Long cardNumber);

	@Query("Select SUM(t.creditAmt) From Transaction t Where t.transactionDate Between :from And :now And t.cardNumber= :cardNumber")
	double sumOfCreditTransactions(@Param("from") Date from, @Param("now") Date now,
			@Param("cardNumber") Long cardNumber);

	@Query("Select SUM(t.debitAmt) From Transaction t Where t.transactionDate Between :from And :now And t.cardNumber= :cardNumber")
	double sumOfDebitTransactions(@Param("from") Date from, @Param("now") Date now,
			@Param("cardNumber") Long cardNumber);

	@Query("Select SUM(t.redeemPoints) From Transaction t Where t.transactionDate Between :from And :to And t.cardNumber= :cardNumber")
	double sumOfRewardPoints(@Param("from") Date from, @Param("to") Date to, @Param("cardNumber") Long cardNumber);

	@Query("Select t From Transaction t where t.transactionDate Between :from And :to And t.cardNumber= :cardNumber")
	List<Transaction> getStatement(@Param("from") Date from, @Param("to") Date to, @Param("cardNumber") Long cardNumber);

}
