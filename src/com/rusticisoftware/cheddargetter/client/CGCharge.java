package com.rusticisoftware.cheddargetter.client;

import java.util.Date;

import org.w3c.dom.Element;

public class CGCharge {
	protected String id;
	protected String code;
	protected String type;
	protected int quantity;
	protected float eachAmount;
	protected String description;
	protected Date createdDatetime;
	
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getType() {
		return type;
	}

	public int getQuantity() {
		return quantity;
	}

	public float getEachAmount() {
		return eachAmount;
	}

	public String getDescription() {
		return description;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public CGCharge(Element elem){
		this.id = elem.getAttribute("id");
		this.code = elem.getAttribute("code");
		this.type = XmlUtils.getNamedElemValue(elem, "type");
		this.quantity = (Integer)XmlUtils.getNamedElemValue(elem, "quantity", Integer.class, 0);
		this.eachAmount = (Float)XmlUtils.getNamedElemValue(elem, "eachAmount", Float.class, 0.0f);
		this.description = XmlUtils.getNamedElemValue(elem, "description");
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
	}
}
