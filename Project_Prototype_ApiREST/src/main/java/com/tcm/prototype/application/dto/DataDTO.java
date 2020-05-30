package com.tcm.prototype.application.dto;

import com.tcm.prototype.domain.Data;
import com.tcm.prototype.utilities.InvalidParamException;

public class DataDTO {
	
	private String id;
	private String time;
	private String date;
	private String sensor;
	private String value;

	public DataDTO() {

	}
	public DataDTO(Data data) throws InvalidParamException {
		if(data==null) throw new InvalidParamException();
		this.id=data.getId();
		this.time=data.getTime();
		this.date=data.getDate();
		this.sensor=data.getSensor();
		this.value=data.getValue();

	}
	public String getId() throws InvalidParamException {
		if (id.equals(""))
			throw new InvalidParamException();
		return id;
	}
	public String getTime() throws InvalidParamException {
		if (time.equals(""))
			throw new InvalidParamException();
		return time;
	}
	public String getDate() throws InvalidParamException {
		if (date.equals(""))
			throw new InvalidParamException();
		return date;
	}
	public String getSensor() throws InvalidParamException {
		if (sensor.equals(""))
			throw new InvalidParamException();
		return sensor;
	}
	public String getValue() throws InvalidParamException {
		if (value.equals(""))
			throw new InvalidParamException();
		return value;
	}
	

}
