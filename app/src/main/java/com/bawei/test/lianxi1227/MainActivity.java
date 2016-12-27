package com.bawei.test.lianxi1227;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.tv_cardview).setOnClickListener(this);
        findViewById(R.id.tv_refresh).setOnClickListener(this);
        findViewById(R.id.tv_waterwall).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.tv_cardview:
                jump(CardviewActivity.class);
                break;
            case  R.id.tv_waterwall:
                jump(WaterWallActivity.class);
                break;
            case  R.id.tv_refresh:
                jump(RefershActivity.class);
                break;
        }
    }
    public void jump(Class c){
        Intent intent=new Intent(this,c);
        startActivity(intent);
    }
}
