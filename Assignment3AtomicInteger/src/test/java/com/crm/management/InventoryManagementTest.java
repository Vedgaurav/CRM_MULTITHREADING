package com.crm.management;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Before;
import org.junit.Test;
import com.crm.bean.Customer;
import com.crm.bean.Products;




public class InventoryManagementTest {

	
	public ConcurrentHashMap<Integer,AtomicInteger> productQuantityMap = new ConcurrentHashMap<Integer,AtomicInteger>();
	
	public Map<Integer,Products> productStock = new HashMap<Integer,Products>();
	
	InventoryManagement inventoryManagement = new InventoryManagement();
	public ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	@Before
	public void setUp()
	{
		
		
		for(int i = 1; i <= 10; i++)
		{
			productQuantityMap.put(i, new AtomicInteger(3));
			
			productStock.put(i,new Products(i,"product"+i,new AtomicInteger(3)));
		}
		
		
		for(int i = 1; i <= 4; i++)
        {
        	customerList.add(new Customer(i," customer "+i," pune "));
        	
        	
        }
		
		
	}
	
	
	
	
	@Test
	public void createProductTest()
	{
		
		

		 inventoryManagement.createProduct(10, 3);
		
	
		
		
		
		Iterator<Map.Entry<Integer, AtomicInteger>> iterator1 = productQuantityMap.entrySet().iterator(); 
        
	    
		
		for (Map.Entry<Integer,AtomicInteger> entry : productQuantityMap.entrySet()) 
		{
			assertEquals(entry.getKey(),iterator1.next().getKey());
			
		}
		
		iterator1 = productQuantityMap.entrySet().iterator();
		
		for (Map.Entry<Integer,AtomicInteger> entry : productQuantityMap.entrySet()) 
		{
			assertEquals(entry.getValue().getClass(),iterator1.next().getValue().getClass());
			
		}
		
		
		 Iterator<Map.Entry<Integer, Products>>iterator2 = productStock.entrySet().iterator(); 
        
	    
		
		for (Map.Entry<Integer,Products> entry : productStock.entrySet()) 
		{
			assertEquals(entry.getKey(),iterator2.next().getKey());
			
		}
		
		iterator2 = productStock.entrySet().iterator();
		
		for (Map.Entry<Integer,Products> entry : productStock.entrySet()) 
		{
			assertEquals(entry.getValue().getClass(),iterator2.next().getValue().getClass());
			
		}
		
		
		
	}
	
	@Test
	public void purchaseProductTest1() throws FileNotFoundException
	{
		
		 inventoryManagement.createProduct(10, 3);
		 
		 customerList.get(0).setTempProductId(1);
		 customerList.get(0).setTempPayMode(1);
		 
		  
		 
		 
		  
		boolean bool =   inventoryManagement.purchase(customerList.get(0));
		  
		try {
			
		assertEquals(true,bool);
		
		}catch(AssertionError assertionError)
		{
			assertEquals(false,bool);
		}
		  
		  
		
		
	}
	
	@Test
	public void purchaseProductTest2() throws FileNotFoundException
	{
		
		 inventoryManagement.createProduct(10, 3);
		 
		 customerList.get(0).setTempProductId(1);
		 customerList.get(0).setTempPayMode(0);
		 
		  
		
		boolean bool =   inventoryManagement.purchase(customerList.get(0));
		  
		try {
			
		assertEquals(true,bool);
		
		}catch(AssertionError assertionError)
		{
			assertEquals(false,bool);
		}
		  
	}
	
	
	
	@Test
	public void purchaseProductTest3() throws FileNotFoundException
	{
		
		 inventoryManagement.createProduct(2,0);
		 
		 customerList.get(0).setTempProductId(1);
		 customerList.get(0).setTempPayMode(1);
		 
		  
		
		boolean bool =   inventoryManagement.purchase(customerList.get(0));
		  
		try {
			
		assertEquals(true,bool);
		
		}catch(AssertionError assertionError)
		{
			assertEquals(false,bool);
		}
		  
		  
		
		
	}
	
	@Test
	public void purchaseProductTest4() throws FileNotFoundException
	{
		
		 inventoryManagement.createProduct(2,0);
		 
		 customerList.get(0).setTempProductId(1);
		 customerList.get(0).setTempPayMode(7);
		 
		 
		
		boolean bool =   inventoryManagement.purchase(customerList.get(0));
		
		
		
		
		  
		try {
			
		assertEquals(true,bool);
		
		}catch(AssertionError assertionError)
		{
			assertEquals(false,bool);
		}
		  
		  
		
		
	}
	
	
	
	@Test
	public void paymentTest1()
	{
		customerList.get(0).setTempPayMode(1);
		
		boolean boolActual = inventoryManagement.payment(customerList.get(0));
		
	
		try {
			
		assertEquals(true, boolActual);
		
		}catch(AssertionError assertionError) {
			
			//System.out.println("Test Exception on paymentTest");
			assertEquals(false, boolActual);
			
		}
	}
	
	@Test
	public void paymentTest2()
	{
		customerList.get(0).setTempPayMode(0);
		
		boolean boolActual = inventoryManagement.payment(customerList.get(0));
		
	
		try {
			
		assertEquals(true, boolActual);
		
		}catch(AssertionError assertionError) {
			
			//System.out.println("Test Exception on paymentTest");
			assertEquals(false, boolActual);
			
		}
	}
	
	
	@Test
	public void writeInFileTest()
	{
		customerList.get(0).setProduct(productStock.get(1));
		customerList.get(0).setPaymentMode("Cash On Delivery");
		customerList.get(0).setTransactionStatus("product rejected...");
		inventoryManagement.writeInFile(customerList.get(0), false);
	}
}
