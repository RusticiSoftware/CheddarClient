package com.rusticisoftware.cheddargetter.client;

public class CreditCardData {
	private String type;
	private String lastFour;
	private int expireMonth;
	private int expireYear;
	
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

	public CreditCardData(String type, String lastFour, int expireMonth, int expireYear){
		this.type = type;
		this.lastFour = lastFour;
		this.expireMonth = expireMonth;
		this.expireYear = expireYear;
	}
}
