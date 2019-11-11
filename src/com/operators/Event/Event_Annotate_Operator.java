/**
 * this operator is used to annotate the events .
 * It will add list of labels witht the crowd which will be used by crowd for crowd sourcing
 */
package com.operators.Event;

import java.util.ArrayList;
import java.util.Random;

import net.sf.cglib.beans.BeanCopier.Generator;


/**
 * @author Piyush yadav
 *24 Jan 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class Event_Annotate_Operator {
	
	Object event1;
	String[] label_list;
	String label;
	String label_inst;
	Double price;
	Double hit_exp_time;
	String user;
	int event_id;
	String algo_id;
	int worker_id;
	// for simulation..
	String simu_eventname;
	long simu_generatetym;
	long queue_addtym;
	long queue_removetym;
	long aggregate_tym;
	
	// Event annotate operator constructor class
	
	
	
	public Event_Annotate_Operator() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Event_Annotate_Operator(Object event1, String[] label_list,
			String label, String label_inst, Double price, Double hit_exp_time,
			String user, int event_id, String algo_id, int worker_id,
			String simu_eventname, long simu_generatetym, long queue_addtym,
			long queue_removetym, long aggregate_tym) {
		super();
		this.event1 = event1;
		this.label_list = label_list;
		this.label = label;
		this.label_inst = label_inst;
		this.price = price;
		this.hit_exp_time = hit_exp_time;
		this.user = user;
		this.event_id = event_id;
		this.algo_id = algo_id;
		this.worker_id = worker_id;
		this.simu_eventname = simu_eventname;
		this.simu_generatetym = simu_generatetym;
		this.queue_addtym = queue_addtym;
		this.queue_removetym = queue_removetym;
		this.aggregate_tym = aggregate_tym;
	}

	public Object getEvent1() {
		return event1;
	}
	
	public Object[] getEvent() {
		return (Object[]) event1;
	}

	public void setEvent1(Object event1) {
		this.event1 = event1;
	}


	public String getSimu_eventname() {
		return simu_eventname;
	}

	public void setSimu_eventname(String simu_eventname) {
		this.simu_eventname = simu_eventname;
	}

	public long getSimu_generatetym() {
		return simu_generatetym;
	}

	public void setSimu_generatetym(long simu_generatetym) {
		this.simu_generatetym = simu_generatetym;
	}

	public long getQueue_addtym() {
		return queue_addtym;
	}

	public void setQueue_addtym(long queue_addtym) {
		this.queue_addtym = queue_addtym;
	}

	public long getQueue_removetym() {
		return queue_removetym;
	}

	public void setQueue_removetym(long queue_removetym) {
		this.queue_removetym = queue_removetym;
	}

	public long getAggregate_tym() {
		return aggregate_tym;
	}

	public void setAggregate_tym(long aggregate_tym) {
		this.aggregate_tym = aggregate_tym;
	}

	public String[] getLabel_list() {
		return label_list;
	}


	public void setLabel_list(String[] label_list) {
		this.label_list = label_list;
	}


	public String getLabel() {
		return label;
	}


	public void setLabel(String label) {
		this.label = label;
	}


	public String getLabel_inst() {
		return label_inst;
	}


	public void setLabel_inst(String label_inst) {
		this.label_inst = label_inst;
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


	public int getWorker_id() {
		return worker_id;
	}


	public void setWorker_id(int worker_id) {
		this.worker_id = worker_id;
	}

	
	// create label operator.....
	
	public static Event_Annotate_Operator computeLabel(Object lable_event1, String label_list1,
			String label_inst1, Double price1, Double hit_exp_time1,
			String user1, String algo_id1){
		
		// split the string to an array..
		Random generator1 = new Random();
		
		final String[] label_tokens = label_list1.split(",");
		
		Event_Annotate_Operator operator_event = new Event_Annotate_Operator();
		// set the values.....
		operator_event.setEvent1(lable_event1);
		operator_event.setLabel_list(label_tokens);
		operator_event.setLabel_inst(label_inst1);
		operator_event.setPrice(price1);
		operator_event.setHit_exp_time(hit_exp_time1);
		operator_event.setUser(user1);
		operator_event.setEvent_id(generator1.nextInt());
		operator_event.setWorker_id(0);
		operator_event.setLabel("");
		operator_event.setAlgo_id(algo_id1);
		
		return operator_event;
		
	}
	
	
	
}
