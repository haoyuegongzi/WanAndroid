package com.wanandroid.logic.knowledge.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.logic.knowledge.detail.KnowledgeDetailActivity;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.utils.flowlayout.FlowLayout;
import com.wanandroid.utils.flowlayout.TagAdapter;
import com.wanandroid.utils.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者：chen1 on 2018/3/26 15
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class KnowledgeAdapter extends RecyclerView.Adapter<KnowledgeAdapter.ViewHolder> {
    Context context;
    Activity activity;
    List<KnowledgeBean.DataBean> knwledgeDataList;
    List<KnowledgeBean.DataBean.ChildrenBean> childrenBeanList;

    public KnowledgeAdapter(Activity activity, Context context, List<KnowledgeBean.DataBean> knwledgeDataList) {
        this.context = context;
        this.activity = activity;
        this.knwledgeDataList = knwledgeDataList;
    }

    public void refreshData(List<KnowledgeBean.DataBean> knwledgeDataList){
        this.knwledgeDataList = knwledgeDataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(activity).inflate(R.layout.adapter_knowledge, parent, false));
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvKnowledgeTitle.setText(knwledgeDataList.get(position).name.isEmpty() ? "" : knwledgeDataList.get(position).name);
        final ViewHolder viewHolder = holder;
        final int indexPosition = position;
        childrenBeanList = knwledgeDataList.get(position).children;
        String[] knwledgeArray = new String[childrenBeanList.size()];
        for (int j = 0; j < childrenBeanList.size(); j++) {
            knwledgeArray[j] = childrenBeanList.get(j).name;
        }

        holder.tagKnowledgeFlowLayout.setAdapter(new TagAdapter<KnowledgeBean.DataBean.ChildrenBean>(childrenBeanList) {
            @Override
            public View getView(FlowLayout parent, int position, KnowledgeBean.DataBean.ChildrenBean childrenBean) {
                TextView textView = (TextView) LayoutInflater.from(context).inflate(R.layout.adapter_tag_knowledge_item,
                        viewHolder.tagKnowledgeFlowLayout, false);
                textView.setText(childrenBean.name);
                return textView;
            }
        });


        holder.llKnowledgeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KnowledgeBean.DataBean dataBean = knwledgeDataList.get(indexPosition);
                Intent intent = new Intent(context, KnowledgeDetailActivity.class);
                intent.putExtra("dataBean", dataBean);
                activity.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return knwledgeDataList.size() > 0 ? knwledgeDataList.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvKnowledgeTitle)
        TextView tvKnowledgeTitle;
        @BindView(R.id.tagKnowledgeFlowLayout)
        TagFlowLayout tagKnowledgeFlowLayout;
        @BindView(R.id.llKnowledgeItem)
        LinearLayout llKnowledgeItem;
        @BindView(R.id.rlKnowledgeItem)
        RelativeLayout rlKnowledgeItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
