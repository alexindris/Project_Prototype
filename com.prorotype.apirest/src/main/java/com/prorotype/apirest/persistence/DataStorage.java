package com.prorotype.apirest.persistence;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import com.prorotype.apirest.domain.Data;

public class DataStorage {
	
	
	private HashSet<Data> datainfo;
	 

	private static DataStorage instance = null;
	public static DataStorage getInstance() {
		if(instance == null){
            instance = new DataStorage();
        }
        return instance;
		
	}
	
	public List<Data> fetchData(){
		return new ArrayList<Data>(datainfo);
	}
	
	public List<Data> getDataById(int id) {
		List<Data> out = new ArrayList<Data>();
        for(Data d: datainfo) {
            if(d.getId() == id) {
                out.add(d);
            }
        }
        return out;
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
		
		this.datainfo = new HashSet<Data>();
	}
	
	
}
