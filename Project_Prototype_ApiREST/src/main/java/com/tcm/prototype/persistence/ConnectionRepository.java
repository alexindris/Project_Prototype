package com.tcm.prototype.persistence;

import com.tcm.prototype.utilities.ConstantUtilities;
import com.tcm.prototype.utilities.InvalidParamException;

public class ConnectionRepository {
	private static ConnectionBBDD connection;
	static ConnectionBBDD getConnection() throws InvalidParamException  {
		if (connection==null) connection = new ConnectionBBDD(ConstantUtilities.USERNAME_BBDD, ConstantUtilities.PASSWORD_BBDD);
		return connection;
	}
	
	

}
