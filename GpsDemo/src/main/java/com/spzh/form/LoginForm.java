package com.spzh.form;

public class LoginForm {
    String result;
    String jsession;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getJsession() {
        return jsession;
    }

    public void setJsession(String jsession) {
        this.jsession = jsession;
    }

    public LoginForm(String result, String jsession) {
        this.result = result;
        this.jsession = jsession;
    }

    public LoginForm() {
        super();
    }
}
