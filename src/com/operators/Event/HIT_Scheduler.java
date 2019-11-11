/**
 * 
 */
package com.operators.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Piyush yadav
 *16 Feb 20172017
 * @ Insight Centre for Data Analytics Galway
 */

 public class HIT_Scheduler 
{
	
	
	//ExecutorService service = Executors.newFixedThreadPool(5);
	ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(5);

	ArrayList<Event_Annotate_Operator> arr_events;

	
	// creating constructor for synchronised thread....
    public HIT_Scheduler(ArrayList<Event_Annotate_Operator> arr_events) {
        this.arr_events = arr_events;
    }
    
    
 
/*    // method bloc to send multiple crowd answers bak to scheduer.....
    public ArrayList<Event_Annotate_Operator> call() throws Exception {

    	String[] label = {"Yes","No","OK","Can be"};
    	System.out.println("OK1");
    	ArrayList<Event_Annotate_Operator> each_event = arr_events;
    	for(Event_Annotate_Operator event: each_event ){
    		
    		// give with random time.......we will make the tread sleep in between...
    		int random_tym = ThreadLocalRandom.current().nextInt(min_tym, max_tym + 1);
    		System.out.println("Random Time: " + random_tym);
    		
    		// give random label.....
    		int index = randomGenerator1.nextInt(label.length);
    		System.out.println("random Index: "+ index);
    		
    		// now access the object... set the value.... and make thread sleep for that specific random time...
    		
    		event.setLabel(label[index]);
    		Thread.sleep(random_tym);
    		
    		
    	}

    	return arr_events;

    }*/
    
	public HIT_Scheduler() {
		super();
		// TODO Auto-generated constructor stub
	}



/*	public void Schedule_Batch(ArrayList<Event_Annotate_Operator> batch_list) throws InterruptedException, IOException {
		// get the batch of events.......
		ArrayList<ArrayList<Event_Annotate_Operator>> event_pooler = new ArrayList<>(10);
		ArrayList<ArrayList<Event_Annotate_Operator>> cr_event_pooler = new ArrayList<>();
		System.out.println("Event batch Received:");
		ArrayList<Event_Annotate_Operator> batch = new ArrayList<>();
		ArrayList<Long> tymstamp = new ArrayList<Long>();
		
		BufferedReader reader = new BufferedReader(new FileReader(
				"timestamp.csv"));

		String line = "";
		while ((line = reader.readLine()) != null) {
			
			String[] event = line.trim().split(",");
			float millis = Float.parseFloat(event[0]);
			long num = (long)millis;
			tymstamp.add(num);
		}
		 
		System.out.println("Timestamp added");
		ListResponseModel event_pooler1 = new ListResponseModel();
		for (int m = 0; m<batch_list.size();m++){
			
			Event_Annotate_Operator operator = new Event_Annotate_Operator();
			operator = batch_list.get(m);
			ArrayList<Event_Annotate_Operator> batch1 = new ArrayList<>();
			Thread.sleep(tymstamp.get(m));
			if(event_pooler1.getSize()<10){
				if (event_pooler1.add(operator)== true){
					
					//create thread.....
					// empty the pool
					
				}
				
			}
			else{
				// pooler need to wait 
			}

			batch1.add(operator);
			// read a timestamp file
			
			event_pooler.add(batch1);
			System.out.println("OK");
			
		}

		
		batch.addAll(batch_list);
		
		if(batch.size()!=0){
			
			event_pooler.add(batch);
			Event_Engine.scheduler.clear();
			if(event_pooler.size()!=0){
				ArrayList<Event_Annotate_Operator> batch_event = event_pooler.get(0);
				event_pooler.remove(0);
				
				ConsumerWorker consumer = new ConsumerWorker(inputQueue);
				executor.execute(consumer);
				
				while (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
						CrowdSimulation ans_matrix = new CrowdSimulation();
						cr_event_pooler.addAll(ans_matrix.SimulateCrowd(batch_event));
						executor.shutdown();

				}

			}

		}

	}*/
	
