package edu.health.util;

import java.util.HashMap;
import java.util.Map;

public class JsonResults {
    public final static boolean SUCCESS = true;
    public final static boolean FAILED  = false;
    private boolean result_code;
    private Map<String,Object> result_arr = new HashMap<>();
    private String result_msg = "操作成功";

    public JsonResults() {
        this.result_code = SUCCESS;
    }

    public boolean isResult_code() {
        return result_code;
    }

    public void setResult_code(boolean result_code) {
        this.result_code = result_code;
    }

    public Map<String, Object> getResult_arr() {
        return result_arr;
    }

    public void setResult_arr(Map<String, Object> result_arr) {
        this.result_arr = result_arr;
    }

    public String getResult_msg() {
        return result_msg;
    }

    public void setResult_msg(String result_msg) {
        this.result_msg = result_msg;
    }

    public void put(String key, Object value){
        result_arr.put(key,value);
    }

    @Override
    public String toString() {
        return "JsonResults{" +
                "result_code=" + result_code +
                ", result_arr=" + result_arr +
                ", result_msg='" + result_msg + '\'' +
                '}';
    }
}
