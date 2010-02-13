package com.rusticisoftware.cheddargetter.client;

import org.w3c.dom.Document;

public interface ICGService {

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract String getPassword();

	public abstract void setPassword(String password);

	public abstract String getProductCode();

	public abstract void setProductCode(String productCode);

	public abstract CGCustomer getCustomer(String custCode) throws Exception;

	public abstract boolean customerExists(String custCode);

	public abstract Document getAllCustomers() throws Exception;

	public abstract CGCustomer createNewCustomer(String custCode,
			String firstName, String lastName, String email, String company,
			String subscriptionPlanCode, String ccFirstName, String ccLastName,
			String ccNumber, String ccExpireMonth, String ccExpireYear,
			String ccCardCode, String ccZip) throws Exception;

	public abstract Document updateSubscription(String customerCode,
			String planCode, String ccFirstName, String ccLastName,
			String ccNumber, String ccExpireMonth, String ccExpireYear,
			String ccCardCode, String ccZip) throws Exception;

	public abstract Document cancelSubscription(String customerCode)
			throws Exception;

	public abstract Document addItemQuantity(String customerCode,
			String itemCode) throws Exception;

	public abstract Document addItemQuantity(String customerCode,
			String itemCode, int quantity) throws Exception;

	public abstract CreditCardData getLatestCreditCardData(String customerCode)
			throws Exception;

	public abstract boolean isLatestSubscriptionCanceled(String customerCode)
			throws Exception;

	public abstract int getCurrentItemUsage(String customerCode, String itemCode)
			throws Exception;

}