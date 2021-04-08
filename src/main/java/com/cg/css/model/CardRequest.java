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
@Table(name = "CardRequest")
public class CardRequest {
	
	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int requestId;
	@Column(name = "userId")  
	private int userId;
	@Column(name = "card_type")
	private String cardType;
	@Column(name = "request_Date")
	private Date requestDate = Date.valueOf(LocalDate.now()) ;
	@Column(name = "status")
	private String status = "Pending";

	public int getRequestId() {
		return requestId;
	}

	public String getStatus() {
		return status;
	}

	public String getCardType() {
		return cardType;
	}

	public void setType(String cardType) {
		this.cardType = cardType;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getRequestDate() {
		return requestDate;
	}

	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CardRequest [requestId=");
		builder.append(requestId);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", requestDate=");
		builder.append(requestDate);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	}
}
