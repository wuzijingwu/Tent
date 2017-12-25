package com.bwie.testten.find.presenter;


import com.bwie.testten.find.bean.Dianying;
import com.bwie.testten.find.model.Imodel;
import com.bwie.testten.find.model.Onsecond;
import com.bwie.testten.find.model.model;
import com.bwie.testten.find.view.IsecondView;

/**
 * Created by dell on 2017/12/7.
 */

public class DainYingPresenter {
    IsecondView isecondView;
    Imodel imodel;

    public DainYingPresenter(IsecondView isecondView) {
        this.isecondView = isecondView;
        imodel = new model();
    }

    public void getSecond(String url, String mediaId) {
        imodel.RequstShiping(url, mediaId, new Onsecond() {
            @Override
            public void dataSecondSuccess(Dianying.RetBean list) {
                isecondView.shouVido(list);
            }
        });


    }


}
