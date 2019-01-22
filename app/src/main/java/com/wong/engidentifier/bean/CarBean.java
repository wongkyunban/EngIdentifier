package com.wong.engidentifier.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午5:57
 * @version 1.0
 */
public class CarBean extends MyObject implements Serializable {

    /**
     * log_id : 5192232926746421845
     * location_result : {"width":186,"top":59,"height":132,"left":0}
     * result : [{"score":0.99884402751923,"name":"奔驰s级","year":"2013-2017"},{"score":1.0661346459528E-4,"name":"奔驰e级","year":"2017"},{"score":7.738197746221E-5,"name":"奔驰c级","year":"2015-2017"},{"score":5.9781290474348E-5,"name":"莲花汽车竞速","year":"2008"},{"score":4.3751435441663E-5,"name":"中华骏捷","year":"2011-2015"}]
     * color_result : 黑色
     */

    private long log_id;
    private LocationResultBean location_result;
    private String color_result;
    private List<ResultBean> result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public LocationResultBean getLocation_result() {
        return location_result;
    }

    public void setLocation_result(LocationResultBean location_result) {
        this.location_result = location_result;
    }

    public String getColor_result() {
        return color_result;
    }

    public void setColor_result(String color_result) {
        this.color_result = color_result;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class LocationResultBean {
        /**
         * width : 186
         * top : 59
         * height : 132
         * left : 0
         */

        private int width;
        private int top;
        private int height;
        private int left;

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

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public int getLeft() {
            return left;
        }

        public void setLeft(int left) {
            this.left = left;
        }
    }

    public static class ResultBean implements Serializable{
        /**
         * score : 0.99884402751923
         * name : 奔驰s级
         * year : 2013-2017
         */

        private double score;
        private String name;
        private String year;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }
    }
}
