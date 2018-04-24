package com.wanandroid.logic.knowledge.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.wanandroid.R;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.logic.knowledge.presenter.KnowledgePresenter;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/20 17
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class KnowledgeFragment extends Fragment implements ViewKnowledgeInterface {
    View view;
    KnowledgePresenter knowledgePresenter;
    Disposable disposableKnowledge;
    KnowledgeAdapter knowledgeAdapter = null;
    RecyclerView rvKnowledge;

    LinearLayoutManager layoutManager;

    List<KnowledgeBean.DataBean> knwledgeDataList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_knowledge, container, false);

        rvKnowledge = view.findViewById(R.id.rvKnowledge);

        knowledgePresenter = new KnowledgePresenter(this);
        knowledgePresenter.doRequestKnowledgeData(disposableKnowledge);

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvKnowledge.setLayoutManager(layoutManager);

        return view;
    }


    @Override
    public void requestDataSuccess(int requestCode, KnowledgeBean knowledgeBean, Disposable disposableKnowledge) {
        knwledgeDataList = knowledgeBean.data;
        if (knowledgeAdapter == null) {
            knowledgeAdapter = new KnowledgeAdapter(getActivity(), getActivity(), knwledgeDataList);
            rvKnowledge.setAdapter(knowledgeAdapter);
        }else {
            knowledgeAdapter.refreshData(knwledgeDataList);
        }
    }

    @Override
    public void requestDataFailed(int requestCode, String msg, Disposable disposableKnowledge) {
        Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
    }
}
