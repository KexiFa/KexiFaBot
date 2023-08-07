package com.kexifa.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author kexifa
 * @version 1.0
 * @date 2023/6/29 17:10
 */
@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -7358316591226283588L;

    private Integer code; // 返回编码
//    private String msg;   // 返回信息
    private T data;       // 返回内容

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
//        this.msg = msg;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
//        this.msg = msg;
        this.data = data;
    }

    public Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
//        this.msg = resultCode.getMsg();
    }

    public Result(ResultCode resultCode, T data) {
        this.code = resultCode.getCode();
//        this.msg = resultCode.getMsg();
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(ResultCode.SUCCESS, data);
    }

    public static <T> Result<T> fail() {
        return new Result<T>(ResultCode.FAILED);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<T>(ResultCode.FAILED, data);
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, data);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Result<?> baseRes = (Result<?>) obj;

        return this.code == baseRes.code &&
//                Objects.equals(msg, baseRes.msg) &&
                Objects.equals(data, baseRes.data);
    }

    @Override
    public String toString() {
        return "Result{" +
                "code=" + code +
//                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
