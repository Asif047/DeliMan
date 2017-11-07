package com.deliman_poc.rokomari.deliman_rokomari_poc;

/**
 * Created by DEVPC on 11/7/2017.
 */

public class PostResponseApi {

    private int code;
    private String http_verb;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getHttp_verb() {
        return http_verb;
    }

    public void setHttp_verb(String http_verb) {
        this.http_verb = http_verb;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
