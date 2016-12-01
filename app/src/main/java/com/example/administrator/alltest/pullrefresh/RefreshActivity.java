package com.example.administrator.alltest.pullrefresh;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.administrator.alltest.R;
import com.example.administrator.alltest.toast.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class RefreshActivity extends AppCompatActivity {

    private List<String> list;
    private MyListViewAdapter adapter;
    private ListView listView;

    private Handler handler;

    //可见的最后一个下标
    private int visiableLastIndex;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        init();
    }

    private void init(){
        handler=new Handler();

        listView= (ListView) findViewById(R.id.refresh_listView);
        list=new ArrayList<>();
        for(int i=0;i<20;i++){
            list.add("你是"+i+"号吗？");
        }
        adapter=new MyListViewAdapter(this,list);

        //在适配之前添加底部View
//        addBottomView(listView);
        //适配
        listView.setAdapter(adapter);
        listView.setOnScrollListener(onScrollListener);
    }


    int sum=0;
    //滑动状态监听
    private AbsListView.OnScrollListener onScrollListener=new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView absListView, int i) {
            //实际最后一条item的下标
            int lastIndex=adapter.getCount()-1;
            //判断当滑动静止时，可见最后一条item是否是实际上最后一个item下标
            if(i==SCROLL_STATE_IDLE&&visiableLastIndex==lastIndex){
                sum++;
                if(sum>5){
                    ToastUtil.showToast(getApplicationContext(),"没有更多了！");
                    return;
                }
                loadMoreData();
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onScroll(AbsListView absListView, int i, int i1, int i2) {
            visiableLastIndex=i+i1-1;
        }
    };

    //添加数据
    private void loadMoreData(){
        for(int i=0;i<5;i++){
            list.add(i+"，你是新来的吧！");
        }
    }

//    ####################################################
    //在listView的底部添加一个view的方法，注意：方法要在setAdapter方法之前调用
    private void addBottomView(ListView listView){
        //加载bottomView
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.refresh_list_bottomview,null);
        //添加到listView底部
        listView.addFooterView(view);

        final TextView tv= (TextView) view.findViewById(R.id.refresh_list_btn);
        final ProgressBar pb= (ProgressBar) view.findViewById(R.id.refresh_list_pb);
        //btn的点击事件
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setVisibility(View.INVISIBLE);
                pb.setVisibility(View.VISIBLE);
                //模拟网络请求需要时间，然后用handler发送一个延迟线程
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        //加载数据
                        loadMoreData();
                        tv.setVisibility(View.VISIBLE);
                        pb.setVisibility(View.INVISIBLE);
                        //adapter刷新
                        adapter.notifyDataSetChanged();
                    }
                },3000);
            }
        });
    }

    //////////////////////////////////////////////////////
    //动态设置listview的高度，这里未用，在scrollView嵌套的时候会用到
    public void setListViewHeight(ListView listView){
        //获取listView对应的adapter
        ListAdapter listAdapter= listView.getAdapter();
        //判断是否为空
        if(listAdapter==null){
            return;
        }

        int totalHeight=0;
        //listAdpter.getCount()获得数据项的数目
        for(int i=0;i<listAdapter.getCount();i++){
            //获得每项的view
            View itemView=listAdapter.getView(i,null,listView);
            //计算每一项的高度
            itemView.measure(View.MeasureSpec.UNSPECIFIED,View.MeasureSpec.UNSPECIFIED);
            //子项的总高度
            totalHeight=totalHeight+itemView.getMeasuredHeight();
        }

        //拿到listView的布局参数
        ViewGroup.LayoutParams params=listView.getLayoutParams();
        //在高度上添加子项间分割部分占用的高度
        params.height=totalHeight+(listView.getDividerHeight()*(listAdapter.getCount()-1));
        //重新设置布局参数
        listView.setLayoutParams(params);
    }
}
