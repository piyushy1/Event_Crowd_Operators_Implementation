/**
 * 
 */
package com.operators.Event;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @author Piyush yadav
 *2 Mar 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class ConsumerWorker implements Runnable {

	private BlockingQueue<ArrayList<Event_Annotate_Operator>> inputQueue;
	
	//private final static ArrayList<Event_Annotate_Operator> POISON = new ArrayList<Event_Annotate_Operator>(-1);
	private String threadId;
	public ConsumerWorker(BlockingQueue<ArrayList<Event_Annotate_Operator>> inputQueue) {
		this.inputQueue = inputQueue;
		// this.cr_event_pooler = cr_event_pooler;
	}

	@Override
	public void run() {
		// worker loop keeps taking en element from the queue as long as the
		// producer is still running or as
		// long as the queue is not empty:
		//System.out.println("Check loop");
			//threadId = "Consumer-" + Thread.currentThread().getId();
			while (true) {
			System.out.println("Consumer " + Thread.currentThread().getName()
					+ " START");
			try {
				
				ArrayList<Event_Annotate_Operator> queueElement = new ArrayList<>();
				queueElement= inputQueue.take();
				//System.out.println("Event Send to Crowd: and quesize is "+ queueElement.size());
				//if (queueElement.get(0)!= null){
					String test = queueElement.get(0).getLabel();
					queueElement.get(0).setQueue_removetym(System.nanoTime()/1000000);
					if (test.equalsIgnoreCase("POISON") ) {
						System.out.println("GOT POISON");
						Thread.currentThread().interrupt();
        		        return;

					} 
					System.out.println("Do SImulation");
					CrowdSimulation ans_matrix = new CrowdSimulation();
					Event_Engine.cr_event_pooler.addAll(ans_matrix
							.SimulateCrowd(queueElement));
					System.out.println("Poolersize: "+ Event_Engine.cr_event_pooler.size() );
					Thread.sleep(1000);
					if (Event_Engine.cr_event_pooler.size()== 3999){
						BufferedWriter writer = null;
						
				  		for(ArrayList<Event_Annotate_Operator> batchevent: Event_Engine.cr_event_pooler)
				  		{
				  			for(Event_Annotate_Operator eachevent : batchevent){
				  				StringBuilder tym = new StringBuilder();
				  				tym.append(eachevent.getSimu_eventname());
				  				tym.append(",");
				  				tym.append(eachevent.getSimu_generatetym());
				  				tym.append(",");
				  				tym.append(eachevent.getQueue_addtym());
				  				tym.append(",");
				  				tym.append(eachevent.getQueue_removetym());
				  				tym.append(",");
				  				tym.append(eachevent.getAggregate_tym());
				  				String tymstmp = tym.toString();
				  				try
				  				{
				  				    writer = new BufferedWriter( new FileWriter( "C:\\Users\\piyush\\Desktop\\final Experiments\\arr_100_c90.csv",true));
				  				    writer.write( tymstmp);
				  				    writer.newLine();

				  				}
				  				catch ( IOException e)
				  				{
				  				}
				  				finally
				  				{
				  				    try
				  				    {
				  				        if ( writer != null)
				  				        writer.close( );
				  				    }
				  				    catch ( Exception e)
				  				    {
				  				    }
				  				}
								
				  				
				  			}
				  		}
						
					}
					//consume(10);
					
				//}

				System.out.println("Consumer " + Thread.currentThread().getName()
						 + " STOPPED.");
				 
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			}
	        //System.out.println("Throwing exception ...");
	        //throw new RuntimeException();
		
	}

	// this is used to signal from the main thread that he producer has finished
	// adding stuff to the queue
/*	public void stopRunning() {
		try {
			//inputQueue.put(POISON);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	private void consume(Integer number) {
		 
        System.out.println(threadId + ": Consuming number <= " + number);
 
    }
}