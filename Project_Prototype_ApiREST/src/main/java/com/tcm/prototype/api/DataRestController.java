package com.tcm.prototype.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.tcm.prototype.application.DataController;
import com.tcm.prototype.application.dto.DataDTO;
import com.tcm.prototype.utilities.InvalidParamException;
import com.tcm.prototype.utilities.NotFoundException;

@RestController
public class DataRestController {
	
	@Autowired
	private DataController dataController;
	
	@PostMapping("/data")
	public String crateUser(@RequestBody String jData) throws InvalidParamException {
		Gson gson = new Gson();
		
		DataDTO dataDTO = gson.fromJson(jData, DataDTO.class);
		
		DataDTO	 data = dataController.createData(dataDTO);
		
		
		return gson.toJson(data);
		
	}
	@CrossOrigin(origins = "https://alexindris.github.io/Project_Prototype/")
	@GetMapping("/data")
	public String getAllData() throws InvalidParamException, NotFoundException {
		List < DataDTO> alldata = dataController.getAllData();
		Gson gson = new Gson();
		return gson.toJson(alldata);

	}
	@GetMapping("/data/{id}")
	public String getAllData(@PathVariable String id) throws InvalidParamException, NotFoundException {
		 List<DataDTO> data = dataController.getData(id);
		Gson gson = new Gson();
		return gson.toJson(data);

	}
	@DeleteMapping("/data")
	public void deleteAllData() throws InvalidParamException {
		dataController.deleteAllData();
	}
	
	@DeleteMapping("/data/{id}")
	public void deleteData(@PathVariable String id) throws NotFoundException, InvalidParamException {
		dataController.deleteData(id);
	}
	
	
	
	
	

	
}
