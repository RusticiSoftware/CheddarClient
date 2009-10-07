package com.rusticisoftware.cheddargetter.client;

import java.util.Date;

import org.w3c.dom.Element;

public class CGItem {
	protected String id;
	protected String code;
	protected String name;
	protected int quantity;
	protected int quantityIncluded;
	protected boolean isPeriodic;
	protected float overageAmount;
	protected Date createdDatetime;
	protected Date modifiedDatetime;
	
	public String getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getQuantityIncluded() {
		return quantityIncluded;
	}

	public boolean isPeriodic() {
		return isPeriodic;
	}

	public float getOverageAmount() {
		return overageAmount;
	}

	public Date getCreatedDatetime() {
		return createdDatetime;
	}

	public Date getModifiedDatetime() {
		return modifiedDatetime;
	}

	public CGItem(Element elem){
		this.id = elem.getAttribute("id");
		this.code = elem.getAttribute("code");
		this.name = XmlUtils.getNamedElemValue(elem, "name");
		this.quantity = (Integer)XmlUtils.getNamedElemValue(elem, "quantity", Integer.class, 0);
		this.quantityIncluded = (Integer)XmlUtils.getNamedElemValue(elem, "quantityIncluded", Integer.class, 0);
		this.isPeriodic = (Boolean)XmlUtils.getNamedElemValue(elem, "isPeriodic", Boolean.class, false);
		this.overageAmount = (Float)XmlUtils.getNamedElemValue(elem, "overageAmount", Float.class, 0.0f);
		this.createdDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "createdDatetime"));
		this.modifiedDatetime = CGService.parseCgDate(XmlUtils.getNamedElemValue(elem, "modifiedDatetime"));
	}
}
