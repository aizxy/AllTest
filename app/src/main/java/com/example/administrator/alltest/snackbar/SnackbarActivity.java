package com.example.administrator.alltest.snackbar;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.administrator.alltest.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SnackbarActivity extends AppCompatActivity {

    @BindView(R.id.delete)
    Button button;

    @OnClick(R.id.delete)
    public void delete(){
        snackbarShow();
    }
    @BindView(R.id.conterver)
    RelativeLayout relativeLayout;

    View view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_snackbar);
        ButterKnife.bind(this);
        view= LayoutInflater.from(this).inflate(R.layout.snackbar_item,relativeLayout);
    }

    private void snackbarShow(){
        Snackbar.make(view,"delete sure?",Snackbar.LENGTH_LONG)
                .setAction("Undo", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        button.setBackgroundColor(Color.parseColor("#f0f000"));
                    }
                }).show();
    }
}
