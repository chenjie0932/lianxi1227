package com.bawei.test.lianxi1227;

import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;

public class RefershActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{

    private SwipeRefreshLayout sw;
    private RecyclerView recy;
   ArrayList<String> al=new  ArrayList<>();
    private int index=0;
    private int MAX_COUNT=20;
    private MyRecyclerAdapter myRecyclerAdapter;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    sw.setRefreshing(false);
                    break;
            }
                    //展示数据
                    if(myRecyclerAdapter==null){
                        myRecyclerAdapter=new MyRecyclerAdapter(RefershActivity.this,al);
                        recy.setAdapter(myRecyclerAdapter);
                    }else{
                        myRecyclerAdapter.notifyDataSetChanged();
                    }
        }
    };
    private LinearLayoutManager linearLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refersh);
        //注意---linearLayoutManager抽取出来
        recy=(RecyclerView) findViewById(R.id.recy);
        linearLayoutManager = new LinearLayoutManager(this);
        recy.setLayoutManager(linearLayoutManager);
        sw=(SwipeRefreshLayout)findViewById(R.id.sw);
        recy.setLayoutManager(new LinearLayoutManager(this));
        sw.setColorSchemeColors(Color.BLUE,Color.GREEN,Color.RED);
       // sw.setRefreshing(true);
        sw.post(new Runnable() {
            @Override
            public void run() {
                sw.setRefreshing(true);
                refreshData();
            }
        });
       sw.setOnRefreshListener(this);

        //对recycleView添加一个滑动的监听
        recy.addOnScrollListener(new OnLoadMoreListener(linearLayoutManager) {
            @Override
            public void onloadMore() {
                Toast.makeText(RefershActivity.this, "加载啦", Toast.LENGTH_SHORT).show();
                loadMoreData();
            }
        });

    }

    @Override
    public void onRefresh() {
        Toast.makeText(this,"刷新了",Toast.LENGTH_SHORT).show();
        refreshData();

    }
    public void refreshData(){
 new Thread(){
     @Override
     public void run() {
         super.run();

         try {
             sleep(3000);
         } catch (InterruptedException e) {
             e.printStackTrace();
         }
          index=0;
         al.clear();
         loadData();
         handler.sendEmptyMessage(0);
     }
 }.start();
    }



    public void loadData(){
        for (int i = index; i < index+MAX_COUNT; i++) {
            al.add("我是第"+i+"条");
        }
    }
    /**
     * 加载更多
     */
    public void loadMoreData() {
        new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //设置当前条目的索引值
                index = index + MAX_COUNT;
                loadData();
                //把数据发送给主线程
                handler.sendEmptyMessage(1);

            }
        }.start();
    }
}
