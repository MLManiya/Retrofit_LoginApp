
package com.example.retrofit_loginapp.Modal;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UpdateProduct {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;

    public Integer getConnection() {
        return connection;
    }

    public void setConnection(Integer connection) {
        this.connection = connection;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

}
