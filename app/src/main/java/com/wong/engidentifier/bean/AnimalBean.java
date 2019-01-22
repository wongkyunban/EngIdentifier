package com.wong.engidentifier.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午5:52
 * @version 1.0
 */
public class AnimalBean extends MyObject implements Serializable{

    /**
     * log_id : 3647815914578960053
     * result : [{"score":"0.990768","name":"哈士奇犬"},{"score":"0.00431389","name":"阿拉斯加雪橇犬"},{"score":"0.000342529","name":"阿拉斯加克利凯犬"},{"score":"0.000339435","name":"爱斯基摩犬"},{"score":"0.000306315","name":"德牧/德国牧羊犬"},{"score":"0.000207947","name":"因纽特犬"}]
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
         * score : 0.990768
         * name : 哈士奇犬
         */

        private String score;
        private String name;

        public String getScore() {
            return score;
        }

        public void setScore(String score) {
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
