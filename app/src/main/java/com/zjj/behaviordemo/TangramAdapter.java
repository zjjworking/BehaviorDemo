package com.zjj.behaviordemo;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zjj on 2018/7/31.
 */
public class TangramAdapter extends DelegateAdapter.Adapter<TangramAdapter.TangramViewHolder> {

    private  Context context;
    private    List<Object>  mData;
    private LayoutHelper layoutHelper;

    private RecyclerView.LayoutParams layoutParams;

    private int count = 0;

    private MyItemClickListener myItemClickListener;
    TangramAdapter(Context context, ArrayList<Object> objects,LayoutHelper layoutHelper,int count){
        this.context = context;
        this.mData = objects;
        this.count = count;
        this.layoutHelper = layoutHelper;
        this.layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,300);
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return layoutHelper;
    }

    @NonNull
    @Override
    public TangramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TangramViewHolder(View.inflate(context,R.layout.item,null));
    }

    @Override
    public void onBindViewHolder(@NonNull TangramViewHolder holder, int position) {
        holder.textView.setText("这里是第   "+ position);
        holder.image.setImageResource(R.mipmap.ic_launcher);
    }

    @Override
    public int getItemCount() {
        return count;
    }

    class TangramViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
         ImageView image;
        public TangramViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.Item);
            image = (ImageView) itemView.findViewById(R.id.Image);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(myItemClickListener != null){
                        myItemClickListener.onItemClick(v,getAdapterPosition());
                    }
                }
            });
        }
    }
    // 设置Item的点击事件
    // 绑定MainActivity传进来的点击监听器
    public void setOnItemClickListener(MyItemClickListener listener) {
        myItemClickListener = listener;
    }

    interface MyItemClickListener{
       void  onItemClick(View view,int position);
    }
}
