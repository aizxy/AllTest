package com.example.administrator.alltest.okhttp_mvp_eventbus;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.administrator.alltest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookActivity extends AppCompatActivity implements BookView{

    @BindView(R.id.book_listView)
    ListView listView;
    @BindView(R.id.book_progressBar)
    ProgressBar progressBar;


    private ListBookAdapter adapter;
    private BookPresenter bookPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        bookPresenter=new BookPresenter(this);
        ButterKnife.bind(this);
        showProgressBar();
    }

    @Override
    public void showDatas(List<Book> list) {
        showProgressBar();
        adapter=new ListBookAdapter(this,list);
        listView.setAdapter(adapter);
        hidenProgressBar();
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
        listView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hidenProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
        listView.setVisibility(View.VISIBLE);
    }
}
