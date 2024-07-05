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

package edu.isel.csee.jchecker2_0.core.stage;

import java.util.ArrayList;

/**
 * Interface for compile and execution
 */
public interface IGradeStage {

	/**
	 * Method for compile
	 * @param dpath source codes path
	 * @return state
	 */
	public int compile(String dpath);

	/**
	 * Method for execution
	 * @param cases result of testing with oracle input
	 * @param output oracle output
	 * @param dpath source code path
	 * @return result
	 */
	public boolean build(ArrayList<String> cases, String output, String dpath);

	/**
	 * Method for testing
	 * @param argument oracle input
	 * @param isTest isTest
	 * @return result
	 */
	public ArrayList<String> getTest(String argument, boolean isTest);

	/**
	 * Method for testing
	 * @param argument main path
	 * @param cases oracle input
	 * @param isTest isTest
	 * @return result
	 */
	public ArrayList<String> getTest(String argument, String cases, boolean isTest);
}
