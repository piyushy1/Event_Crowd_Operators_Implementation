/**
 * This operator is used to verify certain events . whether they are true or false.
 * 
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Piyush yadav
 *22 Jan 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Event_Verify_Operator {
	
	Object event;
	ArrayList<String> verification_list;
	String verification_status;
	String verification_instructions;
	Double price;
	Double hit_exp_time;
	String user;
	int event_id;
	String algo_id;
	int worker_id;

	public Event_Verify_Operator(Object event,
			ArrayList<String> verification_list, String verification_status,
			String verification_instructions, Double price,
			Double hit_exp_time, String user, int event_id, String algo_id,
			int worker_id) {
		super();
		this.event = event;
		this.verification_list = verification_list;
		this.verification_status = verification_status;
		this.verification_instructions = verification_instructions;
		this.price = price;
		this.hit_exp_time = hit_exp_time;
		this.user = user;
		this.event_id = event_id;
		this.algo_id = algo_id;
		this.worker_id = worker_id;
	}
	
	public Event_Verify_Operator() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Object getEvent() {
		return event;
	}
	public void setEvent(Object event) {
		this.event = event;
	}
	public String getVerification_status() {
		return verification_status;
	}
	public void setVerification_status(String verification_status) {
		this.verification_status = verification_status;
	}
	public String getVerification_instructions() {
		return verification_instructions;
	}
	public void setVerification_instructions(String verification_instructions) {
		this.verification_instructions = verification_instructions;
	}
	public ArrayList<String> getVerification_list() {
		return verification_list;
	}
	public void setVerification_list(ArrayList<String> verification_list) {
		this.verification_list = verification_list;
	}
	public int getWorker_id() {
		return worker_id;
	}
	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
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
		
	// generate verify method........

	public static Event_Verify_Operator computeVerify(Object event1, String inst,Double price1, Double hit_exp_time1,
			String user1, String algo_id1 ){
		Random generator1 = new Random();
		ArrayList<String> status1 = new ArrayList<String>();
		status1.add("True");
		status1.add("False");
		Event_Verify_Operator verify_event = new Event_Verify_Operator();
		verify_event.setAlgo_id(algo_id1);
		verify_event.setEvent(event1);
		verify_event.setEvent_id(generator1.nextInt());
		verify_event.setHit_exp_time(hit_exp_time1);
		verify_event.setPrice(price1);
		verify_event.setUser(user1);
		verify_event.setVerification_instructions(inst);
		verify_event.setVerification_list(status1);
		verify_event.setVerification_status("");
		verify_event.setWorker_id(0);
		return verify_event ;
			
		
	}
	

}
