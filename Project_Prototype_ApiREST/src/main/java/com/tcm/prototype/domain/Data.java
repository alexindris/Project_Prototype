package com.tcm.prototype.domain;

import com.tcm.prototype.application.dto.DataDTO;
import com.tcm.prototype.utilities.InvalidParamException;

public class Data {
	
	private String id;
	private String time;
	private String date;
	private String sensor;
	private String valor;
	
	public Data() {
		
	}
	
	public Data(DataDTO data) throws InvalidParamException {

		if(data==null) throw new InvalidParamException();
		this.id=data.getId();
		this.time=data.getTime();
		this.date=data.getDate();
		this.sensor=data.getSensor();
		this.valor=data.getValor();
		
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

	public String getValor() {
		return valor;
	}


	@Override
	public String toString() {
		return "Data {id=" + id + ", time=" + time + ", date=" + date + ", Sensor=" + sensor + ", valor=" + valor + "}";
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
