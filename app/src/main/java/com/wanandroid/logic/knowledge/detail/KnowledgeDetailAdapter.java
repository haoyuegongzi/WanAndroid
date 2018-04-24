package com.wanandroid.logic.knowledge.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

import com.wanandroid.logic.knowledge.detail.detailfragment.view.DetailFragment;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;

import java.util.List;

/**
 * 作者：chen1 on 2018/3/28 11
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class KnowledgeDetailAdapter extends FragmentStatePagerAdapter {
    KnowledgeBean.DataBean dataBean;
    FragmentManager fragmentManager;
    FragmentTransaction transaction;

    List<KnowledgeBean.DataBean.ChildrenBean> childrenBeanList;

    public KnowledgeDetailAdapter(FragmentManager fm) {
        super(fm);
        fragmentManager = fm;
        transaction = fm.beginTransaction();
    }

    public void refreshData(KnowledgeBean.DataBean dataBean) {
        this.dataBean = dataBean;
        childrenBeanList = dataBean.children;
        notifyDataSetChanged();
    }

    @Override
    public Fragment getItem(int position) {
        int index = position;
        Fragment fragment = new DetailFragment();
        KnowledgeBean.DataBean.ChildrenBean childrenBean = childrenBeanList.get(index);
        Bundle bundle = new Bundle();
        bundle.putParcelable("childrenBean", childrenBean);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getCount() {
        return childrenBeanList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return childrenBeanList.get(position).name;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        DetailFragment fragment = (DetailFragment) object;
        if (transaction == null) {
            transaction = fragmentManager.beginTransaction();
        }
        //调用事务的remove方法
        transaction.remove(fragment);
        fragment = null;
    }
}
