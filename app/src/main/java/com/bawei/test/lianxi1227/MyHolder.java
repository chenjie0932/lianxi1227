package com.bawei.test.lianxi1227;

import android.view.View;
import android.widget.TextView;

/**
 * Created by johpo on 2016/12/27 0027.
 */
public class MyHolder extends BaseHolder {
    public final TextView text1;


    public MyHolder(View itemView) {
        super(itemView);
       text1=(TextView) itemView.findViewById(android.R.id.text1);
    }

}
