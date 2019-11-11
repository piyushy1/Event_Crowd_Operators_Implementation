/**
 * 
 */
package com.operators.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;

import com.espertech.esper.epl.generated.EsperEPL2GrammarParser.NewAssignContext;

/**
 * @author Piyush yadav
 *2 Mar 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Producer_Events implements Runnable{

	private BlockingQueue<ArrayList<Event_Annotate_Operator>> queue ;
    ArrayList<Event_Annotate_Operator> batch_list = new ArrayList<Event_Annotate_Operator>();
    private volatile boolean running = true;
    public Producer_Events(BlockingQueue<ArrayList<Event_Annotate_Operator>> queue,ArrayList<Event_Annotate_Operator> batch_list) {
        this.queue = queue;
        this.batch_list = batch_list;
     
    }

    public void run() {

        try {
        	// do all the shit
        		
        		ArrayList<Long> tymstamp = new ArrayList<Long>();
        		
        		BufferedReader reader = new BufferedReader(new FileReader(
        				"timestmp_all_4000_new.csv"));

        		String line = "";
        		while ((line = reader.readLine()) != null) {
        			
        			String[] event = line.trim().split(",");
        			float millis = Float.parseFloat(event[12]);
        			long num = (long)millis;
        			tymstamp.add(num);
        		}
        		System.out.println("Timestamp added");
        		for (int m = 0; m<batch_list.size();m++){
        			
        			Event_Annotate_Operator operator = new Event_Annotate_Operator();
        			operator = batch_list.get(m);
        			ArrayList<Event_Annotate_Operator> batch1 = new ArrayList<>();
        			Thread.sleep(tymstamp.get(m));
        			operator.setSimu_eventname(Integer.toString(m));
        			operator.setSimu_generatetym(System.nanoTime()/1000000);
        			batch1.add(operator);
        			if(m< (batch_list.size()-1)){
        				Thread.sleep(150);
        				queue.put(batch1);
        				operator.setQueue_addtym(System.nanoTime()/1000000);
        				System.out.println("Event Added: "+ m);
        			}
        			else{
        				// inject a poison
        				ArrayList<Event_Annotate_Operator> batch2 = new ArrayList<>();
        				Event_Annotate_Operator poison = new Event_Annotate_Operator();
        				poison.setLabel("POISON");
        				batch2.add(poison);
        				queue.put(batch2);
        				System.out.println("Producer STOPPED.");
        				Thread.currentThread().interrupt();
        		        return;
        				//System.out.println("Producer STOPPED.");
        				//break;
        			}
        			
        		}
     
        		
        
       } catch (InterruptedException e) {
    	   Thread.currentThread().interrupt();
    	   System.out.println("Producer11 STOPPED.");
           return;
        } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void stopRunning()
    {
        running = false;
    }
}