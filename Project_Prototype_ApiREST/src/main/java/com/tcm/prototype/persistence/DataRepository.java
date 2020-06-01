package com.tcm.prototype.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import com.tcm.prototype.domain.Data;
import com.tcm.prototype.utilities.InvalidParamException;
import com.tcm.prototype.utilities.NotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DataRepository { 


	public List<Data> getAllData() throws NotFoundException, InvalidParamException {
		
		
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		List<Data> out = new ArrayList<Data>();
		try {
			String sql = "SELECT * FROM DATA";
			PreparedStatement pst = connection.prepareStatement(sql);
			pst.clearParameters();
			ResultSet rs= pst.executeQuery();
			while(rs.next()) {
				String id = rs.getString("D_ID");
				String time = rs.getString("D_TIME");
				String date = rs.getString("D_DATE");
				String sensor = rs.getString("D_SENSOR");
				String value = rs.getString("D_VALUE");
				out.add(new Data(id, time, date, sensor, value));
			}
			if(out.size()==0)
			throw new NotFoundException();
			return out;
			
		}catch(SQLException e) {
			e.printStackTrace();
			throw new InvalidParamException();
		}
	}


public List<Data> getData(String dataId) throws NotFoundException, InvalidParamException {
	
	ConnectionBBDD connection = ConnectionRepository.getConnection();
	List<Data> out = new ArrayList<Data>();
	try {
		String sql = "SELECT * FROM DATA WHERE D_ID = ?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.clearParameters();
		pst.setString(1, dataId);
		ResultSet rs= pst.executeQuery();
		while(rs.next()) {
			String id = rs.getString("D_ID");
			String time = rs.getString("D_TIME");
			String date = rs.getString("D_DATE");
			String sensor = rs.getString("D_SENSOR");
			String value = rs.getString("D_VALUE");
			out.add(new Data(id, time, date, sensor, value));
		}
		if(out.size()==0)
		throw new NotFoundException();
		out.sort(new Comparator<Data>() {

			@Override
			public int compare(Data o1, Data o2) {
				DateFormat format = new SimpleDateFormat("MM/DD/yyyy-HH:mm:ss", Locale.ENGLISH);
				try {
					Date date1 = format.parse(o1.getDate()+"-"+o1.getTime());
					Date date2 = format.parse(o2.getDate()+"-"+o2.getTime());
					return date1.compareTo(date2) ;
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return 0;
				
				
				
			}
			
		});
		return out;
		
	}catch(SQLException e) {
		e.printStackTrace();
		throw new InvalidParamException();
	}
}

public void deleteData(String id) throws NotFoundException, InvalidParamException {
	
	try {
		ConnectionBBDD connection = ConnectionRepository.getConnection();
		String sql ="DELETE FROM DATA WHERE D_ID = ?";
		PreparedStatement pst = connection.prepareStatement(sql);
		pst.clearParameters();
		pst.setString(1, id);
		pst.executeUpdate();
		
		
	}catch(SQLException e) {
		e.printStackTrace();
		throw new InvalidParamException();
	}
	
}


public void deleteAllData() throws InvalidParamException {
	try {
	ConnectionBBDD connection = ConnectionRepository.getConnection();
	String sql ="DELETE FROM DATA";
	PreparedStatement pst = connection.prepareStatement(sql);
	pst.executeUpdate();
	}catch(SQLException e) {
		e.printStackTrace();
		throw new InvalidParamException();
	}
}

public void saveData(Data data) throws InvalidParamException {
	try {
	ConnectionBBDD connection = ConnectionRepository.getConnection();
	String sql = "INSERT INTO DATA (D_ID,D_TIME,D_DATE,D_SENSOR,D_VALUE) values (?,?,?,?,?)";
	PreparedStatement pst = connection.prepareStatement(sql);
	pst.clearParameters();
	pst.setString(1, data.getId());
	pst.setString(2, data.getTime());
	pst.setString(3, data.getDate());
	pst.setString(4,data.getSensor());
	pst.setString(5,data.getValue());
	if(pst.executeUpdate()!=1) {
		throw new InvalidParamException();
	}
}catch(SQLException e) {
	e.printStackTrace();
	throw new InvalidParamException();
}

}
}
