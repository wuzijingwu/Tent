package com.bwie.testten.find.api;

import com.bwie.testten.find.bean.Dianying;
import com.bwie.testten.find.bean.Pinlun;
import com.bwie.testten.find.bean.ShouYe;

import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Created by dell on 2017/12/6.
 */

public interface ApiService {
    @GET("homePageApi/homePage.do")
    Observable<ShouYe> getDataes();


    @POST
    Observable<Dianying> getdate(@Url String url, @QueryMap Map<String, String> map);



    @POST
    Observable<Pinlun> getdats1(@Url String url, @QueryMap Map<String, String> map);


}
