package com.crm.management;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import com.crm.bean.Customer;
import com.crm.bean.Products;

/**
 * @author Ved
 * It stores all Products, product - quantity mapping 
 * Creating Product, purchasing products, payment and file writing are done.
 * 
 *
 */
public class InventoryManagement {
	
	public static ConcurrentHashMap<Integer,AtomicInteger> productQuantityMap = new ConcurrentHashMap<Integer,AtomicInteger>();
	
	private static Map<Integer,Products> productStock = new HashMap<Integer,Products>();

	
	
	
	/**
	 * @param productCount
	 * @param quantity1Q	
	 * @return
	 * populates the productStock and do Product - Quantity mapping 
	 */
	public boolean createProduct(int productCount, int quantity)
	{
	
		
		for(int i = 1; i <= productCount; i++)
		{
			productQuantityMap.put(i, new AtomicInteger(quantity));
			
			productStock.put(i,new Products(i,"product"+i,new AtomicInteger(quantity)));
			
			
		}
		
		
		
		return true;

	}
	
	/**
	 * @param customer
	 * @param productId
	 * @param customer.getTempPayMode()
	 * @return
	 * Purchase product alongwith payment 
	 * It increments, decrements product quantity if quantity becomes zero removes the product 
	 */
	public boolean purchase(Customer customer )
	{
		long startTime = System.currentTimeMillis();
		boolean bool = false;
		
		
		
		
		
		if(!productQuantityMap.isEmpty())
		{
			
			
			
			
			if(productQuantityMap.get(customer.getTempProductId()).decrementAndGet() >= 0){
			
			System.out.println("Product Id: "+customer.getTempProductId()+" reserved for "+customer.getCustomerName());
			
			}
			else
			{
				productQuantityMap.get(customer.getTempProductId()).incrementAndGet();
					
				
				
				return bool;
			}
			
		
		
		
		
		if(payment(customer))
		{
			
			System.out.println("Product Id: "+productStock.get(customer.getTempProductId()).getProductId()+"Quantity: "+
									productStock.get(customer.getTempProductId()).getQuantity().get());
			
			if(productStock.get(customer.getTempProductId()).getQuantity().decrementAndGet() == 0)
				productQuantityMap.remove(customer.getTempProductId());
			
			
			System.out.println("Payment Successfull...");
			
			System.out.println("Product Id: "+customer.getTempProductId()+"\t--------------------------------------------------------------"
					+ "sold to Customer Id: "+customer.getCustomerId());
			
			
				
				
			if(customer.getTempPayMode() == 0) {
				 
				 customer.setPaymentMode("Online Payment");
				 customer.setTransactionStatus("succeded...");
			}
			else if(customer.getTempPayMode() == 1) {
				 
				customer.setPaymentMode("Cash On Delivery");
				customer.setTransactionStatus("cash recieved...");
			}
			
			
			
			customer.setProduct(productStock.get(customer.getTempProductId()));
			
			
			boolean status = writeInFile(customer, true);
			
			if(status)
			System.out.println("Written in file\n");
			else
				System.out.println("Writing in file failed\n");
			
			 
			
			bool = true;
			
		}
		else 
		{
			
			System.out.println("Product Id: "+productQuantityMap.get(customer.getTempProductId())+" Customer has product: "+customer.getTempProductId());
			
			
			 productQuantityMap.get(customer.getTempProductId()).incrementAndGet();
			
			
			
			
			
			
			if(customer.getTempPayMode() == 0) {
				 
				 customer.setPaymentMode("Online Payment");
				 customer.setTransactionStatus("failed...");
			}
			else if(customer.getTempPayMode() == 1) {
				 
				customer.setPaymentMode("Cash On Delivery");
				customer.setTransactionStatus("product rejected...");
			}
			
			
			
			customer.setProduct(productStock.get(customer.getTempProductId()));
			
			
			boolean status = writeInFile(customer,false);
			
			if(status)
			System.out.println("Writtent in file");
			else
				System.out.println("Writing in file failed");
			
			
				
			
			System.out.println("\nStock Refilled \n");
			
			
			}
			
		}
		else
		{
			System.out.println("Time Elapsed - Difference in Millis "+(System.currentTimeMillis() - startTime));
		return false;
		}
			System.out.println("Time Elapsed - Difference in Millis "+(System.currentTimeMillis() - startTime));
		return bool;
	}
	
	/**
	 * @param paymentMode
	 * @return
	 * Do Online payment and Cash On delivery
	 * Success and Failure are totally random
	 */
	public boolean payment(Customer customer)
	{
		

		boolean bool = false;
		Random random = new Random();
		
		if(customer.getTempPayMode() == 0) {
			
			
			
		int randomNumber = random.nextInt(2);
		
		if(randomNumber == 0) {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println(e.getMessage());
			}
			
			System.out.println("Online Payment Failed for....Customer Id: "+Thread.currentThread().getName()+"\n");
			
			bool = false;
		}
		else {
			
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				
				System.out.println(e.getMessage());
			}
			
			System.out.println("Online Payment Successfull...Customer Id: "+Thread.currentThread().getName()+"\n");
			bool = true;
		}
		}
		else if(customer.getTempPayMode() == 1)
		{
			System.out.println("Product out for delivery...Customer Id: "+Thread.currentThread().getName()+"\n");
			
			int randomNumber = random.nextInt(2);
			
			if(randomNumber == 0) {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					System.out.println(e.getMessage());
				}
				
				System.out.println("Product was rejected...Customer Id: "+Thread.currentThread().getName()+"\n");
				
				bool = false;
			}
			else {
				
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					
					System.out.println(e.getMessage());
				}
				
				System.out.println("Product delivered successfully and payment recieved..Customer Id: "
				+Thread.currentThread().getName()+"\n");
				bool = true;
			}
		}
		return bool;
		
		
		
	}
	
	/**
	 * @param customer
	 * @param transactionStatus
	 * @return
	 * Writes the successful as well as failure transactions in their respective files
	 */
	public boolean writeInFile(Customer customer,boolean transactionStatus)
	{
		
		BufferedWriter output = null;
		 
		
		/* if(transactionStatus == true)
			 	customer.setFilePath(new File("E:\\CRM\\SuccessTransaction\\"+customer.getCustomerName()+".txt"));
		 else
			 	customer.setFilePath(new File("E:\\CRM\\FailureTransaction\\"+customer.getCustomerName()+".txt"));*/
		
		
		 
		 /* Note: This block writes all the entries in the single file only
		  For successful transactions in the SuccessTransactionTest file 
		  and for failure transactions in the FailureTranactionsTest File
		  Except this no change and effect will be there*/
		 if(transactionStatus == true)
			 	customer.setFilePath(new File("E:\\SuccessTransactionTest.txt"));
		 else
			 	customer.setFilePath(new File("E:\\FailureTransactionTest.txt"));
		 
		 try
	        {    
	            
	           synchronized(this) {
			 	FileWriter fileWriter = new FileWriter(customer.getFilePath(),true);
	              output = new BufferedWriter(fileWriter);
	              
	              output.write(customer.toString());
	              output.newLine();
	            
	          
	              	output.close();
		            fileWriter.close();
				
	           }
	              return true;
	        } 
	          
	        catch(IOException ioException) 
	        { 
	        	
	            System.out.println(ioException.getMessage()); 
	            
	            return false;
	        } 
	}
}
