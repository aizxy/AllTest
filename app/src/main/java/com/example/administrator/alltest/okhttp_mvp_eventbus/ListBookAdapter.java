package com.example.administrator.alltest.okhttp_mvp_eventbus;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.alltest.R;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2016/11/22 0022.
 */

public class ListBookAdapter extends BaseAdapter {

    private List<Book> list;
    private LayoutInflater layoutInflater;
    private Context context;

    public ListBookAdapter(Context context,List<Book> list){
        this.context=context;
        this.list=list;
        layoutInflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if(view==null){
            view=layoutInflater.inflate(R.layout.listview_item,null);
            vh=new ViewHolder();
            vh.author= (TextView) view.findViewById(R.id.listview_author);
            vh.price= (TextView) view.findViewById(R.id.listview_price);
            vh.title= (TextView) view.findViewById(R.id.listview_title);
            vh.time= (TextView) view.findViewById(R.id.listview_time);
            vh.publisher= (TextView) view.findViewById(R.id.listview_publisher);
            vh.img= (ImageView) view.findViewById(R.id.listview_iv);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        Book one=list.get(i);
        vh.author.setText(one.getAuthor());
        vh.title.setText(one.getTitle());
        vh.time.setText(one.getUpdatedAt());
        vh.publisher.setText(one.getPublisher());
        vh.price.setText(one.getPrice());
        setIcon(one.getImg(),vh.img);
        return view;
    }

    class ViewHolder{
        TextView author,title,time,publisher,price;
        ImageView img;
    }

    //网络下载图片，设置ImageView
    private void setIcon(final String url,final ImageView im){
        final Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                if(msg.what==2){
                    Bitmap bm= (Bitmap) msg.obj;
                    im.setImageBitmap(bm);
                }
            }
        };
        new Thread(){
            @Override
            public void run() {
                try {
                    URL path=new URL(url);
                    HttpURLConnection http= (HttpURLConnection) path.openConnection();
                    InputStream in=http.getInputStream();
                    Bitmap bitmap= BitmapFactory.decodeStream(in);
                    in.close();
                    Message msg=new Message();
                    msg.what=2;
                    msg.obj=bitmap;
                    handler.sendMessage(msg);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
