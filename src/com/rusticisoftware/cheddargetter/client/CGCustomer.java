package com.rusticisoftware.cheddargetter.client;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.w3c.dom.Element;

public class CGCustomer {
	protected String id;
	protected String code;
	protected String firstName;
	protected String lastName;
	protected String company;
	protected String email;
	protected String gatewayToken;
	protected Date createdDatetime;
	protected Date modifiedDatetime;
	protected List<CGSubscription> subscriptions;
	
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getCompany() {
		return company;
	}

	public String getEmail() {
		return email;
	}

	public String getGatewayToken() {
		return gatewayToken;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	public List<CGSubscription> getSubscriptions() {
		return subscriptions;
	}

	public CGCustomer(Element elem){
		this.id = elem.getAttribute("id");
		this.code = elem.getAttribute("code");
		this.firstName = XmlUtils.getNamedElemValue(elem, "firstName");
		this.lastName = XmlUtils.getNamedElemValue(elem, "lastName");
		this.company = XmlUtils.getNamedElemValue(elem, "company");
		this.email = XmlUtils.getNamedElemValue(elem, "email");
		this.gatewayToken = XmlUtils.getNamedElemValue(elem, "gatewayToken");
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		this.modifiedDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "modifiedDatetime"));
		
		Element subsParent = XmlUtils.getFirstChildByTagName(elem, "subscriptions");
		if(subsParent != null){
			this.subscriptions = new ArrayList<CGSubscription>();
			List<Element> subsList = XmlUtils.getChildrenByTagName(subsParent, "subscription");
			for(Element sub : subsList){
				this.subscriptions.add(new CGSubscription(sub));
			}
			
			//Sort subscriptions by create date (most recent first)
			Collections.sort(this.subscriptions, 
				new Comparator<CGSubscription>() {
					public int compare(CGSubscription sub1, CGSubscription sub2) {
						return sub2.getCreatedDatetime().compareTo(sub1.getCreatedDatetime());
					}
				});
		}
	}
}
