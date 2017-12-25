package com.bwie.testten.find.model;

/**
 * Created by dell on 2017/12/6.
 */

public interface Imodel {
    void RequestShouYe(String url, Onselectshouye onselectshouye);
    void RequstShiping(String url, String mediaId, Onsecond onsecond);
    void RequstPinlun(String url, String mediaId, int pnum, Onthree onthree);


}
