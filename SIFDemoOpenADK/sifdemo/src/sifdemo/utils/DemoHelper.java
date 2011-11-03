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
package sifdemo.utils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

import openadk.library.SIFDataObject;

import org.apache.log4j.Logger;

import au.com.systemic.framework.utils.DateUtils;
import au.com.systemic.framework.utils.StringUtils;


public class DemoHelper
{
  private final static String RECORD_MARKER = "\n===============================================================================\n";

  protected static final Logger logger = Logger.getLogger(DemoHelper.class);
  
	public static void dumpObject(SIFDataObject sifObject, String zoneName, String consumerID, String dumpFileName)
	{
		if (StringUtils.notEmpty(dumpFileName))
		{
			BufferedWriter out = null;
			try
			{
				FileWriter fstream = new FileWriter(dumpFileName, true);
				out = new BufferedWriter(fstream);
				out.write(RECORD_MARKER);
				out.write("== "+sifObject.getElementDef().name()+" from zone '"+zoneName+"' processed by consumer '"+consumerID+"' at "+DateUtils.dateToString(new Date(), "dd/MM/yyyy HH:mm:ss.SSS"));
				out.write(RECORD_MARKER);
				out.write(sifObject.toXML());
			}
			catch (Exception ex)
			{
				logger.error("Failed to write data to dump file with name:" + dumpFileName, ex);
			}
			finally
			{
				if (out != null)
				{
					try
					{
						out.close();
					}
					catch (Exception ex)
					{
					} // nothing we can do
				}
			}
		}
	}

	public static String getDumpFileName(String agentID, String outputDir)
	{
		return outputDir + "/" + agentID + ".xml";
	}

}
