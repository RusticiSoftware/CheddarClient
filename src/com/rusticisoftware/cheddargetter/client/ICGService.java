/* Software License Agreement (BSD License)
 * 
 * Copyright (c) 2010-2011, Rustici Software, LLC
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *     * Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *     * Neither the name of the <organization> nor the
 *       names of its contributors may be used to endorse or promote products
 *       derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL Rustici Software, LLC BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.rusticisoftware.cheddargetter.client;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

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
	
	public CGCustomer updateCustomerAndSubscription(String custCode, String firstName, String lastName, 
			String email, String company, String subscriptionPlanCode, String ccFirstName,
			String ccLastName, String ccNumber, String ccExpireMonth, String ccExpireYear, 
			String ccCardCode, String ccZip) throws Exception;
	
	public CGCustomer updateCustomer(String custCode, String firstName, String lastName, 
			String email, String company) throws Exception;

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
	
	public abstract Document removeItemQuantity(String customerCode,
			String itemCode, int quantity) throws Exception;
	
	public abstract Document addCustomCharge(String customerCode, String itemCode,
			int quantity, String eachAmount) throws Exception;

	public abstract CreditCardData getLatestCreditCardData(String customerCode)
			throws Exception;

	public abstract boolean isLatestSubscriptionCanceled(String customerCode)
			throws Exception;

	public abstract int getCurrentItemUsage(String customerCode, String itemCode)
			throws Exception;
	
	public abstract Document presentFinalBill(String customerCode) throws Exception;
	
	public Document makeServiceCall(String path, Map<String,String> paramMap) throws Exception;

}