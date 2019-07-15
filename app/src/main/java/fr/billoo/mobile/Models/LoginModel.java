package fr.billoo.mobile.Models;

import com.google.gson.annotations.SerializedName;

public class LoginModel
{
    @SerializedName("AUTHTOKEN")
    private String authtoken;

    @SerializedName("MSG")
    private String msg;

    @SerializedName("STATUS")
    private String status;

    @SerializedName("Email")
    private String email;

    @SerializedName("UserId")
    private String userid;

    @SerializedName("password")
    private String password;

    @SerializedName("BARCODE")
    private String barcode;

    @SerializedName("ERROR_CODE")
    private String errorCode;

    @SerializedName("ResponseCode")
    public String ResponseCode;

    @SerializedName("ResponseMessage")
    public String ResponseMessage;

    public LoginModel(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getResponseCode() {
        return ResponseCode;
    }

    public void setResponseCode(String responseCode) {
        ResponseCode = responseCode;
    }

    public String getResponseMessage() {
        return ResponseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        ResponseMessage = responseMessage;
    }
}
