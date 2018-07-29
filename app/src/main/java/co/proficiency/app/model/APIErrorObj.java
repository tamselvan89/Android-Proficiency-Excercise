package co.proficiency.app.model;

public class APIErrorObj {

    int errorCode;
    String errorMsg;

    public APIErrorObj(int ecode, String eMsg)
    {
        this.errorCode=ecode;
        this.errorMsg=eMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
