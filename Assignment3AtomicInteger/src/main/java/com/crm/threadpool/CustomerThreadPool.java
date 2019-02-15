package com.crm.threadpool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.crm.bean.Customer;
import com.crm.management.InventoryManagement;


/**
 * @author Ved
 * It's the task where customer threads come and do purchasing
 * They get product Id, payment Mode randomly
 *
 */
class Task implements Runnable{
	

	private Customer customer;
	
	Random random = new Random();
	InventoryManagement inventoryManagement = new InventoryManagement();
	
	Task(Customer customer){
		
		this.customer = customer;
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * In this purchasing of product is done if some thread comes back it is restarted untill all products are not 
	 * out of stock
	 */
	public void run() {
		
		System.out.println("Customer Thread Started Id: "+customer.getCustomerId()+"\n");
		
		while(!InventoryManagement.productQuantityMap.isEmpty()) {
		
		
		
		
			
			List<Integer> productIdList = new ArrayList<Integer>(InventoryManagement.productQuantityMap.keySet());
		
			
			
			Collections.shuffle(productIdList);
	            
			try {
				
				
	          customer.setTempProductId(productIdList.get(0));
	          
	         
	          
			}catch(IndexOutOfBoundsException indexOutOfBoundsException ) {
				
				System.out.println("Product Out Of Stock");
				
				
			}
	            
	         if(customer.getTempProductId() == 0)
	        	 continue;
			
			customer.setTempPayMode(random.nextInt(2));
	          
			boolean bool = inventoryManagement.purchase(customer);
		
	
		
		if(bool == false)
		{
			try {
				Thread.sleep(2000);
				
				
			} catch (InterruptedException interruptedException) {
				
				System.out.println(interruptedException.getMessage());
			}
			
			continue;
		}
		else
			break;
		
		}
		
		
		if(InventoryManagement.productQuantityMap.isEmpty()) {
		
		System.out.println("All products sold Market closed");
		}
			
		
	
		
	}
}
	

	/**
	 * @author gs-2023
	 *	Creates a thread pool with threads as many as Customers and executes it.
	 */
	public class CustomerThreadPool {
		
		 
	
		public void createCustomerThreads(ArrayList<Customer> customersList){
			
			
	
			Iterator<Customer> customerIterator = customersList.iterator();
	
			ExecutorService customerThreadPoolExecutor = Executors.newFixedThreadPool(customersList.size());
	
	
			for(int i = 0;i<customersList.size();i++)
				customerThreadPoolExecutor.execute(new Task(customerIterator.next()));
	
			
		
			customerThreadPoolExecutor.shutdown();
	
	}
		
		


}

