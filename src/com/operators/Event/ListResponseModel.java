/**
 * 
 */
package com.operators.Event;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

/**
 * @author Piyush yadav
 *2 Mar 20172017
 * @ Insight Centre for Data Analytics Galway
 */
public class ListResponseModel extends AbstractListModel {

    private static final long serialVersionUID = 1L;

    private ArrayList<Event_Annotate_Operator> delegate = new ArrayList<Event_Annotate_Operator>();

    
    public int getSize() {
        return delegate.size();
    }


    public Object getElementAt(int index) {
        return delegate.get(index);
    }

    public boolean add(Event_Annotate_Operator e){
        int index = delegate.size();
        delegate.add(e);
        fireIntervalAdded(this, index, index);
        //System.out.println("Value Added");
		return true;
    }
    
    public void delete(Event_Annotate_Operator e){
        int index = delegate.size();
        delegate.remove(index);
        fireIntervalAdded(this, index, index);
        //System.out.println("Value Added");
    }
    
    
    
}