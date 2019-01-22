package com.wong.engidentifier.http.bean;

/**
 * @author WongKyunban
 * description
 * created at 2018-12-29 下午4:28
 * @version 1.0
 */
public class RequestAuthBean {
    private String grant_type = "client_credentials";
    private String client_id; //API_Key
    private String client_secret; //Secret key

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

}
