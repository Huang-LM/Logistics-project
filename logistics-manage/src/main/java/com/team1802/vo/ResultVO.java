package com.team1802.vo;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */
public class ResultVO {
    private int code;

    private String message;

    public static final ResultVO FAILED = new ResultVO(0, "failed");

    public static final ResultVO SUCCESS = new ResultVO(1, "success");

    private ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public ResultVO setMessage(String message) {
        this.message = message;
        return this;
    }
}
