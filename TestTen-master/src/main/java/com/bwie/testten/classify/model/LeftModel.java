package com.bwie.testten.classify.model;

import com.bwie.testten.classify.ClassifyConstract;
import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.classify.bean.TwoBean;
import com.bwie.testten.utils.ApiServer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;



public class LeftModel implements ClassifyConstract.IClassifyModel{

    @Override
    public void OnRequestsListener(String url, final ClassifyConstract.OnRequestListener onRequestListener) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<OneBean> getonedata = apiServer.getonedata();
        getonedata.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<OneBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRequestListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(OneBean oneBean) {
                        List<OneBean.DataBean> class_list = oneBean.getData();
                        onRequestListener.OnSuccess(class_list);
                    }
                });
    }

    @Override
    public void OnRightData(String url,int cid, final ClassifyConstract.OnRightListener onRightListener) {

        Map<String,Integer> map = new HashMap<>();
        map.put("cid",cid);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ApiServer apiServer = retrofit.create(ApiServer.class);
        Observable<TwoBean> right = apiServer.getRight("product/getProductCatagory",map);
        right.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<TwoBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onRightListener.OnError(e.getMessage().toString());
                    }

                    @Override
                    public void onNext(TwoBean oneBean) {
                        List<TwoBean.DataBean> data = oneBean.getData();
                        onRightListener.OnSuccess(data);
                    }
                });
    }
}
