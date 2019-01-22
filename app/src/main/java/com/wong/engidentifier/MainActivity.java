package com.wong.engidentifier;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.wong.engidentifier.baidu.FileUtil;
import com.wong.engidentifier.bean.AdvancedBean;
import com.wong.engidentifier.bean.AnimalBean;
import com.wong.engidentifier.bean.CarBean;
import com.wong.engidentifier.bean.DishBean;
import com.wong.engidentifier.bean.PlantBean;
import com.wong.engidentifier.bean.TagBean;
import com.wong.engidentifier.camera.CameraUtils;
import com.wong.engidentifier.object.AdvancedGeneralObjectDetect;
import com.wong.engidentifier.object.AnimalDetect;
import com.wong.engidentifier.object.CarDetect;
import com.wong.engidentifier.object.DetectiveObjectType;
import com.wong.engidentifier.object.DishDetect;
import com.wong.engidentifier.object.FlowerDetect;
import com.wong.engidentifier.object.IngredientDetect;
import com.wong.engidentifier.object.LandmarkDetect;
import com.wong.engidentifier.object.LogoDetect;
import com.wong.engidentifier.object.ObjectDetect;
import com.wong.engidentifier.object.PlantDetect;
import com.wong.engidentifier.object.ResultCallback;
import com.wong.engidentifier.utils.FileUtils;

import org.apache.log4j.chainsaw.Main;

import java.io.File;


public class MainActivity extends BaseActivity implements View.OnClickListener {


    public static final String TAG = "MainActivity";
    public static final int REQUEST_CODE_TAKE_PICTURE = 1;
    public static final String DATA_BEAN = "dataBean";

    private ImageView mIvPic;
    private Button mBtnTakePicture;

    private TagBean tagBean;

    public static void startActivity(Context context, TagBean bean) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(DATA_BEAN, bean);
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initDatas();

        initViews();

        initEvents();
    }

    private void initDatas() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tagBean = (TagBean) bundle.getSerializable(DATA_BEAN);
        }
    }

    private void initViews() {
        mIvPic = (ImageView) findViewById(R.id.iv_pic);
        mBtnTakePicture = (Button) findViewById(R.id.btn_take_a_shot);
    }

    private void initEvents() {
        mBtnTakePicture.setOnClickListener(this);

    }

    private File takePicFile;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_CODE_TAKE_PICTURE:
                if (resultCode == RESULT_OK && data != null) {

                    Bitmap bm = CameraUtils.getPicture(data);
                    if (bm != null) {
                        String fileName = FileUtils.generateFileName("jpg");
                        CameraUtils.saveBitmapIntoExternal(MainActivity.this, fileName, bm);

                        mIvPic.setImageBitmap(bm);

                        takePicFile = FileUtils.getSmartAppCacheFile(MainActivity.this, fileName);

                        detectPic(takePicFile);


                    }

                }
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_take_a_shot:
                clear();
                CameraUtils.openSysCamera(MainActivity.this, REQUEST_CODE_TAKE_PICTURE);
                break;
        }
    }

    private TranslateHandler translateHandler = new TranslateHandler();
    class TranslateHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            String rs = (String)msg.obj;
            if (tagBean != null) {
                switch (tagBean.getType()) {
                    case DetectiveObjectType
                            .TYPE_ADVANCED_GENERAL_OBJECT:
                        AdvancedBean advancedBean = (new Gson()).fromJson(rs,AdvancedBean.class);

                        if(advancedBean != null && advancedBean.getResult().size() > 0 && takePicFile != null){
                            TranslationActivity.startActivity(MainActivity.this,advancedBean,takePicFile);
                        }


                        break;
                    case DetectiveObjectType.TYPE_ANIMAL:
                        AnimalBean animalBean = (new Gson()).fromJson(rs,AnimalBean.class);
                        if(animalBean != null && animalBean.getResult().size() > 0 && takePicFile != null){
                            TranslationActivity.startActivity(MainActivity.this,animalBean,takePicFile);
                        }

                        break;
                    case DetectiveObjectType.TYPE_CAR:
                        CarBean carBean = (new Gson()).fromJson(rs,CarBean.class);
                        if(carBean != null && carBean.getResult().size() > 0 && takePicFile != null){
                            TranslationActivity.startActivity(MainActivity.this,carBean,takePicFile);
                        }

                        break;

                    case DetectiveObjectType.TYPE_DISH:
                        DishBean dishBean = (new Gson()).fromJson(rs,DishBean.class);
                        if(dishBean != null && dishBean.getResult().size() > 0 && takePicFile != null){
                            TranslationActivity.startActivity(MainActivity.this,dishBean,takePicFile);
                        }

                        break;

                    case DetectiveObjectType.TYPE_FLOWER:


                        break;

                    case DetectiveObjectType.TYPE_INGREDIENT:

                        break;
                    case DetectiveObjectType.TYPE_LANDMARK:

                        break;
                    case DetectiveObjectType.TYPE_LOGO:

                        break;
                    case DetectiveObjectType.TYPE_OBJECT:

                        break;
                    case DetectiveObjectType.TYPE_PLANT:
                        PlantBean plantBean = (new Gson()).fromJson(rs,PlantBean.class);
                        if(plantBean != null && plantBean.getResult().size() > 0 && takePicFile != null){
                            TranslationActivity.startActivity(MainActivity.this,plantBean,takePicFile);
                        }

                        break;
                }
            }

        }
    }


    private ResultCallback callback = new ResultCallback() {
        @Override
        public void onResultCallback(String rs) {

            if(!TextUtils.isEmpty(rs)){
                Message msg = translateHandler.obtainMessage();
                msg.obj = rs;
                translateHandler.handleMessage(msg);
            }

        }
    };

    private void detectPic(final File file) {
        if (file != null) {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    if (tagBean != null) {
                        switch (tagBean.getType()) {
                            case DetectiveObjectType
                                    .TYPE_ADVANCED_GENERAL_OBJECT:
                                AdvancedGeneralObjectDetect.detect(file.getAbsolutePath(),callback);
                                break;
                            case DetectiveObjectType.TYPE_ANIMAL:
                                AnimalDetect.detect(file.getAbsolutePath(),callback);

                                break;
                            case DetectiveObjectType.TYPE_CAR:
                                CarDetect.detect(file.getAbsolutePath(),callback);

                                break;

                            case DetectiveObjectType.TYPE_DISH:
                                DishDetect.detect(file.getAbsolutePath(),callback);

                                break;

                            case DetectiveObjectType.TYPE_FLOWER:

                                FlowerDetect.detect(file.getAbsolutePath(),callback);

                                break;

                            case DetectiveObjectType.TYPE_INGREDIENT:
                                IngredientDetect.detect(file.getAbsolutePath(),callback);

                                break;
                            case DetectiveObjectType.TYPE_LANDMARK:
                                LandmarkDetect.detect(file.getAbsolutePath(),callback);

                                break;
                            case DetectiveObjectType.TYPE_LOGO:
                                LogoDetect.detect(file.getAbsolutePath(),callback);

                                break;
                            case DetectiveObjectType.TYPE_OBJECT:
                                ObjectDetect.detect(file.getAbsolutePath(),callback);

                                break;
                            case DetectiveObjectType.TYPE_PLANT:
                                PlantDetect.detect(file.getAbsolutePath(),callback);

                                break;
                        }
                    }
                }
            }).start();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);


    }


    private void clear() {
        File cache = FileUtils.getSmartAppCacheDir(MainActivity.this);
        FileUtils.clear(cache);
    }

}
