package com.wong.engidentifier;

import android.graphics.Color;
import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.wong.engidentifier.adapter.MainMenuRecyclerViewAdapter;
import com.wong.engidentifier.annotation.MyBindView;
import com.wong.engidentifier.annotation.MyButterKnife;
import com.wong.engidentifier.bean.TagBean;
import com.wong.engidentifier.object.DetectiveObjectType;

import java.util.ArrayList;
import java.util.List;

public class MainMenuActivity extends BaseActivity {

    @MyBindView(R.id.main_menu)
    RecyclerView mRecyclerView;

    private List<TagBean> list;
    private MainMenuRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        MyButterKnife.bind(this);

        initDatas();
        initRecyclerView();

    }


    private void initDatas(){

        list = new ArrayList<>();


        list.add(new TagBean("植物",DetectiveObjectType.TYPE_PLANT,R.mipmap.plant));
//        list.add(new TagBean("物体",DetectiveObjectType.TYPE_OBJECT,R.mipmap.objects));
//        list.add(new TagBean("标志",DetectiveObjectType.TYPE_LOGO,R.mipmap.logo));
//        list.add(new TagBean("地标",DetectiveObjectType.TYPE_LANDMARK,R.mipmap.landmark));
//        list.add(new TagBean("食材",DetectiveObjectType.TYPE_INGREDIENT,R.mipmap.vegetables));
//        list.add(new TagBean("花卉",DetectiveObjectType.TYPE_FLOWER,R.mipmap.flower));
        list.add(new TagBean("菜品",DetectiveObjectType.TYPE_DISH,R.mipmap.dish));
        list.add(new TagBean("汽车",DetectiveObjectType.TYPE_CAR,R.mipmap.car));
        list.add(new TagBean("动物",DetectiveObjectType.TYPE_ANIMAL,R.mipmap.animal));
        list.add(new TagBean("通用",DetectiveObjectType.TYPE_ADVANCED_GENERAL_OBJECT,R.mipmap.all));
    }

    private static final int MAX = 9;

    private void initRecyclerView(){
        GridLayoutManager layoutManage = new GridLayoutManager(MainMenuActivity.this, 2);
        mRecyclerView.setLayoutManager(layoutManage);
        adapter = new MainMenuRecyclerViewAdapter(list);
        mRecyclerView.setAdapter(adapter);

    }


}
