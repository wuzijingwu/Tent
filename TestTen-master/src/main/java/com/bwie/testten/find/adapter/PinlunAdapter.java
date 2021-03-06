package com.bwie.testten.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bwie.testten.R;
import com.bwie.testten.find.bean.Pinlun;

import java.util.List;



/**
 * Created by dell on 2017/12/8.
 */

public class PinlunAdapter extends RecyclerView.Adapter {

    List<Pinlun.RetBean.ListBean> list;
    Context context;

    public PinlunAdapter(List<Pinlun.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.text, parent, false);
        return new MyViewHolodd(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyViewHolodd myViewHolodd = (MyViewHolodd) holder;
        myViewHolodd.textView.setText(list.get(position).getMsg());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolodd extends RecyclerView.ViewHolder {


        private final TextView textView;

        public MyViewHolodd(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text_pinluns);
        }
    }

}
