package com.wong.engidentifier.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午6:20
 * @version 1.0
 */
public class PlantBean extends MyObject implements Serializable {

    /**
     * log_id : 3907626626458209493
     * result : [{"score":0.43506968021393,"name":"风信子"},{"score":0.31647628545761,"name":"再力花"},{"score":0.23762993514538,"name":"花叶丁香"},{"score":0.20822171866894,"name":"醉鱼草"},{"score":0.12588216364384,"name":"紫丁香"}]
     */

    private long log_id;
    private List<ResultBean> result;

    public long getLog_id() {
        return log_id;
    }

    public void setLog_id(long log_id) {
        this.log_id = log_id;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean implements Serializable{
        /**
         * score : 0.43506968021393
         * name : 风信子
         */

        private double score;
        private String name;

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
    }
}
