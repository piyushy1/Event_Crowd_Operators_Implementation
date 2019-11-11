/**
 * @author Piyush yadav
 *${date}${year}
 * @ Insight Centre for Data Analytics Galway
 */

package com.operators.Event;

import java.util.ArrayList;

// this is event class basically defined for characteristics of event related to ranking 

public class Stock_Event_Rank {
	
	long stock_event_id;
	String stock_event_source;
	double stock_event_value;
	String stock_event_symbol;
	//int stock_event_Rank;
	//ArrayList<Integer> rank_event_scale;
	public Stock_Event_Rank(long stock_event_id, String stock_event_source,
			double stock_event_value, String stock_event_symbol,
			int stock_event_Rank, ArrayList<Integer> rank_event_scale) {
		super();
		this.stock_event_id = stock_event_id;
		this.stock_event_source = stock_event_source;
		this.stock_event_value = stock_event_value;
		this.stock_event_symbol = stock_event_symbol;
		//this.stock_event_Rank = stock_event_Rank;
		//this.rank_event_scale = rank_event_scale;
	}


	public long getStock_event_id() {
		return stock_event_id;
	}
	public void setStock_event_id(long stock_event_id) {
		this.stock_event_id = stock_event_id;
	}
	public String getStock_event_source() {
		return stock_event_source;
	}
	public void setStock_event_source(String stock_event_source) {
		this.stock_event_source = stock_event_source;
	}
	public double getStock_event_value() {
		return stock_event_value;
	}
	public void setStock_event_value(double stock_event_value) {
		this.stock_event_value = stock_event_value;
	}
	public String getStock_event_symbol() {
		return stock_event_symbol;
	}
	public void setStock_event_symbol(String stock_event_symbol) {
		this.stock_event_symbol = stock_event_symbol;
	}


}
