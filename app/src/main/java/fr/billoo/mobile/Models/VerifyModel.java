package fr.billoo.mobile.Models;

import com.google.gson.annotations.SerializedName;

public class VerifyModel
{
    @SerializedName("userid")
    private String userid;

    @SerializedName("authtoken")
    private String authtoken;

    @SerializedName("code")
    private String code;

    @SerializedName("STATUS")
    private String status;

    @SerializedName("ERROR_CODE")
    private String errorCode;

    @SerializedName("ResponseCode")
    public String ResponseCode;

    @SerializedName("ResponseMessage")
    public String ResponseMessage;

    public VerifyModel(String userid,String authtoken,  String code) {
        this.authtoken = authtoken;
        this.userid = userid;
        this.code = code;
    }

    public VerifyModel(String authtoken, String userid, String code, String status, String responseCode, String responseMessage) {
        this.authtoken = authtoken;
        this.userid = userid;
        this.code = code;
        this.status = status;
        ResponseCode = responseCode;
        ResponseMessage = responseMessage;
    }

    public String getAuthtoken() {
        return authtoken;
    }

    public void setAuthtoken(String authtoken) {
        this.authtoken = authtoken;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
