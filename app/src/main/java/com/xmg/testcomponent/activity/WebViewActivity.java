package com.xmg.testcomponent.activity;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.xmg.testcomponent.R;

@Route(path = "/test/webview")
public class WebViewActivity extends Activity {
    private TextView mTvContent;
    private WebView mWebview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        mTvContent = (TextView) findViewById(R.id.tv_content);
        mWebview = (WebView) findViewById(R.id.webview);

        String url = getIntent().getStringExtra("url");
//        mWebview.loadUrl(url);

        mTvContent.setText(url);

    }
}
