/**
 * 
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Piyush yadav
 *14 Feb 20172017
 * @ Insight Centre for Data Analytics Galway
 */

public class Crowd_Engine {
	
	private static Random randomGenerator = new Random();
	
	public static ArrayList<ArrayList<Event_Annotate_Operator>> createcrowd(int crowdnum,int min, int max){

		ArrayList<ArrayList<Event_Annotate_Operator>> crowd_pool = new ArrayList<ArrayList<Event_Annotate_Operator>>(crowdnum);
		
		for (int i = 0; i < crowdnum; i++) {
			int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
			//System.out.println("Check the randomness: " +  randomNum);
			ArrayList<Event_Annotate_Operator> list = new ArrayList<Event_Annotate_Operator>();
			for (int k=0;k< randomNum;k++){
				list.add(null);
			}
			crowd_pool.add(list);
		    // Use the list further...
		}
		
		return crowd_pool;
		
	}
	
	public static ArrayList<ArrayList<Event_Annotate_Operator>> docrowdsourcing(ArrayList<Event_Annotate_Operator> batch_events,int crowds){
		
		// firstly create crowd....
		ArrayList<ArrayList<Event_Annotate_Operator>> crowd_pool = createcrowd(crowds,1,1);
		//System.out.println("Crowd ready......");
		// Assign events to crowd....
		ArrayList<ArrayList<Event_Annotate_Operator>> crowd_pool_events = assignevents(crowd_pool, batch_events);
		// create seperate threads for each crowd...
		
		
		
		// simulates events to crowds....
		
		
		
		
		return crowd_pool_events;
		
	}
	
	/**
	 * @param batch_events 
	 * @param crowd_pool 
	 * @return
	 */
	private static ArrayList<ArrayList<Event_Annotate_Operator>> assignevents(ArrayList<ArrayList<Event_Annotate_Operator>> crowd_pool, ArrayList<Event_Annotate_Operator> batch_events) {
				 // create copy of arraylist.......
				 
				// get random events and fill it to the crowd....assign it...
				ArrayList<ArrayList<Event_Annotate_Operator>> eventstocrowd = new ArrayList<ArrayList<Event_Annotate_Operator>>();
				// do a deep copy.......
				ArrayList<Event_Annotate_Operator> copy = new ArrayList<Event_Annotate_Operator>(batch_events.size());

				for (Event_Annotate_Operator foo: batch_events) {
				  copy.add((Event_Annotate_Operator)foo);
				}
				
				for (int i= 0 ; i<crowd_pool.size();i++){
					// assign events to crowd worker...
					ArrayList<Event_Annotate_Operator> getcrowd = crowd_pool.get(i);
					
					for(int j = 0; j< getcrowd.size(); j++){
						if(copy.size()!=0){
						    int index = randomGenerator.nextInt(copy.size());
					        Event_Annotate_Operator randomItem = copy.get(index);
					        getcrowd.set(j, randomItem);
					        copy.remove(index);

						}
						else{
							int index = randomGenerator.nextInt(batch_events.size());
					        Event_Annotate_Operator randomItem = batch_events.get(index);
					        getcrowd.set(j, randomItem);
							
						}
						
						
				    
						
					}
					
					eventstocrowd.add(getcrowd);
				}
				
		return eventstocrowd;
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		
		//System.out.println("OK....");
		
	}

}
