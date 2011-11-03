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
package sifdemo.publisher.iterator;

import openadk.library.ADK;
import openadk.library.SIFDataObject;
import openadk.library.school.SchoolInfo;
import openadk.library.tools.mapping.ADKMappingException;

import org.apache.log4j.Logger;

import sifdemo.dao.SQLResult;
import sifdemo.dao.SchoolDAO;
import systemic.sif.sifcommon.BaseInfo;
import systemic.sif.sifcommon.mapping.MappingInfo;
import systemic.sif.sifcommon.mapping.adapter.ResultSetAdapter;
import systemic.sif.sifcommon.publisher.SIFResponseIterator;

public class SchoolInfoResponseIterator implements SIFResponseIterator
{
	private Logger logger = ADK.getLog();

	private SQLResult result = null;
	private ResultSetAdapter adapter = null;

	public SchoolInfoResponseIterator()
	{
		SchoolDAO dao = new SchoolDAO();
		if (dao != null)
		{
			result = dao.getSchools();
			if ((result != null) && (result.getResultSet() != null))
			{
				try
				{
					adapter = new ResultSetAdapter(result.getResultSet(), SchoolInfo.class);
				}
				catch (Exception ex)
				{
					logger.error("SchoolInfoEventIterator() in SchoolInfoEventIterator has failed: "+ex.getMessage(), ex);
					adapter = null;
				}
			}
		}
	}
	
	//@Override
    public SIFDataObject getNextSIFObject(BaseInfo baseInfo, MappingInfo mappingInfo) throws ADKMappingException
    {
		if (adapter != null)
		{
			try
			{
				SchoolInfo sifObj = (SchoolInfo)adapter.map(mappingInfo.getMappingCtx());
				sifObj.setRefId(ADK.makeGUID());

				return sifObj;
			}
			catch (Exception ex)
			{
				logger.error("getNextEvent() in SchoolInfoEventIterator has failed: "+ex.getMessage(), ex);
			}
		}
		
		return null;
    }

	//@Override
    public boolean hasNext()
    {
		if (adapter != null)
		{
			try
			{
				return adapter.hasNext();
			}
			catch (Exception ex)
			{
				logger.error("hasNext() in SchoolInfoEventIterator has failed: "+ex.getMessage(), ex);
			}
		}

	    return false;
    }

	//@Override
    public void releaseResources()
    {
		if (result != null)
		{
			result.close();
			result = null;
		}
    }

}
