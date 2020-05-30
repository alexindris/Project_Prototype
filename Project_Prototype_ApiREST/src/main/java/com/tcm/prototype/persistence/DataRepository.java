package com.tcm.prototype.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.tcm.prototype.domain.Data;
import com.tcm.prototype.utilities.InvalidParamException;
import com.tcm.prototype.utilities.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

@Service
public class DataRepository {
	
	@Autowired
	DataRepositoryCrud crudRepository;
	
	private static HashSet<Data> datainfo=new HashSet<Data>();
	 


	public List<Data> getAllData() {
		
		
		return new ArrayList<Data>(datainfo);
	}


public Data getData(String id) throws NotFoundException {
	for(Data data:datainfo) {
		if(data.getId().equals(id)) return data;
	}
	throw new NotFoundException();
}

public void deleteData(String id) throws NotFoundException {
	
Iterator<Data> it = datainfo.iterator();
while(it.hasNext()) {
	
	Data data = it.next();
	if(data.getId().equals(id)) {
		it.remove();
		return;
		
	}
	
}
throw new NotFoundException();
}

public void deleteAllData() {
	datainfo.clear();
	
}

public void saveData(Data data) throws InvalidParamException {
	if(data==null) throw new InvalidParamException();
	if(!datainfo.add(data))throw new InvalidParamException();
	System.out.println("-------------------------IMPRIMIM DATA---------------------------");
	System.out.println(data.toString());
	if(crudRepository == null) {
		System.out.println("--------DataRepository: crudRepository es null");
	}else{
		crudRepository.save(data);

	}
}

}
