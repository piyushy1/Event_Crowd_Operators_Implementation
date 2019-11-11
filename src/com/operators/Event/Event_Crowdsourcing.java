/**
 * @author Piyush yadav
 *${date}${year}
 * @ Insight Centre for Data Analytics Galway
 */

package com.operators.Event;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Event_Crowdsourcing {
	
	
	public static void docrowdsourcing(Object[] event_crowd) throws IOException{
		
		// read the dabase and set the file..object.....
		
		//Object event1 = event_crowd[0];
		//event1.
		BufferedReader reader =new BufferedReader(new FileReader("database1.csv"));

        String line = "";
        while((line=reader.readLine())!=null){
        	
        	String [] event =line.trim().split(",");
        	/*if (Integer.parseInt(event[0])== ){
        		
        	}*/
        	
        	
		
	}
	

}
}