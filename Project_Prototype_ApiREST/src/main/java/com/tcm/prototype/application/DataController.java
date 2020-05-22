package com.tcm.prototype.application;

import java.util.ArrayList;
import java.util.List;

import com.tcm.prototype.application.dto.DataDTO;
import com.tcm.prototype.domain.Data;
import com.tcm.prototype.persistence.DataRepository;
import com.tcm.prototype.utilities.InvalidParamException;
import com.tcm.prototype.utilities.NotFoundException;

public class DataController {

	
	public static List<DataDTO> getAllData() throws InvalidParamException {

		List<Data> allData = DataRepository.getAllData()
;
		return convertDataToDTO(allData);
	}

	private static List<DataDTO> convertDataToDTO(List<Data> allData) throws InvalidParamException {
		
		List<DataDTO> result= new ArrayList<DataDTO>();
		for(Data data:allData) result.add(new DataDTO(data));
		return result;
	}
	
	public static DataDTO getData(String id )throws InvalidParamException, NotFoundException{
		Data data = DataRepository.getData(id);
		return new DataDTO(data);
		
	}
	
	public static void deleteData(String id)throws NotFoundException{
		DataRepository.deleteData(id);
	}
	public static void deleteAllData() {
		DataRepository.deleteAllData();
	}
	
	public static DataDTO createData(DataDTO dataDTO) throws InvalidParamException {
		Data data = new Data(dataDTO);
		DataRepository.saveUser(data);
		return new DataDTO(data);
	}
	
	
}
