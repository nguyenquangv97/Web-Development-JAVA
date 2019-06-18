******************************

		  Change Log

******************************

From lab 3, I got deducted 4 points for not implementing the choice interface. 

- in this lab, I have included the choice interface inside the adapter package as 
well as the updated ProxyAutomobile and BuildAuto to impelement the Choice interface.
I have also included the Driver to come with its implementation.

******************************

		 Instruction

******************************

In the first Driver, I show the multithreading process.

I pass in the key of the linkedHashMap into the method that starts the thread inside 
ProxyAutomobile. Then inside EditOption, it takes that key and retrieve the corresponding 
data inside the LinkedHashMap. The threads will process that automobile. 

also inside editOption class, I left the operation 3 (inside the switch) not-synchronized!
this is to show the data corruption. 

but for operations 1 and 2, they're both synchronized. I used the Synchronized block in order 
to have the Automobile as the monitor object.

In the second driver, I demonstrate the implementation of the choice interface that I was previously
missing in lab 3. 

------------------------------------

In the test run folder, I have 2 separate text files. One is the test run text file, and the other is the
analysis of those test runs. (syncrhonized vs non-synchronized) 

----------------------------------------------------------------------------------------------

