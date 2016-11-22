package com.example.administrator.alltest.okhttp_mvp_eventbus;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class BookPresenter {

    private BookView bookView;
    private BookModel bookModel;
    public BookPresenter(BookView bookView){
        EventBus.getDefault().register(this);
        this.bookView=bookView;
        bookModel=BookModel.getBookModel();
        bookModel.asyncGetBook();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BookList bookList){
        if(bookList.isSuccess()){
            bookView.showDatas(bookList.getData());
        }
    }

}
