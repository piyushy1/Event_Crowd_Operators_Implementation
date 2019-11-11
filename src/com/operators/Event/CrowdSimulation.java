/**
 * 
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;



/**
 * @author Piyush yadav
 *14 Feb 20172017
 * @ Insight Centre for Data Analytics Galway
 * 
 */

class CrowdSim implements Callable<ArrayList<Event_Annotate_Operator>>
{
	private  Random randomGenerator1 = new Random();
	ArrayList<Event_Annotate_Operator> arr_events;
	int min_tym = 15;
	int max_tym = 25;
	
	// creating constructor for synchronised thread....
    public CrowdSim(ArrayList<Event_Annotate_Operator> arr_events) {
        this.arr_events = arr_events;
    }
 
    // method bloc to send multiple crowd answers bak to scheduer.....
    public ArrayList<Event_Annotate_Operator> call() throws Exception {
    	
    	String[] label = {"Yes","No"};
    	//System.out.println("OK1");
    	ArrayList<Event_Annotate_Operator> each_event = arr_events;
    	//System.out.println("Start time for crowd:" + System.nanoTime()/1000000000);
    	//System.out.println("HIT SIze:" + each_event.size());
    	for(Event_Annotate_Operator event: each_event ){
    		
    		// give with random time.......we will make the tread sleep in between...
    		//int random_tym = ThreadLocalRandom.current().nextInt(min_tym, max_tym + 1);
    		//slow
    		//int randomNum=  50 + (int)(Math.random() * ((75 - 50) + 1));
    		//System.out.println("Random Time: " + random_tym);
    		// med..
    		//int randomNum=  25 + (int)(Math.random() * ((50 - 25) + 1));
    		//fast
    		//int randomNum=  10 + (int)(Math.random() * ((25 - 10) + 1));
    		int randomNum=  10 ;
    		// give random label.....
    		int index = randomGenerator1.nextInt(label.length);
    		//System.out.println("random Index: "+ index);
    		
    		// now access the object... set the value.... and make thread sleep for that specific random time...
    		
    		event.setLabel(label[index]);
    		Thread.sleep((randomNum*1000));
    		
    		
    	}
    	//System.out.println("End time for crowd:" + System.nanoTime()/1000000000);
    	return arr_events;

    }
}



public class CrowdSimulation {

	/**
	 * @param args
	 */
	
	private static Random generator = new Random();
	
	public ArrayList<ArrayList<Event_Annotate_Operator> > SimulateCrowd(ArrayList<Event_Annotate_Operator> batch_list) {
		// get the batch of events.......
		//System.out.println("Size:" +batch_list.size());
		
		//System.out.println("Done");
		
		// do the crowdsourcing......
		System.out.println("Batch Submitted...");
		long startTime = System.nanoTime();
		//System.out.println("Time Started:"+ startTime/1000000000);
		
		ArrayList<ArrayList<Event_Annotate_Operator>> crwd_events =  Crowd_Engine.docrowdsourcing(batch_list,1);
		
/*		 CountDownLatch latch = new CountDownLatch(1);
		 CrowdSim crowd_thread1 = new CrowdSim(crwd_events.get(0));
		 //CrowdSim crowd_thread2 = new CrowdSim(crwd_events.get(1));
		 ArrayList<Event_Annotate_Operator> val = crowd_thread1.start();
		 //ArrayList<Event_Annotate_Operator> val = crowd_thread1.getValue();
		 //crowd_thread2.start();
		 //ArrayList<Event_Annotate_Operator> val2 = crowd_thread2.getValue();
		 latch.countDown();  
		 System.out.println(val);*/
		
		// block to create crowd i.e threadpool
		ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(crwd_events.size());
		ArrayList<Future<ArrayList<Event_Annotate_Operator>>> resultList = new ArrayList<>();
		ArrayList<ArrayList<Event_Annotate_Operator> > crowd_ans = new ArrayList<>();
        
        Random random = new Random();
         
        for (int i=0; i<crwd_events.size(); i++)
        {
        	
        	//System.out.println("Crowd " + i + " HIT Size: "+ crwd_events.get(i).size() );
            CrowdSim crowd_sim  = new CrowdSim(crwd_events.get(i));
            Future<ArrayList<Event_Annotate_Operator>> result = executor.submit(crowd_sim);
            resultList.add(result);
            
        }
        
        for(Future<ArrayList<Event_Annotate_Operator>> future : resultList)
        {
              try
              {
                  //System.out.println("Future result is - " + " - " + future.get() + "; And Task done is " + future.isDone());
                  crowd_ans.add(future.get());
              } 
              catch (InterruptedException | ExecutionException e) 
              {
                  e.printStackTrace();
              }
          }
          //shut down the executor service now
          executor.shutdown();
          
          
  		final long endTime = System.nanoTime();
  		//System.out.println("Time Ended: "+ endTime/1000000000);
  		//System.out.println("Total execution time(sec): " + ((endTime - startTime)/1000000000) );
  		//System.out.println("Program END");
		// adding aggregator tym...
  		for(ArrayList<Event_Annotate_Operator> batchevent: crowd_ans)
  		{
  			for(Event_Annotate_Operator eachevent : batchevent){
  				eachevent.setAggregate_tym(System.nanoTime()/1000000);
  				
  			}
  		}
  		return crowd_ans;

	    }

	

}
