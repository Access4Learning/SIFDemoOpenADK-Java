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

import java.util.List;

import openadk.library.tools.mapping.ADKMappingException;
import openadk.library.tools.mapping.MappingsContext;

import systemic.sif.sifcommon.BaseInfo;
import systemic.sif.sifcommon.model.SIFEvent;
import systemic.sif.sifcommon.publisher.BasePublisher;
import systemic.sif.sifcommon.publisher.SIFEventIterator;

public class XMLFileEventIterator implements SIFEventIterator
{
	private List<SIFEvent> events  = null;
	private int currentPos = 0;

	public XMLFileEventIterator(BasePublisher publisher)
	{
		events = publisher.getEventsFromFile();
		currentPos = 0;
	}
	
	//@Override
    public SIFEvent getNextEvent(BaseInfo baseInfo, MappingsContext mappingCtx) throws ADKMappingException
    {
		SIFEvent event = null;
		if (hasNext())
		{
			event = events.get(currentPos);
			currentPos++;
		}

	    return event;
    }

	//@Override
    public boolean hasNext()
    {
		return (events != null) && (currentPos < events.size());
    }

	//@Override
    public void releaseResources()
    {
	    //noting to do here ...
    }
}
