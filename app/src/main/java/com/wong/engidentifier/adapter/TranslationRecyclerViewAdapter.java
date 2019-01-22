package com.wong.engidentifier.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wong.engidentifier.R;
import com.wong.engidentifier.bean.TranslationResultBean;
import com.wong.engidentifier.holder.TranslationViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-22 下午3:39
 * @version 1.0
 */
public class TranslationRecyclerViewAdapter extends RecyclerView.Adapter<TranslationViewHolder> {


    private List<TranslationResultBean.TransResultBean> datas;

    public  TranslationRecyclerViewAdapter(List<TranslationResultBean.TransResultBean> list){
        this.datas = list;

   }



    public void setDatas(List<TranslationResultBean.TransResultBean> datas) {
        this.datas = datas;
    }

    @NonNull
    @Override
    public TranslationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_translation,viewGroup,false);

        return new TranslationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TranslationViewHolder holder, int i) {
        TextView ch = holder.itemView.findViewById(R.id.tv_title_ch);
        TextView en = holder.itemView.findViewById(R.id.tv_title_en);
        ch.setText(datas.get(i).getSrc());
        en.setText(datas.get(i).getDst());

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}
