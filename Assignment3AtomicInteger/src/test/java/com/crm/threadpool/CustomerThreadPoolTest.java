package com.crm.threadpool;

import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import com.crm.bean.Customer;
import com.crm.management.InventoryManagement;


public class CustomerThreadPoolTest {
	
	ArrayList<Customer> customerList = new ArrayList<Customer>();
	
	
	InventoryManagement inventoryManagement = new InventoryManagement();
	
	@Before
	public void setUp()
	{
		
		
		inventoryManagement.createProduct(5, 2);
		
		
		for(int i = 1; i <= 500; i++)
        {
				customerList.add(new Customer(i," customer "+i," pune "));
        	
				
        }
		
		
	}
	
	@Test
	public void createCustomerThreadsTest()
	{
		CustomerThreadPool customerThreadPool = new CustomerThreadPool();
		
		
	
		customerThreadPool.createCustomerThreads(customerList);
	}
	
	
}
