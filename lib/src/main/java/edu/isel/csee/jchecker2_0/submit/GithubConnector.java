/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package edu.isel.csee.jchecker2_0.submit;

import java.io.File;

/**
 * Class for checking github repository
 */
public class GithubConnector 
{
	/**
	 * Method for clone the repository
	 * @param URL repository url
	 * @param workpath workspace path
	 */
	public void clone(String URL, String workpath)
	{
		ProcessBuilder builder = null;
		
		try 
		{
			builder = new ProcessBuilder("git clone " + URL);
			builder.directory(new File(workpath));
			
			Process process = builder.start();
			process.destroy();
			
			
		} catch(Exception e) { e.printStackTrace(); }
	}
}
