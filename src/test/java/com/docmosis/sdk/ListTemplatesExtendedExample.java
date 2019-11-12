package com.docmosis.sdk;

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

import java.io.IOException;

import com.docmosis.sdk.environment.Environment;
import com.docmosis.sdk.template.ListTemplatesResponse;
import com.docmosis.sdk.template.Template;
import com.docmosis.sdk.template.TemplateException;

/**
 * 
 * This example connects to the public Docmosis cloud server and returns 
 * a list of the templates stored on the server including associated meta data.
 * 
 * How to use:
 * 
 *  1) sign up to the Docmosis Cloud Services
 *  2) plug your ACCESS_KEY into this class
 *  3) run the class and see the result
 *  
 * You can find a lot more about the Docmosis conversion capability by reading
 * the Web Services Guide and the Docmosis Template guide in the support area
 * of the Docmosis web site (http://www.docmosis.com/support) 
 *  
 */
public class ListTemplatesExtendedExample
{
	// you get an access key when you sign up to the Docmosis cloud service
	private static final String ACCESS_KEY = "XXX";

	public static void main(String args[]) throws TemplateException, IOException
	{

		if (ACCESS_KEY.equals("XXX")) {
			System.err.println("Please set your ACCESS_KEY");
			System.exit(1);
		}

		//Set the default environment to use your access key
		Environment.setDefaults("https://eu.dws3.docmosis.com/api/", ACCESS_KEY);

		//Create and execute the request
		ListTemplatesResponse response = Template.list().paging(true).includeDetail(false).execute();
		
		while (response.hasSucceeded() && response.getNextPageToken() != null) {
			System.out.println("Returned Page of " + response.list().size() + " records");
			response = Template.list().paging(true).includeDetail(false).pageToken(response.getNextPageToken()).execute();
		}
		if (response.hasSucceeded()) {
			System.out.println("Returned Page of " + response.list().size() + " records");
			//System.out.println(response.toString());
		}
	}
}

