package com.prorotype.apirest.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import com.prorotype.apirest.domain.Data;

public class DataStorage {
	
	
	private List<Data> datainfo;
	 

	private static DataStorage instance = null;
	public static DataStorage getInstance() {
		if(instance == null){
            instance = new DataStorage();
        }
        return instance;
		
	}
	
	public List<Data> fetchData(){
		return datainfo;
	}
	
	public Data getDataById(int id) {
        for(Data d: datainfo) {
            if(d.getId() == id) {
                return d;
            }
        }
        return null;
    }
	public boolean removeData(int id){
       Iterator<Data> it = datainfo.iterator();
       while(it.hasNext()) {
    	   Data d = it.next();
    	   if(d.getId()==id) {
    		   it.remove();
    		   return true;
    	   }
    	   
       }
       return false;
    }
	public Data addData(int id, String time, String date, String sensor, float valor) {
		Data d = new Data(id, time, date, sensor, valor);
		datainfo.add(d);
		return d;
		
	}
	
	private DataStorage() {
		
		this.datainfo = new ArrayList<Data>();
	}
	
	
}
