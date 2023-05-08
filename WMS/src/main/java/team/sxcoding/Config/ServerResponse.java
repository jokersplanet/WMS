package team.sxcoding.Config;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;



    private ServerResponse(int status) {
        this.status = status;
    }

    private ServerResponse(String msg) {
        this.msg = msg;
    }

    private ServerResponse(T data) {
        this.data = data;
    }

    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }

    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    private ServerResponse(String msg,T data) {
        this.msg = msg;
        this.data = data;
    }

    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }




    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess() {
        return this.status == ResponseStatus.SUCCESS.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }


    

    public static <T> ServerResponse<T> Success() {
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatus());
    }

    public static <T> ServerResponse<T> SuccessMessage(String msg) {
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatus(), msg);
    }

    public static <T> ServerResponse<T> Success(T data) {
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatus(), data);
    }

    public static <T> ServerResponse<T> Success(String msg, T data) {
        return new ServerResponse<T>(ResponseStatus.SUCCESS.getStatus(), msg, data);
    }


    public static <T> ServerResponse<T> Error() {
        return new ServerResponse<T>(ResponseStatus.ERROR.getStatus(), ResponseStatus.ERROR.getDesc());
    }


    public static <T> ServerResponse<T> ErrorMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseStatus.ERROR.getStatus(), errorMessage);
    }

    public static <T> ServerResponse<T> NeedLogin() {
        return new ServerResponse<T>(ResponseStatus.NEED_LOGIN.getStatus(), ResponseStatus.NEED_LOGIN.getDesc());
    }
    public static <T> ServerResponse<T> NeedLoginMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseStatus.NEED_LOGIN.getStatus(),errorMessage);
    }
    public static <T> ServerResponse<T> Forbidden() {
        return new ServerResponse<T>(ResponseStatus.FORBIDDEN.getStatus(), ResponseStatus.FORBIDDEN.getDesc());
    }
    public static <T> ServerResponse<T> ForbiddenMessage(String errorMessage) {
        return new ServerResponse<T>(ResponseStatus.FORBIDDEN.getStatus(),errorMessage );
    }




}
