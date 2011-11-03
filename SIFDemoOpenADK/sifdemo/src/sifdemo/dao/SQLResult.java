/*
* Copyright 2010-2011 Systemic Pty Ltd
* 
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
* 
* Unless required by applicable law or agreed to in writing, software distributed under the License 
* is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
* or implied.
* See the License for the specific language governing permissions and limitations under the License.
*/
package sifdemo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import au.com.systemic.framework.dao.connection.ConnectionManager;

public class SQLResult
{
    private Connection connection = null;
    private ResultSet resultSet = null;
    
    public SQLResult()
    {
    	this(null, null);
    }
    
    public SQLResult(Connection connection, ResultSet resultSet)
    {
    	setConnection(connection);
    	setResultSet(resultSet);
    }
    
    public SQLResult(Connection connection)
    {
    	this(connection, null);
    }

    
    public SQLResult(ResultSet resultSet)
    {
    	this(null, resultSet);
    }
    
	public Connection getConnection()
    {
    	return connection;
    }
	
	public void setConnection(Connection connection)
    {
    	this.connection = connection;
    }
	
	public ResultSet getResultSet()
    {
    	return resultSet;
    }
	
	public void setResultSet(ResultSet resultSet)
    {
    	this.resultSet = resultSet;
    }
    
    public void close()
    {
        closeResultSet(getResultSet());
        ConnectionManager.closeConnection(getConnection());
 
    }

    /**
     * Convenience Method. Deals with exceptions. This method will close a given result set.
     *
     * @param rs The result set to be closed.
     */
	private void closeResultSet(ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		}
		catch (SQLException ex) {} // nothing we can do!
	}
}
