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

package edu.isel.csee.jchecker2_0.statics.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for collecting all path and source codes
 */
public class EntireContentParser {
	/**
	 * Method for getting all source codes
	 * @param target main path
	 * @return source
	 */
	public List<String> getAllFiles(String target) {
		List<String> source = new ArrayList<>();

		try {
			File srclist = new File(target + "//srclist.txt");

			BufferedReader br = new BufferedReader(new FileReader(srclist));
			BufferedReader piece = null;

			String line = "";

			StringBuffer sb = null;

			while((line = br.readLine()) != null) {
				sb = new StringBuffer();
				File file = new File(target + "/" + line);
				piece = new BufferedReader(new FileReader(file));

				String content = "";

				while((content = piece.readLine()) != null) {
					sb.append(content);
					sb.append("\n");
				}

				source.add(sb.toString());
			}

			br.close();
			piece.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return source;
	}

	/**
	 * Method for getting all path of source codes
	 * @param target main path
	 * @return filePathList
	 */
	public List<String> getAllFilePaths(String target) {
		List<String> filePathList = new ArrayList<>();

		try {
			File srclist = new File(target + "//srclist.txt");

			BufferedReader bufferedReader = new BufferedReader(new FileReader(srclist));
			String line = "";

			while((line = bufferedReader.readLine()) != null) {
				filePathList.add(target + "/" + line);
			}

			bufferedReader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return filePathList;
	}
}
