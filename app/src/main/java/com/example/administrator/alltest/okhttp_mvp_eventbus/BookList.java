package com.example.administrator.alltest.okhttp_mvp_eventbus;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookList {
    private boolean success;
    private String error;
    private List<Book> data;

    public BookList(boolean success, String error, List<Book> data) {
        this.success = success;
        this.error = error;
        this.data = data;
    }

    public BookList() {
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Book> getData() {
        return data;
    }

    public void setData(List<Book> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BookList{" +
                "success=" + success +
                ", error='" + error + '\'' +
                ", data=" + data +
                '}';
    }
}
