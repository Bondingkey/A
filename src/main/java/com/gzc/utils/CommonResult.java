package com.gzc.utils;

/**
 * @Author chenxin
 * @date 2022/3/16
 * @Version 1.0
 * 功能：设置异步请求响应结果的格式
 * 最终是将CommonResult的对象变为json字符串返给js
 */
public class CommonResult {
    private boolean flag;  //确定本次响应的结果是否正常
    private Object resultData;  //响应结果的数据
    private String message;  //信息

    /**
     * 处理请求成功
     * @return
     */
    public static CommonResult ok(){
        return new CommonResult().setFlag(true);
    }

    /**
     * 处理请求失败
     * @return
     */
    public static CommonResult error(){
        return new CommonResult().setFlag(false);
    }

    public boolean isFlag() {
        return flag;
    }

    private CommonResult setFlag(boolean flag) {
        this.flag = flag;
        return this;
    }

    public Object getResultData() {
        return resultData;
    }

    public CommonResult setResultData(Object resultData) {
        this.resultData = resultData;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResult setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        return "CommonResult{" +
                "flag=" + flag +
                ", resultData=" + resultData +
                ", message='" + message + '\'' +
                '}';
    }


}
