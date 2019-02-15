# CRM_MULTITHREADING
Atomic Integer, Concurrent HashMap

Create a multi threaded Java application which does the following.
- Accepts two numbers (1 - no. of products, 2 - quantity of those products)
- Threads should be created accordingly. (eg. if user enters 5 products and quantity as 2 for each then totals items available for buy will be 5*2 = 10)
- Each thread is considered as a customer who is allowed to buy one product only.

2. Mode of payment: COD/ Card.
  - Mode of payment for that customer should be random.
  - Transaction by card or COD should also be random.
   - If the transaction fails then the availability count of products should be same       as before.
   - If the transaction is success then the total count of items available should reduce by 1.

3. Writing to file.
  - Transaction fails or succeeds, should be written to separate file.
  - Program should exit once there no items left for sell.
