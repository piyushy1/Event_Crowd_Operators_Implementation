/**
 * @author Piyush yadav
 *${date}${year}
 * @ Insight Centre for Data Analytics Galway
 */

package com.operators.Event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

/* this is implementation of ranking operator for CEP and is used in term of extension for Esper operator
 this is general overall implementation of rank method
 it will take any object and add rank_scale and rank with it by creating a new proxy object....
 it will not alter the previous eventbean object and just add to it in new proxy event bean*/

public class Event_Rank_Operator {

	Object rank_event; // object event bean
	ArrayList<Integer> ranks_range = new ArrayList<Integer>(); // rank rang scale......
	int rank; // rank of object given by public
	String rank_instruction;
	Double price;
	Double hit_exp_time;
	String user;
	int event_id;
	String algo_id;
	int worker_id;
	
																

	/* Creating new event object constructor */
	public Event_Rank_Operator(Object rank_event,
			ArrayList<Integer> ranks_range, int rank, String rank_instruction,
			Double price, Double hit_exp_time, String user, int event_id,
			String algo_id, int worker_id) {
		super();
		this.rank_event = rank_event;
		this.ranks_range = ranks_range;
		this.rank = rank;
		this.rank_instruction = rank_instruction;
		this.price = price;
		this.hit_exp_time = hit_exp_time;
		this.user = user;
		this.event_id = event_id;
		this.algo_id = algo_id;
		this.worker_id = worker_id;
	}

	
	
	public Event_Rank_Operator() {
		super();
		// TODO Auto-generated constructor stub
	}


	// generating getters and setters for new event object
	public Object getRank_event() {
		return rank_event;
	}


	public void setRank_event(Object rank_event) {
		this.rank_event = rank_event;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public int getWorker_id() {
		return worker_id;
	}

	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}

	public ArrayList<Integer> getRanks_range() {
		return ranks_range;
	}

	public void setRanks_range(ArrayList<Integer> ranks_range) {
		this.ranks_range = ranks_range;
	}
	
	public String getRank_instruction() {
		return rank_instruction;
	}

	public void setRank_instruction(String rank_instruction) {
		this.rank_instruction = rank_instruction;
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
	
	// creating rank operator method.....

	public static ArrayList<Event_Rank_Operator> computeRank(Object[] event,
			int rank_scale, String inst,Double price1, Double hit_exp_time1,
			String user1, String algo_id1) {

		ArrayList<Integer> ranks_range = new ArrayList<Integer>(); // creating
																	// range......
		ArrayList<Event_Rank_Operator> event_rank_object = new ArrayList<Event_Rank_Operator>();

		try {
			if (rank_scale != 0 || rank_scale != (Integer) null) {

				for (int i = 0; i <= rank_scale; i++) {

					ranks_range.add(i);

				}

				for (Object event1 : event) {
					Random generator1 = new Random();
					Event_Rank_Operator rank_crowd_event = new Event_Rank_Operator();
					rank_crowd_event.setAlgo_id(algo_id1);
					rank_crowd_event.setHit_exp_time(hit_exp_time1);
					rank_crowd_event.setPrice(price1);
					rank_crowd_event.setRanks_range(ranks_range);
					rank_crowd_event.setRank_event(event1);
					rank_crowd_event.setUser(user1);
					rank_crowd_event.setWorker_id(0);
					rank_crowd_event.setRank(0);
					rank_crowd_event.setEvent_id(generator1.nextInt());
					rank_crowd_event.setRank_instruction(inst);
					event_rank_object.add(rank_crowd_event);
					//System.out.println("Rank scale added to each events.....");

				}

			}

		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return event_rank_object;

	}

	public boolean checkstatus(Stock_Event_Rank[] event) {

		if (event.length >= 2) {

			return true;

		}

		return false;
	}

}
