package com.wong.engidentifier.bean;

import com.wong.engidentifier.annotation.Column;
import com.wong.engidentifier.annotation.Table;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 上午10:51
 * @version 1.0
 */

@Table("Info")
public class Info {


    /**
     * source : value
     * target : value
     */

    @Column("source")
    private String source;
    @Column("target")
    private String target;

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
