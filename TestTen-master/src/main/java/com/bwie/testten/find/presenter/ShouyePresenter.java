package com.bwie.testten.find.presenter;

import com.bwie.testten.find.bean.ShouYe;
import com.bwie.testten.find.model.Imodel;
import com.bwie.testten.find.model.Onselectshouye;
import com.bwie.testten.find.model.model;
import com.bwie.testten.find.view.Iview;

import java.util.List;

//import text.bwei.com.yukao11yue.MainActivity;
//import text.bwei.com.yukao11yue.bean.ShouYe;
//import text.bwei.com.yukao11yue.model.Imodel;
//import text.bwei.com.yukao11yue.model.Onselectshouye;
//import text.bwei.com.yukao11yue.model.model;
//import text.bwei.com.yukao11yue.view.Iview;

/**
 * Created by dell on 2017/12/6.
 */

public class ShouyePresenter {
    Iview iview;
    Imodel imodel;

    public ShouyePresenter(Iview iview) {
        this.iview = iview;
        imodel = new model();
    }

    public void getShouye(String url) {
        imodel.RequestShouYe(url, new Onselectshouye() {
            @Override
            public void dataShouyeSuccess(List<ShouYe.RetBean.ListBean> list) {
                iview.showshouye(list);
            }
        });


    }


}
