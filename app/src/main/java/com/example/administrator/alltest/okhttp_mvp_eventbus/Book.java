package com.example.administrator.alltest.okhttp_mvp_eventbus;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class Book {

    private String author;
    private String createdAt;
    private String img;
    private String objectId;
    private String price;
    private String publisher;
    private String title;
    private String updatedAt;

    public Book() {
    }

    public Book(String author, String createdAt, String img, String objectId, String price, String publisher, String title, String updatedAt) {
        this.author = author;
        this.createdAt = createdAt;
        this.img = img;
        this.objectId = objectId;
        this.price = price;
        this.publisher = publisher;
        this.title = title;
        this.updatedAt = updatedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", img='" + img + '\'' +
                ", objectId='" + objectId + '\'' +
                ", price='" + price + '\'' +
                ", publisher='" + publisher + '\'' +
                ", title='" + title + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                '}';
    }
}
