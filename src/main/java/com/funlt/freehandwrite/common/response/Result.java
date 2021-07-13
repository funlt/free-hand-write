package com.funlt.freehandwrite.common.response;

/**
 * 接口统一返回类型
 * @param <T>
 */
public class Result<T> {

    private boolean success;

    private String msg;

    private T data;

    public Result() {

    }

    public Result(boolean success, String msg, T data) {
        this.success = success;
        this.msg = msg;
        this.data = data;
    }

    public Result<T> ok(){
        return new Result<>(true,null,null);
    }

    public Result<T> ok(String msg) {
        return new Result<>(true, msg, null);
    }

    public Result<T> oK(T data) {
        return new Result<>(true, null, data);
    }

    public Result<T> ok(String msg, T data) {
        return new Result<>(true, msg, data);
    }

    public Result<T> fail() {
        return new Result<>(false, null, null);
    }

    public Result<T> fail(String msg) {
        return new Result<>(false, msg, null);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }


}
