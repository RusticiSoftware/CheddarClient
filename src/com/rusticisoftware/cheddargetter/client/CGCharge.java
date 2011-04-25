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
