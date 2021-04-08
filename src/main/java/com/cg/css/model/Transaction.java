package com.cg.css.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "trans_info")
public class Transaction {

	@Id
	@Column(name = "transactionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	@Column(name = "cardNumber")
	private Long cardNumber;
	@Column(name = "transactionDate")
	private Date transactionDate = Date.valueOf(LocalDate.now());
	@Column(name = "creditAmt")
	private double creditAmt;
	@Column(name = "debitAmt")
	private double debitAmt;
	@Column(name = "balanceAmt")
	private double balanceAmt;
	@Column(name = "redeemPoints")
	private double redeemPoints = 0;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public double getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(double credit) {
		this.creditAmt = credit;
	}

	public double getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(double debitAmt) {
		this.debitAmt = debitAmt;
	}

	public double getBalanceAmt() {
		return balanceAmt;
	}

	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}

	public double getRedeemPoints() {
		return redeemPoints;
	}

	public void setRedeemPoints(double redeemPoints) {
		this.redeemPoints = redeemPoints;
	}

	public Transaction(int transactionId, Long cardNumber, Date transactionDate, double credit, double debitAmt,
			double balanceAmt, double redeemPoints) {
		super();
		this.transactionId = transactionId;
		this.cardNumber = cardNumber;
		this.transactionDate = transactionDate;
		this.creditAmt = credit;
		this.debitAmt = debitAmt;
		this.balanceAmt = balanceAmt;
		this.redeemPoints = redeemPoints;
	}

	public Transaction() {
		super();
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", cardNumber=" + cardNumber + ", transactionDate="
				+ transactionDate + ", creditAmt=" + creditAmt + ", debitAmt=" + debitAmt + ", balanceAmt=" + balanceAmt
				+ ", redeemPoints=" + redeemPoints + "]";
	}

}
