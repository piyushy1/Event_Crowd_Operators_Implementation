/**
 * this operator is to rate the events as per the given score by the crowd.
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Piyush yadav
 *24 Jan 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Event_Rate_Operator {
	
	Object event; // object event bean
	ArrayList<Integer> rating_scale = new ArrayList<Integer>(); // rank rang scale.....
	String rating; // rating of object given by public
	String rating_instruction;
	Double price;
	Double hit_exp_time;
	String user;
	int event_id;
	String algo_id;
	int worker_id;

	// generate constructors......
	public Event_Rate_Operator(Object event, ArrayList<Integer> rating_scale,
			String rating, String rating_instruction, Double price,
			Double hit_exp_time, String user, int event_id, String algo_id,
			int worker_id) {
		super();
		this.event = event;
		this.rating_scale = rating_scale;
		this.rating = rating;
		this.rating_instruction = rating_instruction;
		this.price = price;
		this.hit_exp_time = hit_exp_time;
		this.user = user;
		this.event_id = event_id;
		this.algo_id = algo_id;
		this.worker_id = worker_id;
	}
	
	// generating a super constructor
	
	public Event_Rate_Operator() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public Object getEvent() {
		return event;
	}

	public void setEvent(Object event) {
		this.event = event;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getRating_instruction() {
		return rating_instruction;
	}
	public void setRating_instruction(String rating_instruction) {
		this.rating_instruction = rating_instruction;
	}
	public ArrayList<Integer> getRating_scale() {
		return rating_scale;
	}
	public void setRating_scale(ArrayList<Integer> rating_scale) {
		this.rating_scale = rating_scale;
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

	public static Event_Rate_Operator computeRating(Object event1, int rating_scale ,String rating_inst,Double price1, Double hit_exp_time1,
			String user1, String algo_id1){
		
		ArrayList<Integer> rating_range = new ArrayList<Integer>();
		
		if (rating_scale != 0 || rating_scale != (Integer) null) {

			for (int i = 0; i <= rating_scale; i++) {

				rating_range.add(i);
			}

		
	}
		Random generator1 = new Random();
		Event_Rate_Operator rate_event = new Event_Rate_Operator();
		rate_event.setAlgo_id(algo_id1);
		rate_event.setEvent(event1);
		rate_event.setEvent_id(generator1.nextInt());
		rate_event.setHit_exp_time(hit_exp_time1);
		rate_event.setPrice(price1);
		rate_event.setRating("");
		rate_event.setRating_instruction(rating_inst);
		rate_event.setRating_scale(rating_range);
		rate_event.setUser(user1);
		rate_event.setWorker_id(0);
		return rate_event;
		
	

	}}
