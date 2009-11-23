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
	
	public String getLastName() {
		return lastName;
	}
	
	public String getType() {
		return type;
	}

	public String getLastFour() {
		return lastFour;
	}

	public int getExpireMonth() {
		return expireMonth;
	}

	public int getExpireYear() {
		return expireYear;
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
