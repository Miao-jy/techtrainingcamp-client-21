package com.group21.noticeboard.domain;

/**
 * 服务器获取到的文章
 */
public class ArticleResponse {
    private String code;
    private String message;
    private String data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
