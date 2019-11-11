/**
 * the class is used as a match operator where it will
 * takes two function events and match them with output 
 * true or false
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Piyush yadav
 *21 Jan 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Event_Match_Operator {
	
	Object event1;
	Object event2;
	ArrayList<String> match_status;
	String status;
	String match_instructions;
	Double price;
	Double hit_exp_time;
	String user;
	int event_id;
	String algo_id;
	int worker_id;
	
	// creating constructor of fields.....
	public Event_Match_Operator(Object event1, Object event2,
			ArrayList<String> match_status, String status,
			String match_instructions, Double price, Double hit_exp_time,
			String user, int event_id, String algo_id, int worker_id) {
		super();
		this.event1 = event1;
		this.event2 = event2;
		this.match_status = match_status;
		this.status = status;
		this.match_instructions = match_instructions;
		this.price = price;
		this.hit_exp_time = hit_exp_time;
		this.user = user;
		this.event_id = event_id;
		this.algo_id = algo_id;
		this.worker_id = worker_id;
	}
	
	// creating a constructor with super field......
	
	public Event_Match_Operator() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Object getEvent1() {
		return event1;
	}


	public void setEvent1(Object event1) {
		this.event1 = event1;
	}
	public Object getEvent2() {
		return event2;
	}
	public void setEvent2(Object event2) {
		this.event2 = event2;
	}
	public ArrayList<String> isMatch_status() {
		return match_status;
	}
	public void setMatch_status(ArrayList<String> match_status) {
		this.match_status = match_status;
	}
	public String getMatch_instructions() {
		return match_instructions;
	}
	public void setMatch_instructions(String match_instructions) {
		this.match_instructions = match_instructions;
	}
	public int getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ArrayList<String> getMatch_status() {
		return match_status;
	}
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getHit_exp_time() {
		return hit_exp_time;
	}

	public void setHit_exp_time(Double hit_exp_time) {
		this.hit_exp_time = hit_exp_time;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public int getEvent_id() {
		return event_id;
	}

	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}

	public String getAlgo_id() {
		return algo_id;
	}

	public void setAlgo_id(String algo_id) {
		this.algo_id = algo_id;
	}
	
	// creating operator wrapper.....
	
	public static Event_Match_Operator computeMatch(Object event_1, Object event_2, String inst,Double price1, Double hit_exp_time1,
			String user1, String algo_id1){
		Random generator1 = new Random();
		ArrayList<String> status1 = new ArrayList<String>();
		status1.add("True");
		status1.add("False");
		Event_Match_Operator event_match = new Event_Match_Operator();
		event_match.setEvent1(event_1);
		event_match.setEvent2(event_2);
		event_match.setAlgo_id(algo_id1);
		event_match.setEvent_id(generator1.nextInt());
		event_match.setHit_exp_time(hit_exp_time1);
		event_match.setMatch_instructions(inst);
		event_match.setMatch_status(status1);
		event_match.setPrice(price1);
		event_match.setStatus("");
		event_match.setUser(user1);
		event_match.setWorker_id(0);
		
		return event_match;
		
		
	}
	

}
