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

package edu.isel.csee.jchecker2_0.score;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;

/**
 * Class for getting grading policy
 */
public class PolicyParser {
	/**
	 * Method for parsing JSON data (grading policy)
	 * @param policyTable EvaluationSchemeMapper
	 * @param policy JSON data (grading policy)
	 */
	public void parse(EvaluationSchemeMapper policyTable, JsonObject policy) {
		try {
			JsonObject obj;

			policyTable.setToken(policy.get("token").getAsString());
			policyTable.setItoken(policy.get("itoken").getAsString());
			policyTable.setClassName(policy.get("className").getAsString());
			policyTable.setInstructor(policy.get("instructor").getAsString());
			policyTable.setPoint(policy.get("point").getAsDouble());
			policyTable.setDirect(policy.get("feedback").getAsBoolean());
			policyTable.setDueDate(policy.get("dueDate").getAsString());
			policyTable.setFeedbackLevel(policy.get("feedbackLevel").getAsInt());

			obj = new Gson().fromJson(policy.get("compiled"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setCompiled(true);
				policyTable.setBTool(getBoolean(obj, "buildTool", false));
				policyTable.setCompiled_deduct_point(getDouble(obj, "deductPoint", 0.0));
			}

			obj = new Gson().fromJson(policy.get("oracle"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setTest(true);
				policyTable.setInputs(getList(obj, "input", new TypeToken<ArrayList<String>>(){}));
				policyTable.setChecksums(getList(obj, "checksum", new TypeToken<ArrayList<String>>(){}));
				policyTable.setOutputs(getList(obj, "output", new TypeToken<ArrayList<String>>(){}));
				policyTable.setRuntime_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setRuntime_max_deduct(getDouble(obj, "maxDeduct", 0.0));
				policyTable.setReqFilePath(getList(obj, "filePath", new TypeToken<ArrayList<String>>(){}));
			}

			obj = new Gson().fromJson(policy.get("packages"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setPackageName(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setPackage_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setPackage_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("classes"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setReqClass(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setClass_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setClass_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("methods"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setReqMethod(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setReqMethodCount(getList(obj, "count", new TypeToken<ArrayList<Integer>>(){}));
				policyTable.setReqMethodClass(getList(obj, "classes", new TypeToken<ArrayList<String>>(){}));
				policyTable.setMethod_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setMethod_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("customException"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setReqCustExc(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setCustomExc_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setCustomExc_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("customStructure"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setReqCusStruct(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setCustomStr_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setCustomStr_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("inheritSuper"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setSoriginClass(getList(obj, "origins", new TypeToken<ArrayList<String>>(){}));
				policyTable.setSuperClass(getList(obj, "inherit", new TypeToken<ArrayList<String>>(){}));
				policyTable.setSpc_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setSpc_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("inheritInterface"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setIoriginClass(getList(obj, "origins", new TypeToken<ArrayList<String>>(){}));
				policyTable.setInterfaceClass(getList(obj, "inherit", new TypeToken<ArrayList<String>>(){}));
				policyTable.setItf_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setItf_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("overriding"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setOverriding(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setOvr_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setOvr_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("overloading"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setOverloading(getList(obj, "required", new TypeToken<ArrayList<String>>(){}));
				policyTable.setOvl_deduct_point(getDouble(obj, "deductPoint", 0.0));
				policyTable.setOvl_max_deduct(getDouble(obj, "maxDeduct", 0.0));
			}

			obj = new Gson().fromJson(policy.get("thread"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setThreads(true);
				policyTable.setThr_deduct_point(getDouble(obj, "deductPoint", 0.0));
			}

			obj = new Gson().fromJson(policy.get("javadoc"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setJavadoc(true);
				policyTable.setJvd_deduct_point(getDouble(obj, "deductPoint", 0.0));
			}

			obj = new Gson().fromJson(policy.get("encapsulation"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setEncaps(true);
				policyTable.setEnc_deduct_point(getDouble(obj, "deductPoint", 0.0));
			}

			obj = new Gson().fromJson(policy.get("count"), JsonObject.class);
			if (getBoolean(obj, "state", false)) {
				policyTable.setCount(true);
				policyTable.setMethodCount(getInt(obj, "methodCount", 0));
				policyTable.setFieldCount(getInt(obj, "fieldCount", 0));
				policyTable.setEnForCount(getInt(obj, "enForCount", 0));
				policyTable.setCnt_deduct_point(getDouble(obj, "deductPoint", 0.0));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private boolean getBoolean(JsonObject obj, String key, boolean defaultValue) {
		if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) {
			return defaultValue;

		}
		try {
			return obj.get(key).getAsBoolean();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private double getDouble(JsonObject obj, String key, double defaultValue) {
		if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) {
			return defaultValue;

		}
		try {
			return obj.get(key).getAsDouble();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private int getInt(JsonObject obj, String key, int defaultValue) {
		if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) {
			return defaultValue;

		}
		try {
			return obj.get(key).getAsInt();
		} catch (Exception e) {
			return defaultValue;
		}
	}

	private <T> ArrayList<T> getList(JsonObject obj, String key, TypeToken<ArrayList<T>> type) {
		if (obj == null || !obj.has(key) || obj.get(key).isJsonNull()) {
			return new ArrayList<>();

		}
		try {
			return new Gson().fromJson(obj.get(key), type.getType());
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}
}
