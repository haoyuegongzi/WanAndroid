package com.wanandroid.logic.firehot.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.wanandroid.R;
import com.wanandroid.logic.commonwebsite.CommonWebSiteActivity;
import com.wanandroid.logic.firehot.moudle.CommonWebSiteBean;
import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;
import com.wanandroid.logic.firehot.presenter.FireHotPresenter;
import com.wanandroid.logic.search.view.SearchActivity;
import com.wanandroid.utils.flowlayout.FlowLayout;
import com.wanandroid.utils.flowlayout.TagAdapter;
import com.wanandroid.utils.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/20 17
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class FireHotFragment extends Fragment implements ViewFireHotInterface {
    Activity activity;
    View view;
    TagFlowLayout tagFlowLayout, tagFlowLayoutCommonUse;
    FireHotPresenter fireHotPresenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fire_hot, container, false);
        activity = getActivity();

        tagFlowLayout = view.findViewById(R.id.tagFlowLayout);
        tagFlowLayoutCommonUse = view.findViewById(R.id.tagFlowLayoutCommonUse);
        fireHotPresenter = new FireHotPresenter(this);

        fireHotPresenter.requestHotSearchData();
        fireHotPresenter.requestCommonUseWebSite();
        return view;
    }
    List<FireHotSearchBean.DataBean> fireHotList = new ArrayList<>();
    @Override
    public void requestHotSearchDataSuccess(int requestCode, FireHotSearchBean searchBean) {
        fireHotList = searchBean.data;
        setListener();
    }

    private void setListener(){
        String[]fireHotArray = new String[fireHotList.size()];
        for (int i = 0; i < fireHotList.size(); i++) {
            fireHotArray[i] = fireHotList.get(i).name;
        }

        tagFlowLayout.setAdapter(new TagAdapter<String>(fireHotArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.adapter_tag_item,
                                                                        tagFlowLayout, false);
                tv.setText(s);
                int mod = position % 5;
                switch (mod){
                    case 0:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item01));
                        break;
                    case 1:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item02));
                        break;
                    case 2:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item03));
                        break;
                    case 3:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item04));
                        break;
                    case 4:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item05));
                        break;
                    default:
                        break;
                }
                return tv;
            }
        });

        tagFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                FireHotSearchBean.DataBean fireHotBean = fireHotList.get(position);
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                intent.putExtra("fireHotBean", fireHotBean);
                getActivity().startActivity(intent);
                return false;
            }
        });
    }

    @Override
    public void requestHotSearchDataFailed(int requestCode, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void requestCommonWebSiteSuccess(int requestCode, CommonWebSiteBean commonWebSiteBean) {
        List<CommonWebSiteBean.DataBean>commonUseList = commonWebSiteBean.data;
        setTagFlowLayoutCommonUse(commonUseList);
    }

    @Override
    public void requestCommonWebSiteFailed(int requestCode, String msg) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }

    private void setTagFlowLayoutCommonUse(final List<CommonWebSiteBean.DataBean>commonUseList){
        String[]commonUseArray = new String[commonUseList.size()];
        for (int i = 0; i < commonUseList.size(); i++) {
            commonUseArray[i] = commonUseList.get(i).name;
        }
        tagFlowLayoutCommonUse.setAdapter(new TagAdapter<String>(commonUseArray) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                TextView tv = (TextView) LayoutInflater.from(getActivity()).inflate(R.layout.adapter_tag_item,
                                                                    tagFlowLayoutCommonUse, false);
                tv.setText(s);
                int mod = position % 5;
                switch (mod){
                    case 0:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item05));
                        break;
                    case 1:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item04));
                        break;
                    case 2:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item03));
                        break;
                    case 3:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item02));
                        break;
                    case 4:
                        tv.setTextColor(activity.getResources().getColor(R.color.flow_table_item01));
                        break;
                    default:
                        break;
                }
                return tv;
            }
        });

        tagFlowLayoutCommonUse.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                CommonWebSiteBean.DataBean commonWebSite = commonUseList.get(position);
                Intent intent = new Intent(getActivity(), CommonWebSiteActivity.class);
                intent.putExtra("fireHotBean", commonWebSite);
                getActivity().startActivity(intent);
                return false;
            }
        });
    }

    public FireHotFragment(){
    }
}
