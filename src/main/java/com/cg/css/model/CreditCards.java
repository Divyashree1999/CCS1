package com.cg.css.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "CreditCards")
public class CreditCards {

	@Id
	@Column(name = "card_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int  cardId;
	
	@NotNull(message="Please specify the card name")
	@Column(name = "card_name")
	private String cardName;
	
	@NotNull(message="Please specify the minimum salary")
	@Column(name = "min_salary")
	private double minSalary;
	
	@NotNull(message="Please specify the swiping limit")
	@Column(name = "swiping_limit")
	private double swipingLimit;
	
	@NotNull(message="Please specify the validity period")
	@Column(name = "period")
	private int period;

	public int getCardId() {
		return cardId;
	}

	public void setCardId(int cardId) {
		this.cardId = cardId;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public double getMinSalary() {
		return minSalary;
	}

	public void setMinSalary(double minSalary) {
		this.minSalary = minSalary;
	}

	public double getSwipingLimit() {
		return swipingLimit;
	}

	public void setSwipingLimit(double swipingLimit) {
		this.swipingLimit = swipingLimit;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	public CreditCards() {
		super();
		
	}

	@Override
	public String toString() {
		return "CreditCards [cardId=" + cardId + ", cardName=" + cardName + ", minSalary=" + minSalary
				+ ", swipingLimit=" + swipingLimit + ", period=" + period + "]";
	}
}
