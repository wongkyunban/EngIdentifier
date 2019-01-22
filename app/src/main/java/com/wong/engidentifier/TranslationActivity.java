package com.wong.engidentifier;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.wong.engidentifier.adapter.TranslationRecyclerViewAdapter;
import com.wong.engidentifier.annotation.MyBindView;
import com.wong.engidentifier.annotation.MyButterKnife;
import com.wong.engidentifier.bean.AdvancedBean;
import com.wong.engidentifier.bean.AnimalBean;
import com.wong.engidentifier.bean.CarBean;
import com.wong.engidentifier.bean.DishBean;
import com.wong.engidentifier.bean.MyObject;
import com.wong.engidentifier.bean.PlantBean;
import com.wong.engidentifier.bean.TranslationResultBean;
import com.wong.engidentifier.language.OkHttpGet;
import com.wong.engidentifier.language.TransApi;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class TranslationActivity extends BaseActivity {

    public static final String OBJECT_KEY = "key";
    public static final String File_KEY = "picFile";


    public static void startActivity(Context context, MyObject object, File file) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(OBJECT_KEY, object);
        bundle.putSerializable(File_KEY, file);
        Intent intent = new Intent();
        intent.putExtras(bundle);
        intent.setClass(context, TranslationActivity.class);
        context.startActivity(intent);

    }

    private MyObject object;

    private File picFile;

    @MyBindView(R.id.iv_translate_img)
    ImageView imageView;

    @MyBindView(R.id.rv_translate)
    RecyclerView recyclerView;

    private List<TranslationResultBean.TransResultBean> datas = new ArrayList<>();

    private TranslationRecyclerViewAdapter adapter;


    private final static int UPDATE_EVENT = 1;
    private TranslationActivityHandler myHandler = new TranslationActivityHandler();

    class TranslationActivityHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case UPDATE_EVENT:
                    String s = (String) msg.obj;
                    if(!TextUtils.isEmpty(s)){
                        TranslationResultBean bean = new Gson().fromJson(s,TranslationResultBean.class);
                        if(bean.getTrans_result() == null){
                            return;
                        }
                        datas.addAll(bean.getTrans_result());

                        adapter.notifyItemChanged(datas.size() -1);

                        try {
//                            String rs = bean.getTrans_result().get(0).getSrc();

//                            Toast.makeText(TranslationActivity.this, rs, Toast.LENGTH_LONG).show();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }


                    break;
            }
        }
    }


    private void sendCommand(int i, Object object) {
        Message msg = myHandler.obtainMessage();
        msg.what = i;
        msg.obj = object;
        myHandler.sendMessage(msg);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translation);
        MyButterKnife.bind(this);
        initData();

        initViews();

        initRecyclerView();

    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            object = (MyObject) bundle.getSerializable(OBJECT_KEY);
            picFile = (File) bundle.getSerializable(File_KEY);
            initTranslate();
        }
    }

    private void initViews() {
        if (picFile != null) {
            imageView.setImageURI(Uri.fromFile(picFile));
        }

    }

    private void initRecyclerView(){
        adapter = new TranslationRecyclerViewAdapter(datas);
        LinearLayoutManager layoutManage = new LinearLayoutManager(TranslationActivity.this);
        recyclerView.setLayoutManager(layoutManage);
        recyclerView.setAdapter(adapter);
    }
    private void initTranslate() {
        if (object instanceof AdvancedBean) {

            AdvancedBean advancedBean = (AdvancedBean) object;
            for (AdvancedBean.ResultBean resultBean : advancedBean.getResult()) {

                translate(resultBean);

            }

        } else if (object instanceof AnimalBean) {

        } else if (object instanceof CarBean) {

        } else if (object instanceof DishBean) {

        } else if (object instanceof PlantBean) {

        }
    }

    private void translate(final AdvancedBean.ResultBean resultBean) {

        TransApi.translate(resultBean.getKeyword(), new OkHttpGet.OkCallback() {
            @Override
            public void success(Call call, Response response) throws IOException {
                String result;
                if (response != null) {
                    ResponseBody responseBody = response.body();
                    if (responseBody != null) {
                        result = responseBody.string();
                        sendCommand(UPDATE_EVENT, result);
                    }
                }

            }

            @Override
            public void failed(Call call, IOException e) {

            }
        });

    }
}
