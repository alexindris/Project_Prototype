package com.tcm.prototype.domain;

import com.tcm.prototype.application.dto.DataDTO;
import com.tcm.prototype.utilities.InvalidParamException;

public class Data {

	private String id;
	private String time;
	private String date;
	private String sensor;
	private String value;
	
	public Data() {
		
	}
	
	public Data(String id, String time, String date, String sensor, String value) throws InvalidParamException {
		if (id.equals(""))
			throw new InvalidParamException();
		if (time.equals(""))
			throw new InvalidParamException();
		if (date.equals(""))
			throw new InvalidParamException();
		if (sensor.equals(""))
			throw new InvalidParamException();
		if (value.equals(""))
			throw new InvalidParamException();
		this.id = id;
		this.time = time;
		this.date = date;
		this.sensor = sensor;
		this.value = value;
	}

	public Data(DataDTO data) throws InvalidParamException {

		if(data==null) throw new InvalidParamException();
		this.id=data.getId();
		this.time=data.getTime();
		this.date=data.getDate();
		this.sensor=data.getSensor();
		this.value=data.getValue();
		
	}
	public String getCompositeId() {
		return id + "_" + time + "_" + date;
	}

	public String getId() {
		return id;
	}


	public String getTime() {
		return time;
	}


	public String getDate() {
		return date;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	
	public String getValue() {
		return value;
	}


	@Override
	public String toString() {
		return "Data {id=" + id + ", time=" + time + ", date=" + date + ", Sensor=" + sensor + ", value=" + value + "}";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
		result = prime * result + id.hashCode();
		result = prime * result + ((time == null) ? 0 : time.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Data)) {
			return false;
		}
		Data other = (Data) obj;
		
		if(this.date.equalsIgnoreCase(other.date)&&this.id.equals(other.id)&&this.time.equalsIgnoreCase(other.time))
		return true;
		else return false;
	}
	
	
	
	

}
