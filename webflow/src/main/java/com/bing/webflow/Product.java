package com.bing.webflow;

import java.io.Serializable;

public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6743316747564242294L;
	
	private String pName;
	private int price;
	private int id;

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int id, String pName, int price) {
		super();
		this.id = id;
		this.pName = pName;
		this.price = price;
	}

}
