package com.wong.engidentifier.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wong.engidentifier.MainActivity;
import com.wong.engidentifier.R;
import com.wong.engidentifier.bean.TagBean;
import com.wong.engidentifier.holder.MainMenuViewHolder;

import java.util.List;

/**
 * @author WongKyunban
 * description
 * created at 2019-01-21 下午4:00
 * @version 1.0
 */
public class MainMenuRecyclerViewAdapter extends RecyclerView.Adapter<MainMenuViewHolder> {

    private List<TagBean> list;
    private Context context;
    public MainMenuRecyclerViewAdapter(List<TagBean> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MainMenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_main_menu,viewGroup,false);
        return new MainMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainMenuViewHolder mainMenuViewHolder, int i) {

        TagBean tagBean = list.get(i);
        TextView  textView = mainMenuViewHolder.itemView.findViewById(R.id.tv_title);
        textView.setText(tagBean.getTitle());

        Drawable drawable = mainMenuViewHolder.itemView.getContext().getResources().getDrawable(tagBean.getResourceId());
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());// 设置边界
        // param 左上右下
        textView.setCompoundDrawables(null,drawable,null,null);
        mainMenuViewHolder.itemView.setTag(tagBean);
        mainMenuViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TagBean tagBean = (TagBean)v.getTag();
                Toast.makeText(v.getContext(),tagBean.getTitle(),Toast.LENGTH_LONG).show();
                MainActivity.startActivity(v.getContext(),tagBean);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<TagBean> list) {
        this.list = list;
    }
}
