package com.bwie.testten.car.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.testten.R;
import com.bwie.testten.car.Event.MessageEvent;
import com.bwie.testten.car.Event.PriceAndCountEvent;
import com.bwie.testten.car.Event.SomeId;
import com.bwie.testten.car.adapter.MyAdapter;
import com.bwie.testten.car.bean.DatasBean;
import com.bwie.testten.car.bean.MessageBean;
import com.bwie.testten.car.presenter.DelPresenter;
import com.bwie.testten.car.presenter.NewsPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ShopCarFragment extends Fragment implements Iview {

    private View inflate;
    //    private String uid = "71";
    private NewsPresenter presenter;
    private CheckBox mCheckbox2;
    private ExpandableListView mElv;

    /**
     * 0
     */
    private TextView mTvPrice;
    /**
     * 结算(0)
     */
    private TextView mTvNum;
    private MyAdapter adapter;
    private List<DatasBean> groupList = new ArrayList<>();
    private List<List<DatasBean.ListBean>> childList = new ArrayList<>();
    private String pid;
    private DelPresenter delPresenter;
    private int uid;
    private TextView tv_keepout;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        inflate = inflater.inflate(R.layout.activity, container, false);
        return inflate;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        EventBus.getDefault().register(this);


        presenter = new NewsPresenter();
        presenter.attachView(this);
        delPresenter = new DelPresenter();
        delPresenter.attachView(this);
        initView();
        adapter = new MyAdapter(getActivity(), groupList, childList);
        mElv.setAdapter(adapter);


        mCheckbox2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.changeAllListCbState(mCheckbox2.isChecked());
            }
        });




    }

    private void initView() {
        mElv = (ExpandableListView) inflate.findViewById(R.id.elv);
        mCheckbox2 = (CheckBox) inflate.findViewById(R.id.checkbox2);
        mTvPrice = (TextView) inflate.findViewById(R.id.tv_price);
        mTvNum = (TextView) inflate.findViewById(R.id.tv_num);

    }


    @Override
    public void onSuccess(Object o) {
        if (o != null) {
            List<DatasBean> list = (List<DatasBean>) o;
            if (list != null) {
                groupList.addAll(list);
                for (int i = 0; i < list.size(); i++) {
                    List<DatasBean.ListBean> datas = list.get(i).getList();
                    childList.add(datas);
                }

                adapter.notifyDataSetChanged();
                mCheckbox2.setChecked(true);

                adapter.changeAllListCbState(true);
                mElv.setGroupIndicator(null);
                for (int i = 0; i < groupList.size(); i++) {
                    mElv.expandGroup(i);
                }

            }
        }
    }

    @Override
    public void onFailed(Exception e) {

    }

    @Override
    public void delSuccess(MessageBean listMessageBean) {
        Toast.makeText(getActivity(), listMessageBean.getMsg(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        SharedPreferences user = getActivity().getSharedPreferences("USER", MODE_PRIVATE);
        String name = user.getString("name", "000");
        uid = user.getInt("uid", 0);
        super.onResume();
        presenter.getData(uid + "", pid);

    }


    @Subscribe
    public void onMessageEvent(MessageEvent event) {
        mCheckbox2.setChecked(event.isChecked());
    }

    @Subscribe
    public void onMessageEvent(PriceAndCountEvent event) {
        mTvNum.setText("结算(" + event.getCount() + ")");
        mTvPrice.setText("￥" + event.getPrice());
    }

    @Subscribe
    public void onMessageEvent(SomeId event) {
        pid = event.getPid();
        Log.e("zxz", "我得到了pid:" + pid);
        delPresenter.getData(uid + "", pid);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        if (presenter != null) {
            presenter.detachView();
        }
    }
}
