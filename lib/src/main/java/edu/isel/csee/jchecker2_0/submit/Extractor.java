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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Class for preprocessing the submission
 */
public class Extractor {

	/**
	 * Method for unzip the submission
	 * @param filepath file path
	 * @param output output path
	 */
	public void unzip(String filepath, String output){
		ZipFile zipfile = null;

		try {
			zipfile = new ZipFile(filepath, Charset.forName("EUC-KR"));

			Enumeration<? extends ZipEntry> e = zipfile.entries();

			while(e.hasMoreElements()){
				ZipEntry entry = e.nextElement();
				File dest = new File(output, entry.getName());

				dest.getParentFile().mkdirs();

				if (entry.isDirectory()){
					continue;
				}
				else {
					BufferedInputStream bis = new BufferedInputStream(zipfile.getInputStream(entry));
					BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dest), 1024);
					int len;
					byte[] buffer = new byte[1024];

					while((len = bis.read(buffer, 0, 1024)) != -1) {
						bos.write(buffer, 0, len);
					}

					bis.close();
					bos.close();
				}
			}

		}
		catch (FileNotFoundException e){
			System.out.println("No zip file in the path: " + filepath);
			e.printStackTrace();
		}
		catch (IOException e){
			System.out.println("Incorrect type or missed target.");
			e.printStackTrace();
		}
		finally {
			try {
				if (zipfile != null){
					zipfile.close();
				}
			}
			catch (IOException e){
				e.printStackTrace();
			}
		}
	}
}
