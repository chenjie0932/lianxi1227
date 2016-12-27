package com.bawei.test.lianxi1227;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by johpo on 2016/12/27 0027.
 */
public class MyRecyclerAdapter extends RecyclerView.Adapter<BaseHolder> {
    private final Context context;
    private final ArrayList<String> al;
    private static final int TYPE_LOAD_MORE = 1;
    private static final int TYPE_NORMAL = 0;
    public   MyRecyclerAdapter(Context context, ArrayList<String> al){
      this.context=context;
      this. al=al;
  }

    @Override
    public int getItemViewType(int position) {
        if (position==al.size()){
        return  TYPE_LOAD_MORE;
        }
        return TYPE_NORMAL;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View view=null;
        BaseHolder mHolder=null;

        switch (viewType){
            case TYPE_NORMAL:
            view=View.inflate(context,android.R.layout.simple_list_item_1,null);
             mHolder=new  MyHolder(view);
                break;
            case TYPE_LOAD_MORE:
                view = View.inflate(context, R.layout.loadmore, null);
                mHolder = new MoreHolder(view);
                break;
        }

        return mHolder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
      if (position<al.size()){
          MyHolder myHolder=(MyHolder) holder;
          myHolder.text1.setText(al.get(position) );
      }

    }

    @Override
    public int getItemCount() {
        return al.size()+1;
    }
}
