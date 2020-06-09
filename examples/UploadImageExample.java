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

import java.io.File;
import java.io.IOException;

import com.docmosis.sdk.environment.Environment;
import com.docmosis.sdk.image.Image;
import com.docmosis.sdk.image.ImageException;
import com.docmosis.sdk.image.UploadImageResponse;

/**
 * 
 * This example connects to the public Docmosis cloud server and uploads an 
 * image to store on the server.
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
public class UploadImageExample
{
	// you get an access key when you sign up to the Docmosis cloud service
	private static final String ACCESS_KEY = "XXX";

	//Full path of image to be uploaded
	private static final String IMAGE_TO_UPLOAD = "C:/example/myImage.png";

	public static void main(String args[]) throws ImageException, IOException
	{

		if (ACCESS_KEY.equals("XXX")) {
			System.err.println("Please set your ACCESS_KEY");
			System.exit(1);
		}

		//Set the default environment to use your access key.
		Environment.setDefaults(ACCESS_KEY);

		//Set the file we are going to upload.
		File uploadFile = new File(IMAGE_TO_UPLOAD);

		//Create and execute the request
		UploadImageResponse response = Image
										.upload()
										.imageFile(uploadFile)
										.execute();

		if (response.hasSucceeded()) {
			// great - request succeeded.
			System.out.println("Successfully uploaded " + IMAGE_TO_UPLOAD);

		} else {
			// something went wrong, tell the user
			System.err.println("Upload image failed: status="
					+ response.getStatus()
					+ " shortMsg="
					+ response.getShortMsg()
					+ ((response.getLongMsg() == null) ? "" : " longMsg="
							+ response.getLongMsg()));
		}
	}
}