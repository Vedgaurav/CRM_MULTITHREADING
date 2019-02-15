package com.crm.bean;

import java.io.File;
import java.io.Serializable;


/**
 * @author Ved
 *Customer Bean contains customers all information
 */
public class Customer implements Serializable {

	
	private static final long serialVersionUID = 4445544695383880353L;
	private int customerId;
	private String customerName;
	private String customerAddress;
	private String paymentMode;
	private Products product;
	private String transactionStatus;
	private int tempProductId;
	private int tempPayMode;
	private File filePath;
	
	
	
	public Customer(int customerId, String customerName, String customerAddress) {
		
		this.customerId = customerId;
		this.customerName = customerName;
		this.customerAddress = customerAddress;
	}
	
	
	
	public File getFilePath() {
		return filePath;
	}

	public void setFilePath(File filePath) {
		this.filePath = filePath;
	}


	public int getTempPayMode() {
		return tempPayMode;
	}


	public void setTempPayMode(int tempPayMode) {
		this.tempPayMode = tempPayMode;
	}


	public int getTempProductId() {
		return tempProductId;
	}


	public void setTempProductId(int tempProductId) {
		this.tempProductId = tempProductId;
	}


	public String getTransactionStatus() {
		return transactionStatus;
	}


	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}


	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public Products getProduct() {
		return product;
	}


	public void setProduct(Products product) {
		this.product = product;
	}


	public String getPaymentMode() {
		return paymentMode;
	}


	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}
	
	
	@Override
	public String toString() {
		return "Customer [ CustomerId= " + customerId + " CustomerName= " + customerName + " CustomerAddress= "
				+ customerAddress + " PaymentMode= " + paymentMode + " Product Id= " + product.getProductId() +
				"Product Name = "+product.getProductName()+" Product Quantity= "+1+", transactionStatus= "
				+ transactionStatus + "]";
	}
	
	
	
}
