package edu.isel.csee.jchecker.score;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class PolicyValidator {
    public void validator(JsonObject policy) {
        // validation for classes deduct point
        JsonObject obj = new Gson().fromJson(policy.get("classes"), JsonObject.class);
        if (!obj.has("deductPoint")) {
            obj.addProperty("deductPoint", 0.0);
            policy.add("classes", obj);
        }
        if (!obj.has("maxDeduct")) {
            obj.addProperty("maxDeduct", 0.0);
            policy.add("classes", obj);
        }
    }
}
