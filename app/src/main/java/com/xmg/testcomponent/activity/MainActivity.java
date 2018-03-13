package com.xmg.testcomponent.activity;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.xmg.testcomponent.R;
import com.xmg.testcomponent.bean.Person;
import com.xmg.testcomponent.fragment.IndexFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mBtnSimple;
    private Button mBtnJumpWithData;
    private Button mBtnModuleSimple;
    private Button mBtnUrl;
    private Button mBtnFragment;
    private Button mBtnAnimStandard;
    private Button mBtnAnimHighversion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        mBtnSimple = (Button) findViewById(R.id.btn_simple);
        mBtnJumpWithData = (Button) findViewById(R.id.btn_jump_with_data);
        mBtnModuleSimple = (Button) findViewById(R.id.btn_module_simple);
        mBtnUrl = (Button) findViewById(R.id.btn_url);
        mBtnFragment = (Button) findViewById(R.id.btn_fragment);
        mBtnAnimStandard = (Button) findViewById(R.id.btn_anim_standard);
        mBtnAnimHighversion = (Button) findViewById(R.id.btn_anim_highversion);


        mBtnSimple.setOnClickListener(this);
        mBtnJumpWithData.setOnClickListener(this);
        mBtnModuleSimple.setOnClickListener(this);
        mBtnUrl.setOnClickListener(this);
        mBtnFragment.setOnClickListener(this);
        mBtnAnimStandard.setOnClickListener(this);
        mBtnAnimHighversion.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_simple:
                // 1. 应用内简单的跳转(通过URL跳转在'进阶用法'中)
                ARouter.getInstance().build("/test/login").navigation();
                break;

            case R.id.btn_jump_with_data:
                //2. 应用内带参数跳转
                Person p = new Person("10001", "Jina", 24, "Canada");

                ARouter.getInstance().build("/test/home")
                        .withBoolean("flag", false)
                        .withDouble("double", 3.1415d)
                        .withObject("person", p)
                        .navigation();
                break;

            case R.id.btn_module_simple:
                //3. 跳转到module
                ARouter.getInstance().build("/module/activity1")
                        .withString("data", "这是app MainActivity 传出的数据")//传值，根据具体需要来设置
                        .navigation();
                break;

            case R.id.btn_url:
                //4. 使用url跳转
//                ARouter.getInstance().build("/test/webview")
//                    .withString("url", "file:///android_asset/Autowired.html")
//                    .navigation();

                //使用uri跳转
                Uri uri = Uri.parse("xmg://com.text.uri/test/webview");
                ARouter.getInstance().build(uri).withString("url","this is a test uri!!!").navigation();


                break;

            case R.id.btn_fragment:
                //5. 获取fragment实例
                IndexFragment fragment = (IndexFragment) ARouter.getInstance().build("/test/indexFragment")
                        .navigation();

                getSupportFragmentManager().beginTransaction().add(R.id.fl_contain, fragment, "index").commit();

                Toast.makeText(this, "找到Fragment:" + fragment.toString(), Toast.LENGTH_SHORT).show();
                Log.i("TAG", "IndexFragment==" + fragment.toString());
                break;

            case R.id.btn_anim_standard:
                //6. 低版本转场动画
                //navigation()这里要使用上下文，不然动画效果会无法显示。
                ARouter.getInstance().build("/test/login")
                        .withTransition(R.anim.pre_in, R.anim.pre_out).navigation(this);
                break;

            case R.id.btn_anim_highversion:
                //7. 高版本转场动画
                if (Build.VERSION.SDK_INT >= 16) {

                    ActivityOptionsCompat compat = ActivityOptionsCompat.
                            makeScaleUpAnimation(v, v.getWidth() / 2, v.getHeight() / 2, 0, 0);
                    ARouter.getInstance().build("/test/login")
                            .withOptionsCompat(compat).navigation();
                } else {
                    Toast.makeText(this, "API < 16,不支持新版本动画", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }
}
