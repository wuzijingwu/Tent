package com.bwie.testten.classify;

import com.bwie.testten.classify.bean.OneBean;
import com.bwie.testten.classify.bean.TwoBean;

import java.util.List;



public interface ClassifyConstract {
    interface IClassifyView{
        void ShowList(List<OneBean.DataBean> list);
        void ShowRight(List<TwoBean.DataBean> list);
        void ShowError(String e);
    }
    interface IClassifyModel{
        void OnRequestsListener(String url,OnRequestListener onRequestListener);
        void OnRightData(String url,int cid,OnRightListener onRightListener);
    }
    interface OnRightListener{
        void OnSuccess(List<TwoBean.DataBean> list);
        void OnError(String e);
    }
    interface OnRequestListener{
        void OnSuccess(List<OneBean.DataBean> list);
        void OnError(String e);
    }
    interface IClassifyPresenter{
        void LoadList(String url);
        void LoadRight(String url,int cid);
    }
}
