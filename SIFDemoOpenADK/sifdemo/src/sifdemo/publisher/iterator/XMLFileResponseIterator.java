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

import openadk.library.SIFDataObject;
import openadk.library.tools.mapping.ADKMappingException;

import systemic.sif.sifcommon.BaseInfo;
import systemic.sif.sifcommon.mapping.MappingInfo;
import systemic.sif.sifcommon.publisher.BasePublisher;
import systemic.sif.sifcommon.publisher.SIFResponseIterator;

public class XMLFileResponseIterator implements SIFResponseIterator
{
	private List<SIFDataObject> sifObjs = null;
	private int currentPos = 0;
	
	public XMLFileResponseIterator(BasePublisher publisher)
	{
		sifObjs = publisher.getObjectsFromFile();
		currentPos = 0;
	}

	//@Override
    public SIFDataObject getNextSIFObject(BaseInfo baseInfo, MappingInfo mappingInfo) throws ADKMappingException
    {
		SIFDataObject sifObj = null;
		if (hasNext())
		{
			sifObj = sifObjs.get(currentPos);
			currentPos++;
		}

	    return sifObj;
    }

	//@Override
    public boolean hasNext()
    {
		return (sifObjs != null) && (currentPos < sifObjs.size());
    }

	//@Override
    public void releaseResources()
    {
	    //noting to do here ...
    }
}
