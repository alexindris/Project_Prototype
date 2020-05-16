package com.prorotype.apirest.domain;



public class Data {
	private int id;
	private String time;
	private String date;
	private String sensor;
	private float valor;
	
	/**
	 * @param id
	 * @param time
	 * @param date
	 * @param sensor
	 * @param valor
	 */
	
	public Data(int id, String time, String date, String sensor, float valor) {
		super();
		this.id = id;
		this.time = time;
		this.date = date;
		this.sensor = sensor;
		this.valor = valor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Data {id=" + id + ", time=" + time + ", date=" + date + ", Sensor=" + sensor + ", valor=" + valor + "}";
	}
	
	
	
	

}
