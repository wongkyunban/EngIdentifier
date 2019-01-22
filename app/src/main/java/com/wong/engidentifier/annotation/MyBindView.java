package com.wong.engidentifier.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 上午11:11
 * @version 1.0
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyBindView {

    int value();
}
