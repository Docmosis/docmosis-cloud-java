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

package com.docmosis.sdk.examples;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import com.docmosis.sdk.environment.Endpoint;
import com.docmosis.sdk.environment.EnvironmentBuilder;
import com.docmosis.sdk.render.RenderResponse;
import com.docmosis.sdk.render.Renderer;
import com.docmosis.sdk.render.RendererException;

/**
 * 
 * This example shows how to set environmental settings including Proxies,
 * Timeouts and logging.
 * 
 * How to use:
 * 
 *  1) sign up to the Docmosis Cloud Services
 *  2) plug your ACCESS_KEY into this class
 *  3) run the class and see the result
 *  
 * You can find a lot more about the Docmosis rendering capability by reading
 * the Web Services Guide and the Docmosis Template guide in the support area
 * of the Docmosis web site (http://www.docmosis.com/support) 
 *  
 */
public class SimpleProxyExample
{

	// you get an access key when you sign up to the Docmosis cloud service
	private static final String ACCESS_KEY = "XXX";

	// the welcome template is available in your cloud account by default
	private static final String TEMPLATE_NAME = "samples/WelcomeTemplate.docx";

	// The output format we want to produce (pdf, doc, odt and more exist).
	private static final String OUTPUT_FORMAT = "pdf";

	// The name of the file we are going to write the document to.
	private static final String OUTPUT_FILE = "output_cloud." + OUTPUT_FORMAT;

	public static void main(String args[]) throws IOException,
			RendererException
	{
		
		if (ACCESS_KEY.equals("XXX")) {
			System.err.println("Please set your ACCESS_KEY");
			System.exit(1);
		}
		
		EnvironmentBuilder envBldr = new EnvironmentBuilder();
		envBldr.setAccessKey(ACCESS_KEY);
		envBldr.setBaseUrl(Endpoint.DWS_VERSION_3_AUS.getBaseUrl());
		
		//Connect to the Docmosis service via a proxy
		envBldr.setProxy("HostAddress", 8888, "UserName", "Password");
		
		//Set Connection timeout and retry settings
		envBldr.setConnectTimeoutMS(2000); //A maximum of 2 seconds to establish the connection with the remote host.
		envBldr.setReadTimeoutMS(1000); //A maximum of 1 second of inactivity between two data packets.
		envBldr.setMaxTries(5); //A maximum of 5 attempts to connect to the service will be used
		envBldr.setRetryDelay(500); //A delay of 0.5 seconds between connection attempts will be used

		//Create data to send
		final String data = "<title>" + "This is Docmosis Cloud\n" + new Date() + "</title>";

		File outputFile = new File(OUTPUT_FILE);
		RenderResponse response = Renderer
									.render()
									.templateName(TEMPLATE_NAME)
									.outputName(OUTPUT_FILE)
									.sendTo(outputFile) //Or OutputStream TODO: MAKE OPTIONAL
									.data(data)
									.execute(envBldr.build());
							

		if (response.hasSucceeded()) {
			// great - render succeeded.

			System.out.println("Written:" + outputFile.getAbsolutePath());

		} else {
			// something went wrong, tell the user
			System.err.println("Render failed: status="
					+ response.getStatus()
					+ " shortMsg="
					+ response.getShortMsg()
					+ ((response.getLongMsg() == null) ? "" : " longMsg="
							+ response.getLongMsg()));
		}
	}
}