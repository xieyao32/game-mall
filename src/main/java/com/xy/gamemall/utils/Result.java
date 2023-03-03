package com.xy.gamemall.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {
    private static final long serialVersionUID = 1L;
    private int resultCode;     //200 - 成功   500 - 失败
    private String message;
    //用户要返回给浏览器的数据
    private Map<String, Object> datas = new HashMap<String, Object>();

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

    public static Result success(){
        Result result=new Result();
        result.setResultCode(200);
        result.setMessage("操作成功");
        return result;
    }

    public static Result success(String message){
        Result result=new Result();
        result.setResultCode(200);
        result.setMessage(message);
        return result;
    }

    public static Result fail(){
        Result result=new Result();
        result.setResultCode(500);
        result.setMessage("操作失败");
        return result;
    }

    public static Result fail(String message){
        Result result=new Result();
        result.setResultCode(500);
        result.setMessage(message);
        return result;
    }

    public Result add(String key,Object value){
        this.getDatas().put(key,value);
        return this;
    }


    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, Object> getDatas() {
        return datas;
    }

    public void setDatas(Map<String, Object> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", datas=" + datas +
                '}';
    }
}
