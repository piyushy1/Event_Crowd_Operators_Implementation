/**
 * 
 */
package com.operators.Event;

/**
 * @author Piyush yadav
 *26 Jan 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Resident_Status_Event {
	
	int event_id;
	float lat;
	float longi;
	String map_url;
	float time_stamp;
	public Resident_Status_Event(int event_id, float lat, float longi,
			String map_url,float time_stamp) {
		super();
		this.event_id = event_id;
		this.lat = lat;
		this.longi = longi;
		this.map_url = map_url;
		this.time_stamp = time_stamp;
	}
	
	
	public Resident_Status_Event() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public float getLat() {
		return lat;
	}
	public void setLat(float lat) {
		this.lat = lat;
	}
	public float getLongi() {
		return longi;
	}
	
	public float getTime_stamp() {
		return time_stamp;
	}


	public void setTime_stamp(float time_stamp) {
		this.time_stamp = time_stamp;
	}


	public void setLongi(float longi) {
		this.longi = longi;
	}
	public String getMap_url() {
		return map_url;
	}
	public void setMap_url(String map_url) {
		this.map_url = map_url;
	}
	

}
