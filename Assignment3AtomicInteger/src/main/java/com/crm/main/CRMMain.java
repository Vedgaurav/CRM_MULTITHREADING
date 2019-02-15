package com.crm.main;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;

import com.crm.bean.Customer;
import com.crm.management.InventoryManagement;
import com.crm.threadpool.CustomerThreadPool;

class MyNegativeIntegerException extends Exception
{
	
	private static final long serialVersionUID = -1890049393829508895L;

	public MyNegativeIntegerException(String message) {
		
		super(message);
		
	}
	

}

	/**
	 * @author Ved
	 * It takes validated input like number of customers, no of products and their quantity
	 *
	 */
	public class CRMMain 
{
    public static void main( String[] args )
    {
    	String customerName = " customer ";
    	String customerAddress = " pune " ;
    	int customerCount = 0;
    	boolean bool = false;
    	int productCount = 0;
    	int productQuantity = 0;
    	
    	Scanner scanner = new Scanner(System.in);
    	ArrayList<Customer> customerList = new ArrayList<Customer>();
    	
    	while(!bool) {
    	
    	System.out.println("Enter no of customers");
        
    	
    	try {
    		
         customerCount = scanner.nextInt();
         
         if(customerCount <= 0)
         {
        	throw new MyNegativeIntegerException("Negative Integer not allowed");
         }
         
         
         bool = true;
         
    	}catch(InputMismatchException | MyNegativeIntegerException inputMismatchException) {
    		
    		System.out.println("Wrong input !!!!!!!!!!\nPlease Enter a Positive Integer value");
    		scanner.nextLine();
    		
    	}
    	
    	
    	}
    
        
        for(int i = 1; i <= customerCount; i++)
        {
        	customerList.add(new Customer(i,customerName+i,customerAddress));
        }
        
        while(bool) {
        	
        	System.out.println("Enter no of Products");
            
        	
        	try {
        		
             productCount = scanner.nextInt();
             
             if(productCount <= 0)
             {
            	 throw new MyNegativeIntegerException("Negative Integer not allowed");
             }
             
             System.out.println("Enter quantity");
             
              productQuantity = scanner.nextInt();
              
              if(productQuantity <= 0)
              {
            	  throw new MyNegativeIntegerException("Negative Integer not allowed");
              }
             
             bool = false;
             
        	}catch(InputMismatchException | MyNegativeIntegerException inputMismatchException) {
        		
        		System.out.println("Wrong input !!!!!!!!!!\nPlease Enter a Positive Integer value");
        		
        		scanner.nextLine();
        		continue;
        	}
        	}
       
        
      
        
        
       InventoryManagement inventoryManagement = new InventoryManagement();
        
        inventoryManagement.createProduct(productCount, productQuantity);
        
        CustomerThreadPool customerThreadPool = new CustomerThreadPool();
        
        customerThreadPool.createCustomerThreads(customerList);
        
        scanner.close();
    }
}
	
	
