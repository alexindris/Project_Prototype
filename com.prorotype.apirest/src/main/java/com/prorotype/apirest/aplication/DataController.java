package com.prorotype.apirest.aplication;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.prorotype.apirest.persistence.DataStorage;

import com.prorotype.apirest.domain.*;

@RestController
public class DataController {
	
	DataStorage ds= DataStorage.getInstance();
	@GetMapping("/data")
	public List<Data>index(){
		return ds.fetchData();
	}
	@GetMapping("/data/{id}")
    public Data show(@PathVariable String id){
        int dataId = Integer.parseInt(id);
        return ds.getDataById(dataId);
    }
	
	@PostMapping("/data")
    public Data create(@RequestBody Map<String, String> body){
        int id = Integer.parseInt(body.get("id"));
        String date = body.get("date");
        String time = body.get("time");
        String sensor = body.get("sensor");
        float valor = Float.parseFloat(body.get("valor"));
        
        return ds.addData(id, time, date, sensor, valor);
    }
	 @DeleteMapping("data/{id}")
	    public boolean delete(@PathVariable String id){
	        int dataId = Integer.parseInt(id);
	        return ds.removeData(dataId);
	    }

}
