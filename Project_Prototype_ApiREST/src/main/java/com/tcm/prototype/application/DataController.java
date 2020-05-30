package com.tcm.prototype.application;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import com.tcm.prototype.application.dto.DataDTO;
import com.tcm.prototype.domain.Data;
import com.tcm.prototype.persistence.DataRepository;
import com.tcm.prototype.utilities.InvalidParamException;
import com.tcm.prototype.utilities.NotFoundException;

@Controller
public class DataController {
	
	@Autowired
	private DataRepository dataRepo;
	 
	
	public List<DataDTO> getAllData() throws InvalidParamException {

		List<Data> allData = dataRepo.getAllData();
		return convertDataToDTO(allData);
	}

	private List<DataDTO> convertDataToDTO(List<Data> allData) throws InvalidParamException {
		
		List<DataDTO> result= new ArrayList<DataDTO>();
		for(Data data:allData) result.add(new DataDTO(data));
		return result;
	}
	
	public DataDTO getData(String id )throws InvalidParamException, NotFoundException{
		Data data = dataRepo.getData(id);
		return new DataDTO(data);
		
	}
	
	public void deleteData(String id)throws NotFoundException{
		dataRepo.deleteData(id);
	}
	public void deleteAllData() {
		dataRepo.deleteAllData();
	}
	
	public DataDTO createData(DataDTO dataDTO) throws InvalidParamException {
		Data data = new Data(dataDTO);
		if(dataRepo == null) {
			System.out.println("--------DataController: dataRepo es null");
		}else {
			dataRepo.saveData(data);
		}
		
		
		//DataRepositoryCrud.save(data);
		return new DataDTO(data);
	}
	
	
}
