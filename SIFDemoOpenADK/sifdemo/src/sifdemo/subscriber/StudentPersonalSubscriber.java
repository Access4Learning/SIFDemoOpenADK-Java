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
package sifdemo.subscriber;

import openadk.library.ADKException;
import openadk.library.Query;
import openadk.library.SIFDataObject;
import openadk.library.Zone;
import openadk.library.student.StudentDTD;

import sifdemo.utils.DemoHelper;

import systemic.sif.sifcommon.mapping.MappingInfo;
import systemic.sif.sifcommon.model.SIFEvent;
import systemic.sif.sifcommon.subscriber.BaseSubscriber;

public class StudentPersonalSubscriber extends BaseSubscriber 
{
	public StudentPersonalSubscriber(String subscriberID)
	{
		super(subscriberID);
		setDtd(StudentDTD.STUDENTPERSONAL);
	}
	  
	@Override
    public void finalise()
    {
	    logger.debug("...in finalise for "+getId() );
    }
	
	@Override
	protected void addToInitialSyncQuery(Query query, Zone zone)
	{
		// query.addCondition( StudentDTD.STUDENTPERSONAL_LOCALID, ComparisonOperators.LE,
		// "M830540004340" );
	}
  
	@Override
    public void processEvent(SIFEvent sifEvent, Zone zone, MappingInfo mappingInfo, String consumerID) throws ADKException
    {
		DemoHelper.dumpObject(sifEvent.getSifObject(), zone.getZoneId(), consumerID,DemoHelper.getDumpFileName(getAgentID(), getFrameworkProperties().getTestDir(getAgentID())));
    }

	@Override
    public void processResponse(SIFDataObject sifObject, Zone zone, MappingInfo mappingInfo, String consumerID) throws ADKException
    {
		DemoHelper.dumpObject(sifObject, zone.getZoneId(), consumerID, DemoHelper.getDumpFileName(getAgentID(), getFrameworkProperties().getTestDir(getAgentID())));
    }

}
