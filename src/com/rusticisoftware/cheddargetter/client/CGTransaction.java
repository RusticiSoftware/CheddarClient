package com.rusticisoftware.cheddargetter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

public class CGTransaction {
	protected String id;
	protected String code;
	protected String parentId;
	//CGGatewayAccount ?
	protected float amount;
	protected String memo;
	protected String response;
	protected Date transactedDatetime;
	protected Date createdDatetime;
	
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getParentId() {
		return parentId;
	}

	public float getAmount() {
		return amount;
	}

	public String getMemo() {
		return memo;
	}

	public String getResponse() {
		return response;
	}

	public Date getTransactedDatetime() {
		return transactedDatetime;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public CGTransaction(Element elem) {
		this.id = elem.getAttribute("id");
		this.code = elem.getAttribute("code");
		this.parentId = XmlUtils.getNamedElemValue(elem, "parentId");
		this.amount = (Float)XmlUtils.getNamedElemValue(elem, "amount", Float.class, 0.0f);
		this.memo = XmlUtils.getNamedElemValue(elem, "memo");
		this.response = XmlUtils.getNamedElemValue(elem, "response");
		this.transactedDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "transactedDatetime"));
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
	}
	
}