	public void Schedule_Batch1(ArrayList<Event_Annotate_Operator> batch_list) throws InterruptedException{
		
		// create a blocking queue......
		BlockingQueue<ArrayList<Event_Annotate_Operator>> queue = new ArrayBlockingQueue<ArrayList<Event_Annotate_Operator>>(4005);
		ArrayList<ArrayList<Event_Annotate_Operator>> cr_event_pooler = new ArrayList<ArrayList<Event_Annotate_Operator>>();
		Producer_Events producer = new Producer_Events(queue,batch_list);
		ConsumerWorker consumer = new ConsumerWorker(queue);

/*		Thread myThread = new Thread(new ConsumerWorker(queue));
		myThread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			public void uncaughtException(Thread myThread, Throwable e) {
				System.out.println(myThread.getName() + " throws exception: " + e);
			 }
		});
*/
		System.out.println("OK");
		Thread producer1 = new Thread(producer);
		//myThread.start();
		//executor.execute(consumer);
		/*service.submit(producer);
		ConsumerWorker consumer = new ConsumerWorker(queue,cr_event_pooler);
		service.submit(executor.execute(consumer));*/
        //Thread producer1 = new Thread(producer);
		//executor.submit(consumer);
		Thread consumer1 = new Thread(consumer);
        Thread consumer2 = new Thread(consumer);
        Thread consumer3 = new Thread(consumer);
        Thread consumer4 = new Thread(consumer);
        Thread consumer5 = new Thread(consumer);
        Thread consumer6 = new Thread(consumer);
        Thread consumer7 = new Thread(consumer);
        Thread consumer8 = new Thread(consumer);
        Thread consumer9 = new Thread(consumer);
        Thread consumer10 = new Thread(consumer);
        Thread consumer11 = new Thread(consumer);
        Thread consumer12 = new Thread(consumer);
        Thread consumer13 = new Thread(consumer);
        Thread consumer14 = new Thread(consumer);
        Thread consumer15 = new Thread(consumer);
        Thread consumer16 = new Thread(consumer);
        Thread consumer17 = new Thread(consumer);
        Thread consumer18 = new Thread(consumer);
        Thread consumer19 = new Thread(consumer);
        Thread consumer20 = new Thread(consumer);
        Thread consumer21 = new Thread(consumer);
        Thread consumer22 = new Thread(consumer);
        Thread consumer23 = new Thread(consumer);
        Thread consumer24 = new Thread(consumer);
        Thread consumer25 = new Thread(consumer);
        Thread consumer26 = new Thread(consumer);
        Thread consumer27 = new Thread(consumer);
        Thread consumer28 = new Thread(consumer);
        Thread consumer29 = new Thread(consumer);
        Thread consumer30 = new Thread(consumer);
        Thread consumer31 = new Thread(consumer);
        Thread consumer32 = new Thread(consumer);
        Thread consumer33 = new Thread(consumer);
        Thread consumer34 = new Thread(consumer);
        Thread consumer35 = new Thread(consumer);
        Thread consumer36 = new Thread(consumer);
        Thread consumer37 = new Thread(consumer);
        Thread consumer38 = new Thread(consumer);
        Thread consumer39 = new Thread(consumer);
        Thread consumer40 = new Thread(consumer);
        Thread consumer41 = new Thread(consumer);
        Thread consumer42 = new Thread(consumer);
        Thread consumer43 = new Thread(consumer);
        Thread consumer44 = new Thread(consumer);
        Thread consumer45 = new Thread(consumer);
        Thread consumer46 = new Thread(consumer);
        Thread consumer47 = new Thread(consumer);
        Thread consumer48 = new Thread(consumer);
        Thread consumer49 = new Thread(consumer);
        Thread consumer50 = new Thread(consumer);
        Thread consumer51 = new Thread(consumer);
        Thread consumer52 = new Thread(consumer);
        Thread consumer53 = new Thread(consumer);
        Thread consumer54 = new Thread(consumer);
        Thread consumer55 = new Thread(consumer);
        Thread consumer56 = new Thread(consumer);
        Thread consumer57 = new Thread(consumer);
        Thread consumer58 = new Thread(consumer);
        Thread consumer59 = new Thread(consumer);
        Thread consumer60 = new Thread(consumer);
        Thread consumer61 = new Thread(consumer);
        Thread consumer62 = new Thread(consumer);
        Thread consumer63 = new Thread(consumer);
        Thread consumer64 = new Thread(consumer);
        Thread consumer65 = new Thread(consumer);
        Thread consumer66 = new Thread(consumer);
        Thread consumer67 = new Thread(consumer);
        Thread consumer68 = new Thread(consumer);
        Thread consumer69 = new Thread(consumer);
        Thread consumer70 = new Thread(consumer);
        Thread consumer71 = new Thread(consumer);
        Thread consumer72 = new Thread(consumer);
        Thread consumer73 = new Thread(consumer);
        Thread consumer74 = new Thread(consumer);
        Thread consumer75 = new Thread(consumer);
        Thread consumer76 = new Thread(consumer);
        Thread consumer77 = new Thread(consumer);
        Thread consumer78 = new Thread(consumer);
        Thread consumer79 = new Thread(consumer);
        Thread consumer80 = new Thread(consumer);
        Thread consumer81 = new Thread(consumer);
        Thread consumer82 = new Thread(consumer);
        Thread consumer83 = new Thread(consumer);
        Thread consumer84 = new Thread(consumer);
        Thread consumer85 = new Thread(consumer);
        Thread consumer86 = new Thread(consumer);
        Thread consumer87 = new Thread(consumer);
        Thread consumer88 = new Thread(consumer);
        Thread consumer89 = new Thread(consumer);
        Thread consumer90 = new Thread(consumer);
    /*  Thread consumer91 = new Thread(consumer);
        Thread consumer92 = new Thread(consumer);
        Thread consumer93 = new Thread(consumer);
        Thread consumer94 = new Thread(consumer);
        Thread consumer95 = new Thread(consumer);
        Thread consumer96 = new Thread(consumer);
        Thread consumer97 = new Thread(consumer);
        Thread consumer98 = new Thread(consumer);
        Thread consumer99 = new Thread(consumer);
        Thread consumer100 = new Thread(consumer);
     */
       
        producer1.start();
        consumer1.start();
        consumer2.start();
        consumer3.start();
        consumer4.start();
        consumer5.start();
        consumer6.start();
        consumer7.start();
        consumer8.start();
        consumer9.start();
        consumer10.start();
        consumer11.start();
        consumer12.start();
        consumer13.start();
        consumer14.start();
        consumer15.start();
        consumer16.start();
        consumer17.start();
        consumer18.start();
        consumer19.start();
        consumer20.start();
        consumer21.start();
        consumer22.start();
        consumer23.start();
        consumer24.start();
        consumer25.start();
        consumer26.start();
        consumer27.start();
        consumer28.start();
        consumer29.start();
        consumer30.start();
        consumer31.start();
        consumer32.start();
        consumer33.start();
        consumer34.start();
        consumer35.start();
        consumer36.start();
        consumer37.start();
        consumer38.start();
        consumer39.start();
        consumer40.start();
        consumer41.start();
        consumer42.start();
        consumer43.start();
        consumer44.start();
        consumer45.start();
        consumer46.start();
        consumer47.start();
        consumer48.start();
        consumer49.start();
        consumer50.start();
     consumer51.start();
        consumer52.start();
        consumer53.start();
        consumer54.start();
        consumer55.start();
        consumer56.start();
        consumer57.start();
        consumer58.start();
        consumer59.start();
        consumer60.start();
        consumer61.start();
        consumer62.start();
        consumer63.start();
        consumer64.start();
        consumer65.start();
        consumer66.start();
        consumer67.start();
        consumer68.start();
        consumer69.start();
        consumer70.start();
        consumer71.start();
        consumer72.start();
        consumer73.start();
        consumer74.start();
        consumer75.start();
        consumer76.start();
        consumer77.start();
        consumer78.start();
        consumer79.start();
        consumer80.start();
        consumer81.start();
        consumer82.start();
        consumer83.start();
        consumer84.start();
        consumer85.start();
        consumer86.start();
        consumer87.start();
        consumer88.start();
        consumer89.start();
        consumer90.start();
        /*     consumer91.start();
        consumer92.start();
        consumer93.start();
        consumer94.start();
        consumer95.start();
        consumer96.start();
        consumer97.start();
        consumer98.start();
        consumer99.start();
        consumer100.start();*/

        
        //consumer1.interrupt();
        //consumer2.interrupt();
        //consumer3.interrupt();
       // consumer4.interrupt();
        //executor.execute(consumer);
        //executor.shutdown();
		//System.out.println("Producer seriosuly Stopped");
	}
	

	/* (non-Javadoc)
	 * @see java.util.concurrent.Callable#call()
	 */
	public ArrayList<Event_Annotate_Operator> call() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}


