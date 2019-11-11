/**
 * @author Piyush yadav
 *${date}${year}
 * @ Insight Centre for Data Analytics Galway
 */
package com.operators.Event;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Scanner;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;
import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;
import com.espertech.esper.epl.generated.EsperEPL2GrammarParser.NewAssignContext;
import com.espertech.esper.event.map.MapEventBean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/*This is the main event processing file for crowd event operators*/

public class Event_Engine {

	private static Random generator = new Random();
	static String input1;
	static String input2;
	static float input3;
	static float input4;
	static String input5;
	static String input6;
	static long startTime;
	static ArrayList<ArrayList<Event_Annotate_Operator>> cr_event_pooler = new ArrayList<ArrayList<Event_Annotate_Operator>>();
	static ArrayList<Event_Annotate_Operator> crowd_pooler = new ArrayList<Event_Annotate_Operator>();
	static ArrayList<Long> avg_pool = new ArrayList<Long>();
	public static ArrayList<Event_Annotate_Operator> scheduler = new ArrayList<>();
	
	static ArrayList<Event_Annotate_Operator> list_events = new ArrayList<Event_Annotate_Operator>();

	public static class CEPListener implements UpdateListener {
		public void update(EventBean[] newData, EventBean[] oldData) {
			// put some condition here before sending to crowd.......
			// create arraylist of event annotate operator so that when window of events come.. so then
			// it can be send directly to HIT manager......
			//ArrayList<Event_Annotate_Operator> label_events = new ArrayList<Event_Annotate_Operator>();
			//System.out.println("Event Recieved: " + newData[0].getUnderlying());
			ArrayList<Event_Annotate_Operator> label_events = new ArrayList<Event_Annotate_Operator>();
		for (EventBean eb : newData) {
				if (eb instanceof MapEventBean) {
					// MapEvent event = null;
					if (eb.getUnderlying() instanceof Map<?, ?>) {
						Map<?, ?> alert = (Map<?, ?>) eb.getUnderlying();
						for (Entry<?, ?> entry : alert.entrySet()) {
							/*System.out.println("Event received: "
									+ entry.getValue());*/
							//label_events.add((Event_Annotate_Operator) entry.getValue());
							try {
								HIT_Scheduler((Event_Annotate_Operator) entry.getValue());
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

					}

				}
			}

			//System.out.println("Sending event to HIT Manager.........");
			//System.out.println("Sending event to Crowd.........");
		//HIT_Manager(label_events);
		
		// crowd source....	
		//CrowdSimulation ans_matrix = new CrowdSimulation();
		//ans_matrix.SimulateCrowd(label_events);
		}



	}

	public static String generateRandomWords(int numberOfWords) {
		String[] randomStrings = new String[numberOfWords];
		Random random = new Random();
		for (int i = 0; i < numberOfWords; i++) {
			char[] word = new char[random.nextInt(8) + 3]; // words of length 3
															// through 10. (1
															// and 2 letter
															// words are
															// boring.)
			for (int j = 0; j < word.length; j++) {
				word[j] = (char) ('a' + random.nextInt(26));
			}
			randomStrings[i] = new String(word);
		}
		return randomStrings[1];
	}

	public static void GenerateRandomEvent(EPRuntime cepRT) {

		long id = (long) generator.nextLong();
		String source = "NSE";
		double value = (double) generator.nextInt(10);
		String symbol = generateRandomWords(4);
		int event_Rank = 0;
		ArrayList<Integer> rank_scale = null;
		Stock_Event_Rank event1 = new Stock_Event_Rank(id, source, value,
				symbol, event_Rank, rank_scale);
		System.out.println("Sending tick:" + event1);
		cepRT.sendEvent(event1);
	}

	public static void main(String[] args) throws IOException, InterruptedException {

		System.out.println("******************************************");
		System.out.println("Welcome to Event Crowd Operator Demo!!!!!!");
		System.out.println("******************************************");
		System.out.println("Enter number for Operator Selection:");
		System.out.println("1: Annotation ");
		System.out.println("2: Rank ");
		System.out.println("3: Rate ");
		System.out.println("4: Match ");
		System.out.println("5: Verify ");
		// Take the input from the user
		Scanner reader = new Scanner(System.in); // Reading from System.in
		System.out.println("Enter Operator number: ");
		int op_num = reader.nextInt();
		if (op_num == 1) {

			System.out.println("******************************************");
			System.out.println("Annotation operator selected!!");
			System.out.println("******************************************");
			System.out
					.println("This operator labels the event and take two inputs for operators rule and four inputs for crowd rule :");
			
			System.out.println("******************************************");
			
			Scanner reader1 = new Scanner(System.in); // Reading from System.in
			System.out
					.println("Input1: (Enter the list of labels using comma(,) as delimiter): ");
			input1 = reader1.nextLine();
			Scanner reader2 = new Scanner(System.in); // Reading from System.in
			System.out.println("Input2: Enter you instructions which you want to suggest to crowd during selection ");
			input2 = reader2.nextLine();
			Scanner reader3 = new Scanner(System.in); // Reading from System.in
			System.out.println("Input3: Enter price for crowd ($): ");
			input3 = reader3.nextFloat();
			Scanner reader4 = new Scanner(System.in); // Reading from System.in
			System.out.println("Input4: Enter HIT expiration time (sec): ");
			input4 = reader4.nextFloat();
			Scanner reader5 = new Scanner(System.in); // Reading from System.in
			System.out.println("Input5: Enter User info:  ");
			input5 = reader5.nextLine();
			Scanner reader6 = new Scanner(System.in); // Reading from System.in
			System.out.println("Input7: Enter Aggregation Algo Code : ");
			input6 = reader6.nextLine();


		} else if (op_num == 2) {
			System.out.println("Ranking Direct Demo");
		} else if (op_num == 3) {
			System.out.println("Rating Direct Demo");
		} else if (op_num == 4) {
			System.out.println("Matching Direct Demo");

		} else if (op_num == 5) {
			System.out.println("Verifying Direct Demo");
		} else {

			System.out.println("Wrong Selection.......");
			System.exit(0);
		}

		System.out.println("******************************************");
		System.out.println("Configuring Esper Engine......");
		System.out.println("******************************************");
		
		// start time for program execution
		startTime = System.nanoTime();
		// Configure the event engine by initializing one time object
		Configuration cepConfig = new Configuration();
		File resource = new File("configuation.xml");
		cepConfig.configure(resource);
		cepConfig.addEventTypeAutoName("com.operators.Event");
		// Create Engine Instance
		EPServiceProvider cep = EPServiceProviderManager.getProvider(
				"myCEPEngine", cepConfig);

		EPRuntime cepRT = cep.getEPRuntime();

		// We register an EPL statement
		EPAdministrator cepAdm = cep.getEPAdministrator();
		// EPStatement cepStatement =
		// cepAdm.createEPL("select percent(21,53) from MyEvent");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select * from MyEvent(percent(me)< 20) as me");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select * from MyEvent(percent(me)in [40:20]) as me"
		// );
		// EPStatement cepStatement =
		// cepAdm.createEPL("select * from MyEvent as me where percent(me) >=20 and percent(me)<=50");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select rank_event_scale from Stock_Event_Rank(rank(me)<3) as me");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select rank(window(*),5,'rank as per value') from Stock_Event_Rank.win:length(3)");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select match(window(*),window(*),'rank as per value') from Stock_Event_Rank.win:length(1)");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select verify(window(*),'rank as per value') from Stock_Event_Rank.win:length(1)");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select labels(window(*),'ok,ok2,ok3','Label as per priority') from Stock_Event_Rank.win:length(2)");
		// EPStatement cepStatement =
		// cepAdm.createEPL("select rate(window(*),'rate as per priority',5) from Stock_Event_Rank.win:length(2)");

		if (op_num == 1) {
						
			// create EPL statement.....
			EPStatement cepStatement = cepAdm
					.createEPL("select labels(window(*),'" + input1 + "','"+ input2+ "',"+input3+","+input4+",'"+input5+"','"+input6+"') from Resident_Status_Event.win:length(2)");
			cepStatement.addListener(new CEPListener());

			GenerateEvent(cepRT); // generate event......
		
			long sum = 0;
			for(long d : avg_pool){
				sum += d;
			}
			System.out.println("Average Run: " + sum/avg_pool.size());    


		}
		if (op_num == 2) {
						
			// create EPL statement.....
			Double exp_time = (double) 45;
			int rank_scale = 5;
			EPStatement cepStatement = cepAdm
					.createEPL("select rank(window(*)," + rank_scale + ",'"+ "Rank as per accessibility index between 1 and 5"+ "',"+0.002+","+exp_time+",'"+"Piyush"+"','"+"MD"+"') from Resident_Status_Event.win:length(5)");
			cepStatement.addListener(new CEPListener());

			GenerateEvent(cepRT); // generate event......
			
			long sum = 0;
			for(long d : avg_pool){
				sum += d;
			}
			System.out.println("Average Run: " + sum/avg_pool.size());   

		}
		if (op_num == 3) {
			
			// create EPL statement.....
			Double exp_time = (double) 45;
			EPStatement cepStatement = cepAdm
					.createEPL("select rate(window(*)," + 5 + ",'"+ "Rate the event between score 1-5"+ "',"+0.002+","+exp_time+",'"+"Piyush"+"','"+"MD"+"') from Resident_Status_Event.win:length(2)");
			cepStatement.addListener(new CEPListener());
			for(int i=0;i< 100;i++)
			{
			GenerateEvent(cepRT); // generate event......
			}
			long sum = 0;
			for(long d : avg_pool){
				sum += d;
			}
			System.out.println("Average Run: " + sum/avg_pool.size());   

		}
		if (op_num == 4) {
			
			// create EPL statement.....
			Double exp_time = (double) 45;
			EPStatement cepStatement = cepAdm
					.createEPL("select match(window(*),window(*),'" + "Is these two images are same" + "',"+0.002+ ","+exp_time+","+"'Piyush'"+",'"+"MD"+"') from Resident_Status_Event.win:length(1)");
			cepStatement.addListener(new CEPListener());
			for(int i=0;i< 100;i++)
			{
			GenerateEvent(cepRT); // generate event......
			}
			long sum = 0;
			for(long d : avg_pool){
				sum += d;
			}
			System.out.println("Average Run: " + sum/avg_pool.size());   

		}
		if (op_num == 5) {
			
			// create EPL statement.....
			Double exp_time = (double) 45;
			EPStatement cepStatement = cepAdm
					.createEPL("select verify(window(*),'" + "Verify the statement is positive" + "',"+ 0.002+ ","+exp_time+","+"'Piyush'"+",'"+"MD"+"') from Resident_Status_Event.win:length(2)");
			cepStatement.addListener(new CEPListener());
			for(int i=0;i<100;i++)
			{
			GenerateEvent(cepRT); // generate event......
			}
			long sum = 0;
			for(long d : avg_pool){
				sum += d;
			}
			System.out.println("Average Run: " + sum/avg_pool.size());   

		}

	}

	/**
	 * @param cepRT
	 * @throws IOException
	 * function to generate random event....
	 * @throws InterruptedException 
	 */
	private static void GenerateEvent(EPRuntime cepRT) throws IOException, InterruptedException {

		Resident_Status_Event resident_event = new Resident_Status_Event();

		BufferedReader reader = new BufferedReader(new FileReader(
				"database_4000.csv"));

		String line = "";
		while ((line = reader.readLine()) != null) {
			
			String[] event = line.trim().split(",");
			//float millis = Float.parseFloat(event[7]);
			resident_event.setEvent_id(Integer.parseInt(event[0]));
			resident_event.setLat(Float.parseFloat(event[3]));
			resident_event.setLongi(Float.parseFloat(event[4]));
			resident_event.setMap_url(event[5]+","+event[6]);
			//resident_event.setTime_stamp(millis);
			// sending event
			cepRT.sendEvent(resident_event);
			

		}
		
		final long endTime = System.nanoTime();
		//System.out.println("Total execution time: " + ((endTime - startTime)/1000000) );
		avg_pool.add((endTime - startTime)/1000000);
		//System.out.println("Program END");

	}

	/**
	 * @param list_events2
	 */
	private static void createmap(
			ArrayList<Event_Annotate_Operator> list_events2) {

		File file = new File("C:\\Users\\piyush\\Desktop\\map.html");
		/*
		 * if (file.exists()) { System.out.println("File already present...");
		 * file.delete();
		 * 
		 * }
		 *//* else { */

		FileWriter fileWriter = null;
		BufferedWriter bufferedWriter = null;
		try {

			fileWriter = new FileWriter(file);
			bufferedWriter = new BufferedWriter(fileWriter);
			// String htmlPage =
			// "<html><body style=’background-color:#ccc’><b><h3><center><u>Mobile Phones</u></center></h3></b>"
			// ;
			String htmlPage = "";
			bufferedWriter.write(htmlPage);
			// bufferedWriter.append("<strong><h4>Mobile Phone Companies</h4></strong><ul><li>Apple</li><li>Samsung</li><li>Nokia</li></ul></body></html>");

			System.out.println("Html page created");
			bufferedWriter.flush();
			fileWriter.flush();

		} catch (Exception e) {
			// TODO: handle exception
		}

		/* } */

	}
	
	/**
	 * @param this function takes list of events and create HIT of the same.....
	 * @throws IOException 
	 */
/*	private static void HIT_Manager(ArrayList<Event_Annotate_Operator> label_events) throws IOException {
		
		StringBuilder htmlBuilder = new StringBuilder();
		htmlBuilder.append("<html>");
		htmlBuilder.append("<head><title>HIT EVENT_ CAT_ LABEL</title></head>");
		htmlBuilder.append("<body>");
		BufferedWriter writer = null;
		   
		for (Event_Annotate_Operator obj : label_events){
		
		//Resident_Status_Event[] obj_event= (Resident_Status_Event[]) obj.getEvent1(); 
		htmlBuilder.append("<p><b>User:</b>"+obj.getUser()+"</p>");
		htmlBuilder.append("<p><b>Price:</b>"+ obj.getPrice()+"</p>");
		htmlBuilder.append("<p><b>HIT Expiration Time:</b>"+ obj.getHit_exp_time()+"</p>");
		htmlBuilder.append("<p><b>Label Instruction:</b>"+ obj.getLabel_inst()+"</p>");
		htmlBuilder.append("<p><b>Label List:</b>"+ Arrays.toString(obj.getLabel_list())+"</p>");
		htmlBuilder.append("<table border = "+""+5+""+"style="+"height: 21px;"+"width="+"500"+"><tbody><tr><td>Event ID</td><td>Lat</td><td>Long</td><td>Image URL</td></tr><tr>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("<tr>");
		htmlBuilder.append("<td>"+obj_event[0].getEvent_id() +"</td>");
		htmlBuilder.append("<td>"+obj_event[0].getLat() +"</td>");
		htmlBuilder.append("<td>"+obj_event[0].getLongi()+"</td>");
		htmlBuilder.append("<td><a href ="+obj_event[0].getMap_url()+"\">Image</a></td>");
		System.out.println("<td><a href ="+obj_event[0].getMap_url()+"\"></a></td>");
		htmlBuilder.append("</tr>");
		htmlBuilder.append("</tbody>");
		htmlBuilder.append("</table>");
		htmlBuilder.append("<form>");
		htmlBuilder.append("Enter Label: <input type="+"text"+"<br>");
		htmlBuilder.append("</form>");
		htmlBuilder.append("</html>");
		String html = htmlBuilder.toString();
		
		try
		{
		    writer = new BufferedWriter( new FileWriter( "C:\\Users\\piyush\\Desktop\\test.html",true));
		    writer.write( html);

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
		    catch ( IOException e)
		    {
		    }
		}
		
	}
		File htmlFile = new File("C:\\Users\\piyush\\Desktop\\test.html");
		Desktop.getDesktop().browse(htmlFile.toURI());
	}*/
	
	/**
	 * @param label_events
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	
	
	private static void HIT_Scheduler(Event_Annotate_Operator label_events) throws InterruptedException, IOException {
		// add events to the scheduler.....
		// create a thread.....which executes....when th array list is full of certain no. events...
		scheduler.add(label_events);
		if (scheduler.size()==4000){
			
			HIT_Scheduler get_crowd_ans = new HIT_Scheduler();
			get_crowd_ans.Schedule_Batch1(scheduler);
			
		}		
	}

	public static void docrowdsourcing(Resident_Status_Event event_crowd)
			throws IOException {

		// read the database and set the file..object.....

		// Object event1 = event_crowd[0];
		// event1.
		String[] label_tokens = input1.split(",");
		Event_Annotate_Operator annotate_event = new Event_Annotate_Operator();
		BufferedReader reader = new BufferedReader(new FileReader(
				"event_274.csv"));
		String line = "";
		while ((line = reader.readLine()) != null) {

			String[] event3 = line.trim().split(",");
			if (Integer.parseInt(event3[0]) == event_crowd.getEvent_id()) {
				annotate_event.setEvent1(event_crowd);
				annotate_event.setWorker_id(0);
				annotate_event.setLabel_list(label_tokens);
				if (event3[1].equalsIgnoreCase("no") == true) {
					annotate_event.setLabel("10");
				} else if (event3[1].equalsIgnoreCase("yes") == true
						& event3[2].equalsIgnoreCase("yes") == true) {
					annotate_event.setLabel("300");
				} else if (event3[1].equalsIgnoreCase("yes") == true
						& event3[2].equalsIgnoreCase("yes") == false) {
					annotate_event.setLabel("100");
				} else {
					annotate_event.setLabel("0");
				}
				annotate_event.setLabel_inst(input2);

			}
			list_events.add(annotate_event);
			break;

		}

	}

}
