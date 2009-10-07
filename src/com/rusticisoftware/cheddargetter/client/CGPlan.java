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
	protected List<CGItem> items;

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
