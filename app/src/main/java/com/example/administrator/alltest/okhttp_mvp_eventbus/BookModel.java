package com.example.administrator.alltest.okhttp_mvp_eventbus;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookModel {

    private final OkHttpClient okHttpClient;
    private final EventBus eventBus;
    private final Gson gson;

    private static BookModel bookModel;
    private BookModel(){
        okHttpClient=new OkHttpClient.Builder().build();
        gson=new Gson();
        eventBus=EventBus.getDefault();
    }
    public static synchronized BookModel getBookModel(){
        if(bookModel==null){
            bookModel=new BookModel();
        }
        return bookModel;
    }

    private Call getBookCall(){
        String url="http://cloud.bmob.cn/5a3f440fff51573e/books";
        Request request=new Request.Builder()
                .get()
                .url(url)
                .build();
        return okHttpClient.newCall(request);
    }

    public void asyncGetBook(){
        Call call=getBookCall();
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String content=response.body().string();
                BookList bookList=gson.fromJson(content,BookList.class);
                eventBus.post(bookList);
            }
        });
    }
}
