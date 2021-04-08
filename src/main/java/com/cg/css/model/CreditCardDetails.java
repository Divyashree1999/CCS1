package com.cg.css.model;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="CardDetailsTable")
public class CreditCardDetails{
	@Id
	@Column(name ="credit_Card_Number")
	private Long creditCardNumber;
	
	@Column(name = "user_Id")
	private int userId;
	
	@Column(name = "type")
	private String type;
	
	@Column(name ="issueDate")
	private Date issueDate=Date.valueOf(LocalDate.now());
	
	@Column(name = "expiryDate")
	private Date expiryDate;
	
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public void setExpiryDate(Date cal) {
		this.expiryDate = cal;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}

	public Long getCreditCardNumber() {
		return creditCardNumber;
	}

	public void setCreditCardNumber(Long creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}