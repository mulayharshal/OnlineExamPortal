package com.app.response;  // Fix typo: "reponse" to "response"

import com.app.util.Status;

public class Response {
    
    private Object response;
    private Status status;
    
    public Object getRes() {
        return response;
    }
    
    public void setRes(Object response) {  // Fix typo here as well
        this.response = response;
    }
    
    public Status getStatus() {
        return status;
    }
    
    public void setStatus(Status status) {
        this.status = status;
    }
}
