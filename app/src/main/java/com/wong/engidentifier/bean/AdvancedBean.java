package com.wong.engidentifier.bean;

import java.io.Serializable;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午5:24
 * @version 1.0
 */
public class AdvancedBean extends MyObject implements Serializable{

    /**
     * log_id : 7665551560681776981
     * result_num : 5
     * result : [{"score":0.994367,"root":"商品-电脑办公","keyword":"键盘"},{"score":0.600821,"root":"商品-电脑办公","keyword":"笔记本"},{"score":0.423838,"root":"商品-数码产品","keyword":"台式电脑"},{"score":0.206183,"root":"商品-电脑办公","keyword":"笔记本电脑"},{"score":5.63E-4,"root":"商品-电脑办公","keyword":"显示器屏幕"}]
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
         * score : 0.994367
         * root : 商品-电脑办公
         * keyword : 键盘
         */

        private double score;
        private String root;
        private String keyword;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getRoot() {
            return root;
        }

        public void setRoot(String root) {
            this.root = root;
        }

        public String getKeyword() {
            return keyword;
        }

        public void setKeyword(String keyword) {
            this.keyword = keyword;
        }
    }
}
