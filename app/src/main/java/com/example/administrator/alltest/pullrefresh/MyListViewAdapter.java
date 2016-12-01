package com.example.administrator.alltest.pullrefresh;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.administrator.alltest.R;

import java.util.List;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class MyListViewAdapter extends BaseAdapter{

    private List<String> datas;
    private LayoutInflater layoutInflater;
    private Context context;

    public MyListViewAdapter(Context context, List<String> datas) {
        this.context = context;
        this.datas = datas;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=layoutInflater.inflate(R.layout.refresh_item,null);
            vh=new ViewHolder(view);
            view.setTag(vh);
        }
        vh= (ViewHolder) view.getTag();
        String data=datas.get(i);
        vh.tv.setText(data);
        return view;
    }

    private class ViewHolder{
        TextView tv;
        Button btn;

        public ViewHolder(View view){
            tv= (TextView) view.findViewById(R.id.refresh_item_tv);
            btn= (Button) view.findViewById(R.id.refresh_item_btn);
        }
    }
}
