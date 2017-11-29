package com.iheartmedia.coding.exercise.demo.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Jon Roberts
 */
public class Advertiser implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;
	
	private String contactName;
	
	private BigDecimal creditLimit;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
		
	public String getContactName() {
		return contactName;
	}
	
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	public BigDecimal getCreditLimit() {
		return creditLimit;
	}
	
	public void setCreditLimit(BigDecimal creditLimit) {
		this.creditLimit = creditLimit;
	}
	
	@Override	
	public String toString() {	
		return getId() + "," + getName();
	}
}
