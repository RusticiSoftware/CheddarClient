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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

public class CGPlan {
	protected String code;
	protected String id;
	protected String name;
	protected String description;
	protected boolean isActive;
	protected int trialDays;
	protected String billingFrequency;
	protected String billingFrequencyPer;
	protected String billingFrequencyUnit;
	protected int billingFrequencyQuantity;
	protected String setupChargeCode;
	protected float setupChargeAmount;
	protected String recurringChargeCode;
	protected float recurringChargeAmount;
	protected Date createdDatetime;
	protected List<CGItem> items = new ArrayList<CGItem>();

	public String getCode() {
		return code;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public boolean isActive() {
		return isActive;
	}

	public int getTrialDays() {
		return trialDays;
	}

	public String getBillingFrequency() {
		return billingFrequency;
	}

	public String getBillingFrequencyPer() {
		return billingFrequencyPer;
	}

	public String getBillingFrequencyUnit() {
		return billingFrequencyUnit;
	}

	public int getBillingFrequencyQuantity() {
		return billingFrequencyQuantity;
	}

	public String getSetupChargeCode() {
		return setupChargeCode;
	}

	public float getSetupChargeAmount() {
		return setupChargeAmount;
	}

	public String getRecurringChargeCode() {
		return recurringChargeCode;
	}

	public float getRecurringChargeAmount() {
		return recurringChargeAmount;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public List<CGItem> getItems() {
		return items;
	}

	public CGPlan(Element elem){
		this.code = elem.getAttribute("code");
		this.id = elem.getAttribute("id");
		this.name = XmlUtils.getNamedElemValue(elem, "name");
		this.description = XmlUtils.getNamedElemValue(elem, "description");
		this.isActive = (Boolean)XmlUtils.getNamedElemValue(elem, "isActive", Boolean.class, false);
		this.trialDays = (Integer)XmlUtils.getNamedElemValue(elem, "trialDays", Integer.class, 0);
		this.billingFrequency = XmlUtils.getNamedElemValue(elem, "billingFrequency");
		this.billingFrequencyPer = XmlUtils.getNamedElemValue(elem, "billingFrequency");
		this.billingFrequencyUnit = XmlUtils.getNamedElemValue(elem, "billingFrequencyUnit");
		this.billingFrequencyQuantity = (Integer)XmlUtils.getNamedElemValue(elem, "billingFrequencyQuantity", Integer.class, 0);
		this.setupChargeCode = XmlUtils.getNamedElemValue(elem, "setupChargeCode");
		this.setupChargeAmount = (Float)XmlUtils.getNamedElemValue(elem, "setupChargeAmount", Float.class, 0.0f);
		this.recurringChargeCode = XmlUtils.getNamedElemValue(elem, "recurringChargeCode");
		this.recurringChargeAmount = (Float)XmlUtils.getNamedElemValue(elem, "recurringChargeAmount", Float.class, 0.0f);
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		
		Element itemsParent = XmlUtils.getFirstChildByTagName(elem, "items");
		if(itemsParent != null){
			this.items = new ArrayList<CGItem>();
			List<Element> itemList = XmlUtils.getChildrenByTagName(itemsParent, "item");
			for(Element item : itemList){
				this.items.add(new CGItem(item));
			}
		}
	}
}
