package com.rusticisoftware.cheddargetter.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

public class CGInvoice {
	protected String id;
	protected String number;
	protected String type;
	protected Date billingDatetime;
	protected Date createdDatetime;
	protected List<CGTransaction> transactions;
	protected List<CGCharge> charges;
	
	public String getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public String getType() {
		return type;
	}

	public Date getBillingDatetime() {
		return billingDatetime;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}
	
	public List<CGTransaction> getTransactions(){
		return transactions;
	}
	
	public List<CGCharge> getCharges() {
		return charges;
	}
	
	public double getTotalAmount(){
		if(charges == null){
			return 0.0d;
		}
		double sum = 0.0d;
		for(CGCharge charge : charges){
			sum += charge.getEachAmount() * charge.getQuantity();
		}
		return sum;
	}

	public CGInvoice(Element elem){
		this.id = elem.getAttribute("id");
		this.number = XmlUtils.getNamedElemValue(elem, "number");
		this.type = XmlUtils.getNamedElemValue(elem, "type");
		this.billingDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "billingDatetime"));
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		
		Element transactionsParent = XmlUtils.getFirstChildByTagName(elem, "transactions");
		if(transactionsParent != null){
			this.transactions = new ArrayList<CGTransaction>();
			List<Element> transactionsList = XmlUtils.getChildrenByTagName(transactionsParent, "transaction");
			for(Element transaction : transactionsList){
				this.transactions.add(new CGTransaction(transaction));
			}
		}
		
		Element chargesParent = XmlUtils.getFirstChildByTagName(elem, "charges");
		if(chargesParent != null){
			this.charges = new ArrayList<CGCharge>();
			List<Element> chargesList = XmlUtils.getChildrenByTagName(chargesParent, "charge");
			for(Element charge : chargesList){
				this.charges.add(new CGCharge(charge));
			}
		}
	}
}
