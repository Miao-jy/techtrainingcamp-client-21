package com.group21.noticeboard.domain;

/**
 * 公告板中一条具体信息
 */
public class Message {

    private String id;
    private String title;
    private String author;
    private String publishTime;
    private int type;
    private int coverId;
    private int[] covers;

    /**
     * 无图片类型信息
     */
    public Message(String id, String title, String author, String publishTime, int type) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
    }

    /**
     * 有一张图片类型信息
     */
    public Message(String id, String title, String author, String publishTime, int type, int coverId) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
        this.coverId = coverId;
    }

    /**
     * 有多张图片类型信息
     */
    public Message(String id, String title, String author, String publishTime, int type, int[] covers) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publishTime = publishTime;
        this.type = type;
        this.covers = covers;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public int getType() {
        return type;
    }

    public int getCoverId() {
        return coverId;
    }

    public int[] getCovers() {
        return covers;
    }
}
