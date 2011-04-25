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
	protected List<CGPlan> plans = new ArrayList<CGPlan>();
	protected List<CGItem> items = new ArrayList<CGItem>();
	protected List<CGInvoice> invoices = new ArrayList<CGInvoice>();
	
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
