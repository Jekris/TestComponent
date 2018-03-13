package com.xmg.testcomponent.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.xmg.testcomponent.R;
import com.xmg.testcomponent.bean.Person;

@Route(path = "/test/home")
public class HomeActivity extends AppCompatActivity {
    private TextView mHomeTvData;
    private TextView mHomeTvData2;
    private TextView mHomeTvData3;

    @Autowired
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        ARouter.getInstance().inject(this);

        init();
    }

    private void init() {
        mHomeTvData = (TextView) findViewById(R.id.home_tv_data);
        mHomeTvData2 = (TextView) findViewById(R.id.home_tv_data2);
        mHomeTvData3 = (TextView) findViewById(R.id.home_tv_data3);

        double aDouble = getIntent().getDoubleExtra("double", 0.00);
        boolean flag = getIntent().getBooleanExtra("flag", true);

        mHomeTvData.setText(aDouble + "");
        mHomeTvData2.setText(flag + "");

//        Log.i("TAG", "HomeActivity:aDouble" + aDouble + " \n flag=" + flag + " \n person=" + person.getId() + "  " +
//                person.getName() + "  " + person.getAge() + "  " + person.getAddress());
//

//        String json = ((JsonServiceImpl) ARouter.getInstance().build("/service/json").navigation()).object2Json(person);
//        Log.i("TAG", "json==" + json);

        String json = getIntent().getStringExtra("person");
        Person p  = new Gson().fromJson(json,Person.class);
        Log.i("TAG", "p==" + p.id + "  " +
                p.name + "  " + p.age + "  " + p.address);

        mHomeTvData3.setText( person.name + "  " + person.age + "  " + person.address);


    }
}
