package com.crm.bean;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author Ved
 *Products Bean contain all information of products
 */
public class Products implements Serializable {

	
	private static final long serialVersionUID = 308709005735730184L;
	
	private int productId;
	private String productName;
	private AtomicInteger quantity;
	
	
	
	
	public Products(int productId, String productName, AtomicInteger quantity) {
		
		this.productId = productId;
		this.productName = productName;
		this.quantity = quantity;
	}

	public AtomicInteger getQuantity() {
		return quantity;
	}

	public void setQuantity(AtomicInteger quantity) {
		this.quantity = quantity;
	}

	
	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
	
}
