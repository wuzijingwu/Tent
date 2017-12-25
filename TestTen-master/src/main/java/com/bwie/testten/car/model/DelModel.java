package com.bwie.testten.car.model;


import com.bwie.testten.car.bean.MessageBean;
import com.bwie.testten.car.presenter.DelPresenter;
import com.bwie.testten.car.utils.RetrofitUtils;

import io.reactivex.Flowable;



public class DelModel implements IModel {
    private DelPresenter presenter;

    public DelModel(DelPresenter presenter){
        this.presenter =  presenter;

    }
    @Override
    public void getData(String uid,String pid) {

        Flowable<MessageBean> delFlowable = RetrofitUtils.getInstance().getApiService().deleteData(uid,pid);
        presenter.delData(delFlowable);
    }
}