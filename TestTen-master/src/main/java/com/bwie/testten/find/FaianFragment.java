package com.bwie.testten.find;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bwie.testten.R;
import com.bwie.testten.find.adapter.MyShouYeAdapter;
import com.bwie.testten.find.api.Api;
import com.bwie.testten.find.bean.ShouYe;
import com.bwie.testten.find.presenter.ShouyePresenter;
import com.bwie.testten.find.view.Iview;

import java.util.List;

/**
 * Created by dell on 2017/12/13.
 */

public class FaianFragment extends Fragment implements Iview {

    private View inflate;
    private RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.main, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = (RecyclerView) inflate.findViewById(R.id.recycler);
        ShouyePresenter shouyePresenter = new ShouyePresenter(this);
        shouyePresenter.getShouye(Api.URL);
    }


    @Override
    public void showshouye(List<ShouYe.RetBean.ListBean> list) {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new MyShouYeAdapter(list, getActivity()));
    }
}
