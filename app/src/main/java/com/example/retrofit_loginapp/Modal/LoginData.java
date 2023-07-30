
package com.example.retrofit_loginapp.Modal;




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class LoginData {

    @SerializedName("connection")
    @Expose
    private Integer connection;
    @SerializedName("result")
    @Expose
    private Integer result;
    @SerializedName("Register")
    @Expose
    private Register register;

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

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "connection=" + connection +
                ", result=" + result +
                ", register=" + register +
                '}';
    }
}
