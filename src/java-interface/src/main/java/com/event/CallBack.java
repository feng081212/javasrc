package com.event;


public interface CallBack {

    void onSuccess() ;
    
    void onFailure() ;
    
    void onTimeOut() ;
    
    void onException(Exception e) ;
}
