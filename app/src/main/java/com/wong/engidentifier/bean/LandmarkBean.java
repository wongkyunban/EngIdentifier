package com.wong.engidentifier.bean;

import java.io.Serializable;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午6:09
 * @version 1.0
 */
public class LandmarkBean extends MyObject implements Serializable {

    /**
     * log_id : 3450013152046070669
     * result : {"landmark":"狮身人面像"}
     */

    private long log_id;
    private ResultBean result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * landmark : 狮身人面像
         */

        private String landmark;

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }
    }
}
