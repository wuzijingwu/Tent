package com.bwie.testten.car.view;


import com.bwie.testten.car.bean.MessageBean;


public interface Iview {
    void onSuccess(Object o);
    void onFailed(Exception e);
    void delSuccess(MessageBean listMessageBean);
}
