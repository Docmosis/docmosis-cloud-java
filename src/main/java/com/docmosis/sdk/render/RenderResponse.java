/*
 *   Copyright 2019 Docmosis.com or its affiliates.  All Rights Reserved.
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *       http://www.apache.org/licenses/LICENSE-2.0
 *   or in the LICENSE file accompanying this file.
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.docmosis.sdk.render;

import com.docmosis.sdk.response.DocmosisCloudResponse;

/**
 * This class encapsulates a response to a render request.
 * 
 * Typically you would use this response to check for success, then decide what action to take.  For example:
 * 
 * <pre>
 * 
 *   RenderResponse response = rr.execute();
 *   try {  
 *     if (response.hasSucceeded()) {
 *       InputStream doc = response.getDocument();
 *       // do something with the doc
 *     } else {
 *       // read the error messages and status code to 
 *       // decide what to do - check error messages/tell user
 *     }
 *   } finally {
 *     response.cleanup();
 *   }
 * </pre>
 *
 * Since the response may contain an InputStream from the request, it is important that you 
 * have the finally block to cleanup.
 */
public class RenderResponse extends DocmosisCloudResponse
{
	private String requestId;
	private int pagesRendered;

	/**
	 * If the requestId was set in the render, it will be returned in this response.
	 * This helps asynchronous processing determine which response relates to which request.
	 *  
	 * @return null if not set
	 */
	public String getRequestId()
	{
		return requestId;
	}

	public void setRequestId(String requestId)
	{
		this.requestId = requestId;
	}
	
	/**
	 * Get the number of pages rendered on success.
	 * 
	 * @return 0 if unknown
	 */
	public int getPagesRendered()
	{
		return pagesRendered;
	}

	public void setPagesRendered(int pagesRendered)
	{
		this.pagesRendered = pagesRendered;
	}
}
