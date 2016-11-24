package com.example.administrator.alltest.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.alltest.R;

/**
 * Created by Administrator on 2016/11/24 0024.
 */

public class TaobaoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View taobaoLayout=inflater.inflate(R.layout.fragment_taobao,container,false);
        return taobaoLayout;
    }
}
