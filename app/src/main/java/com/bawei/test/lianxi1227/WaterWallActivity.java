package com.bawei.test.lianxi1227;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class WaterWallActivity extends AppCompatActivity {
    private RecyclerView re;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_wall);
        re=(RecyclerView)findViewById(R.id.re);
        re.setLayoutManager(new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL));
        MyAdapter myAdapter=new MyAdapter();
        re.setAdapter(myAdapter);
    }
    class MyViewHolder extends  RecyclerView.ViewHolder{
        public final  ImageView iv;
        public MyViewHolder(View itemView) {
            super(itemView);
          iv=(ImageView)itemView.findViewById(R.id.iv);
        }
    }
    class MyAdapter extends RecyclerView.Adapter<MyViewHolder>{
   private final ArrayList<Integer> heightList;
     public MyAdapter(){
       heightList=new ArrayList<>();
       /*    Random  random=new Random();
         for (int i = 0; i < Images.imageUrls.length; i++) {
             int height=100+random.nextInt(300);
              heightList.add(height);
         }*/
     }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
         View   view= View.inflate(WaterWallActivity.this,R.layout.waterwall,null);
          MyViewHolder  myViewHolder=new   MyViewHolder(view);
            return myViewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            //获取布局参数
            ViewGroup.LayoutParams layoutParams = holder.iv.getLayoutParams();
            //重新设置高度
            layoutParams.height=heightList.get(position);
            Log.i("AAAA-----","-------"+heightList.get(position));
            //重新设置布局参数
            holder.iv.setLayoutParams(layoutParams);

            //Glide展示
            //   Glide.with(WaterWallActivity.this).load(Images.imageUrls[position]).into(holder.iv);
            Glide.with(WaterWallActivity.this).load(Images.imageUrls[position]).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(holder.iv);

        }

        @Override
        public int getItemCount() {
            return Images.imageUrls.length;
        }
    }
}
