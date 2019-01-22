package com.wong.engidentifier.bean;

import java.io.Serializable;

import javax.crypto.SealedObject;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午3:27
 * @version 1.0
 */
public class TagBean implements Serializable {

    /**
     * title : title
     * type : type
     */

    private String title;
    private String type;
    private int resourceId;

    public TagBean(String title,String type,int resourceId){
        this.title = title;
        this.type = type;
        this.resourceId = resourceId;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getResourceId() {
        return resourceId;
    }

    public void setResourceId(int resourceId) {
        this.resourceId = resourceId;
    }
}
