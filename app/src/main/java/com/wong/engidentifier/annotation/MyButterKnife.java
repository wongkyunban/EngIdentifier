package com.wong.engidentifier.annotation;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.view.View;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static java.lang.reflect.Modifier.PRIVATE;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 上午11:15
 * @version 1.0
 */
public class MyButterKnife {
    @NonNull
    @UiThread
    public static final void bind(@NonNull Activity target) {

        View sourceView = target.getWindow().getDecorView();

        bind(target, sourceView);


    }

    @NonNull
    @UiThread
    public static void bind(@NonNull Object target, @NonNull View source) {
        Class<?> targetClass = target.getClass();
        Field[] fArray = targetClass.getDeclaredFields();
        for (Field field : fArray) {
            boolean fExist = field.isAnnotationPresent(MyBindView.class);
            if (!fExist) {
                continue;
            }
            MyBindView myBindView = field.getAnnotation(MyBindView.class);
            int idx = myBindView.value();


            if ((field.getModifiers() & PRIVATE) != 0) {
                throw new IllegalArgumentException(targetClass.getName() + " must not be private.");
            }

            try {
                field.setAccessible(true);
                field.set(target, source.findViewById(idx));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }


        }
    }


}





