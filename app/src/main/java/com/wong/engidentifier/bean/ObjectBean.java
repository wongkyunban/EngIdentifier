package com.wong.engidentifier.bean;

import java.io.Serializable;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午6:35
 * @version 1.0
 */
public class ObjectBean extends MyObject implements Serializable {

    /**
     * log_id : 3056460243959486645
     * result : {"width":63,"top":96,"left":70,"height":96}
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

    public static class ResultBean {
        /**
         * width : 63
         * top : 96
         * left : 70
         * height : 96
         */

        private int width;
        private int top;
        private int left;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getTop() {
            return top;
        }

        public void setTop(int top) {
            this.top = top;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
