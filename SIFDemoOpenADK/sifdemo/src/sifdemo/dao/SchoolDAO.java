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

import java.sql.SQLException;

import org.apache.log4j.Logger;

import au.com.systemic.framework.dao.BaseDAO;
import au.com.systemic.framework.dao.connection.ConnectionManager;
import au.com.systemic.framework.dao.sql.SQLStatement;
import au.com.systemic.framework.dao.sql.SQLStatementLookup;

public class SchoolDAO extends BaseDAO
{
	protected final Logger logger = Logger.getLogger(getClass());
	
    public SQLResult getSchools()
    {
    	SQLResult result = new SQLResult();
		try
		{
			logger.debug("SchoolDAO.getAllSchools started...");
			SQLStatementLookup factory = SQLStatementLookup.getInstance(SchoolDAO.class);
			SQLStatement stmt = factory.getStatement("getAllSchools");
			result.setConnection(ConnectionManager.getConnection(stmt.getSqlStatementInfo().getDatasourceName()));
			result.setResultSet(stmt.executeQuery(result.getConnection()));
			logger.debug("SchoolDAO.getAllSchools finished");
		}
		catch (SQLException sqlEx)
		{
			logger.error("SQL Error: " + sqlEx.getMessage());
			result.close();
			result = null;
		}
		return result;
	}
}
