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
	protected List<CGSubscription> subscriptions = new ArrayList<CGSubscription>();
	
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
