package ru.zhenya.rest.RestApi.util;

import lombok.Data;


public class ChangesErrorResponse {
    private String message;
    private long timestamp;

    public ChangesErrorResponse(String  message,long timestamp){
        this.message=message;
        this.timestamp = timestamp;
    }

    public String getMessage(){return message;}

    public void setMessage(String message){this.message = message;}

    public long getTimestamp(){return timestamp;}

    public void setTimestamp(long timestamp){this.timestamp = timestamp;}


}
