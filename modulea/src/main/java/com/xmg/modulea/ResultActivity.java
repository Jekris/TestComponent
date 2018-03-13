package com.xmg.modulea;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {
    private TextView mTvShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        mTvShow = (TextView) findViewById(R.id.tv_show);

        mTvShow.setText("点开通知了");
    }
}
