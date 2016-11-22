package com.example.administrator.alltest.okhttp_mvp_eventbus;

import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public interface BookView {

    void showDatas(List<Book> list);
    void showProgressBar();
    void hidenProgressBar();
}
