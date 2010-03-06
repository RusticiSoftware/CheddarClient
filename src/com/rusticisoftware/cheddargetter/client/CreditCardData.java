package com.rusticisoftware.cheddargetter.client;

public class CreditCardData {
	private String firstName;
	private String lastName;
	private String type;
	private String lastFour;
	private int expireMonth;
	private int expireYear;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLastFour() {
		return lastFour;
	}

	public void setLastFour(String lastFour) {
		this.lastFour = lastFour;
	}

	public int getExpireMonth() {
		return expireMonth;
	}

	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}

	public int getExpireYear() {
		return expireYear;
	}

	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}

	public CreditCardData() {
		
	}
	
	public CreditCardData(String firstName, String lastName, String type, String lastFour, int expireMonth, int expireYear){
		this.firstName = firstName;
		this.lastName = lastName;
		this.type = type;
		this.lastFour = lastFour;
		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
	}
}
