package com.bwie.testten.car.model;


import com.bwie.testten.car.bean.DatasBean;
import com.bwie.testten.car.bean.MessageBean;
import com.bwie.testten.car.presenter.NewsPresenter;
import com.bwie.testten.car.utils.RetrofitUtils;

import java.util.List;

import io.reactivex.Flowable;



public class NewsModel implements IModel {
    private NewsPresenter presenter;

    public NewsModel(NewsPresenter presenter){
        this.presenter = (NewsPresenter) presenter;

    }
    @Override
    public void getData(String uid,String pid) {
        Flowable<MessageBean<List<DatasBean>>> flowable = RetrofitUtils.getInstance().getApiService().getDatas(uid);
        presenter.getNews(flowable);

    }
}