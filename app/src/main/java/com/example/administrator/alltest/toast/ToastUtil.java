package com.example.administrator.alltest.toast;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Administrator on 2016/11/21 0021.
 */

public class ToastUtil {

    //这样封装后，能够在连击按钮时，也只弹出一个toast。

    private static Toast toast;
    public static void showToast(Context context,String s){
        if(toast==null){
            toast=Toast.makeText(context,s,Toast.LENGTH_SHORT);
        }else{
            toast.setText(s);
        }
        toast.show();

    }
}
