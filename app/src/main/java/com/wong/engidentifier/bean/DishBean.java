package com.wong.engidentifier.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午6:00
 * @version 1.0
 */
public class DishBean extends MyObject implements Serializable {

    /**
     * log_id : 3726289145782285301
     * result_num : 5
     * result : [{"calorie":"142","has_calorie":true,"name":"鸭爪","probability":"0.334864"},{"calorie":"-1","has_calorie":true,"name":"卤水拼盘","probability":"0.0762953"},{"calorie":"75","has_calorie":true,"name":"鱿鱼","probability":"0.0415809"},{"calorie":"240","has_calorie":true,"name":"鹅掌","probability":"0.0411014"},{"calorie":"113","has_calorie":true,"name":"拌猪耳朵","probability":"0.0338084"}]
     */

    private long log_id;
    private int result_num;
    private List<ResultBean> result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public int getResult_num() {
        return result_num;
    }

    public void setResult_num(int result_num) {
        this.result_num = result_num;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * calorie : 142
         * has_calorie : true
         * name : 鸭爪
         * probability : 0.334864
         */

        private String calorie;
        private boolean has_calorie;
        private String name;
        private String probability;

        public String getCalorie() {
            return calorie;
        }

        public void setCalorie(String calorie) {
            this.calorie = calorie;
        }

        public boolean isHas_calorie() {
            return has_calorie;
        }

        public void setHas_calorie(boolean has_calorie) {
            this.has_calorie = has_calorie;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getProbability() {
            return probability;
        }

        public void setProbability(String probability) {
            this.probability = probability;
        }
    }
}
