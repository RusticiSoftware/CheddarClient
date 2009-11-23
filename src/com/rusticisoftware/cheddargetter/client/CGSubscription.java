package com.rusticisoftware.cheddargetter.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.w3c.dom.Element;

public class CGSubscription {
	
	protected String id;
	protected String gatewayToken;
	protected String ccFirstName;
	protected String ccLastName;
	protected String ccType;
	protected String ccLastFour;
	protected Date ccExpirationDate;
	protected Date canceledDatetime;
	protected Date createdDatetime;
	protected List<CGPlan> plans;
	protected List<CGItem> items;
	protected List<CGInvoice> invoices;
	
	public String getId() {
		return id;
	}

	public String getGatewayToken() {
		return gatewayToken;
	}
	
	public String getCcFirstName() {
		return ccFirstName;
	}
	
	public String getCcLastName() {
		return ccLastName;
	}

	public String getCcType() {
		return ccType;
	}

	public String getCcLastFour() {
		return ccLastFour;
	}

	public Date getCcExpirationDate() {
		return ccExpirationDate;
	}

	public Date getCanceledDatetime() {
		return canceledDatetime;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public List<CGPlan> getPlans() {
		return plans;
	}

	public List<CGItem> getItems() {
		return items;
	}

	public List<CGInvoice> getInvoices() {
		return invoices;
	}

	public CGSubscription(Element elem){
		this.id = elem.getAttribute("id");
		this.gatewayToken = XmlUtils.getNamedElemValue(elem, "gatewayToken");
		this.ccFirstName = XmlUtils.getNamedElemValue(elem, "ccFirstName");
		this.ccLastName = XmlUtils.getNamedElemValue(elem, "ccLastName");
		this.ccType = XmlUtils.getNamedElemValue(elem, "ccType");
		this.ccLastFour = XmlUtils.getNamedElemValue(elem, "ccLastFour");

		this.ccExpirationDate = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "ccExpirationDate"));
		this.canceledDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "canceledDatetime"));
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		
		
		Element plansParent = XmlUtils.getFirstChildByTagName(elem, "plans");
		if(plansParent != null){
			this.plans = new ArrayList<CGPlan>();
			List<Element> planList = XmlUtils.getChildrenByTagName(plansParent, "plan");
			for(Element plan : planList){
				this.plans.add(new CGPlan(plan));
			}
		}
		
		Element itemsParent = XmlUtils.getFirstChildByTagName(elem, "items");
		if(itemsParent != null){
			this.items = new ArrayList<CGItem>();
			List<Element> itemList = XmlUtils.getChildrenByTagName(itemsParent, "item");
			for(Element item : itemList){
				this.items.add(new CGItem(item));
			}
		}
		
		Element invoicesParent = XmlUtils.getFirstChildByTagName(elem, "invoices");
		if(invoicesParent != null){
			this.invoices = new ArrayList<CGInvoice>();
			List<Element> invoiceList = XmlUtils.getChildrenByTagName(invoicesParent, "invoice");
			for(Element invoice : invoiceList){
				this.invoices.add(new CGInvoice(invoice));
			}
			
			//Sort invoices by billing date (most recent first)
			Collections.sort(this.invoices, 
				new Comparator<CGInvoice>() {
					public int compare(CGInvoice inv1, CGInvoice inv2) {
						return inv2.getBillingDatetime().compareTo(inv1.getBillingDatetime());
					}
				});
		}
	}
}
