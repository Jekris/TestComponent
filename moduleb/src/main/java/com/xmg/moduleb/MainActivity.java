package com.xmg.moduleb;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;

@Route(path = "/test2/activity2")
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.moduleb_activity_main);



    }
}


