package com.wanandroid.logic.home.view;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.common.ApiClient;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.homearticle.LoadArticleActivity;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.CircleImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：chen1 on 2018/3/22 15
 * E—Mail：chen126jie@163.com
 * TODO：
 *
 * @author chen1
 */

public class ArticleListAdapter extends RecyclerView.Adapter<ArticleListAdapter.Viewholder> {
    Activity activity;
    //默认值是首页的文章列表
    String flag = "HomeFragment";

    List<ItemDetail> itemDetailList;

    public ArticleListAdapter(Activity activity, List<ItemDetail> itemDetailList, String flag) {
        this.flag = flag;
        this.activity = activity;
        this.itemDetailList = itemDetailList;
        Log.i("TAG", "ArticleListAdapter: flag ===" + flag);
    }

    public void refreshAdapter(List<ItemDetail> itemDetailList, String flag) {
        this.flag = flag;
        this.itemDetailList = itemDetailList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Viewholder(LayoutInflater.from(activity).inflate(R.layout.adapter_home_article_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final Viewholder holder, final int position) {
        holder.tvRelease.setText(itemDetailList.get(position).niceDate);
        holder.tvAuthorName.setText(itemDetailList.get(position).author);

        if ("DetailFragment".equals(flag) || "CollectionActivity".equals(flag)) {
            holder.tvArticleType.setVisibility(View.INVISIBLE);
        } else {
            holder.tvArticleType.setVisibility(View.VISIBLE);
        }
        holder.tvArticleType.setText(itemDetailList.get(position).superChapterName);
        holder.tvArticleType.setTextColor(activity.getResources().getColor(R.color.article_type));
        holder.tvArticleContent.setText(itemDetailList.get(position).title);

        if (itemDetailList.get(position).collect) {
            //已收藏
            holder.ivFocus.setImageResource(R.mipmap.focus);
        } else {
            //没收藏
            holder.ivFocus.setImageResource(R.mipmap.focus_un);
        }

        if ("CollectionActivity".equals(flag)) {
            holder.ivFocus.setImageResource(R.mipmap.focus);
        }

        holder.civAuthorPhoto.setImageDrawable(activity.getResources().getDrawable(R.mipmap.ear));

        holder.llWanAndroidItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, LoadArticleActivity.class);
                if ("HomeFragment".equals(flag) || "CollectionActivity".equals(flag)) {
                    ItemDetail artilceInfo = itemDetailList.get(position);
                    intent.putExtra("flag", "HomeFragment");
                    intent.putExtra("HomeFragment", artilceInfo);
                    Log.i("TAG", "onClick: HomeFragment ===" + artilceInfo.toString());
                }
                if ("DetailFragment".equals(flag)) {
                    ItemDetail knowledgeDetailInfo = itemDetailList.get(position);
                    intent.putExtra("flag", "DetailFragment");
                    intent.putExtra("DetailFragment", knowledgeDetailInfo);
                    Log.i("TAG", "onClick: KnowledgeDetailInfo ===" + knowledgeDetailInfo.toString());
                }
                if ("SearchActivity".equals(flag)) {
                    ItemDetail searchData = itemDetailList.get(position);
                    intent.putExtra("flag", "SearchActivity");
                    intent.putExtra("SearchActivity", searchData);
                    Log.i("TAG", "onClick: searchData ===" + searchData.toString());
                }
                activity.startActivity(intent);
            }
        });

        //文章分类
        holder.tvArticleType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG", "onClick: 文章分类 ===");
                /**id chapterId courseId superChapterId**/
                String id = itemDetailList.get(position).id;
//                BaseApplication.getRetrofit().create(ApiClient.Collection.class).wanAndroidCollectState(id).subscribeOn(Schedulers.io())
//                        .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<Object>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//
//                    }
//
//                    @Override
//                    public void onNext(Object jsonObject) {
//                        if (jsonObject == null) {
//                            throw new NullPointerException("网络请求结果为空。请稍候再试");
//                        }
//                        Log.i("TAG", "onNext: jsonObject.toString() ===" + jsonObject.toString());
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//
//                    }
//                });
            BaseApplication.getRetrofit().create(ApiClient.Collection.class).wanAndroidPostCollectState(id)
                    .enqueue(new Callback<Object>() {
                        @Override
                        public void onResponse(Call<Object> call, Response<Object> response) {
                            Log.i("TAG", "onResponse: response.toString() ===" + response.toString());
                        }

                        @Override
                        public void onFailure(Call<Object> call, Throwable t) {

                        }
                    });
            }
        });

        //添加关注
        holder.rlFocus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = itemDetailList.get(position).id;
                /**如果未收藏，调用收藏接口，添加收藏*/
                if (!itemDetailList.get(position).collect) {
                    BaseApplication.getRetrofit().create(ApiClient.Collection.class).wanAndroidAddCollection(id)
                            .enqueue(new Callback<ArticleListBean>() {
                                @Override
                                public void onResponse(Call<ArticleListBean> call, Response<ArticleListBean> response) {
                                    if (response == null) {
                                        throw new NullPointerException("请求结果为空，请稍候或者检查后再试");
                                    }
                                    ArticleListBean listBean = response.body();
                                    if ("0".equals(listBean.errorCode) && StringUtils.isTrimEmpty(listBean.errorMsg)) {
                                        /**原本收藏，现在取反，为false，表示满意收藏*/
                                        itemDetailList.get(position).collect = !itemDetailList.get(position).collect;
                                        holder.ivFocus.setBackgroundResource(R.mipmap.focus);
                                    }
                                    if ("CollectionActivity".equals(flag)) {
                                        itemDetailList.remove(position);
                                    }
                                    notifyDataSetChanged();
                                }

                                @Override
                                public void onFailure(Call<ArticleListBean> call, Throwable t) {

                                }
                            });
                } else {/**如果已经收藏，调用取消收藏接口，取消收藏*/
                    BaseApplication.getRetrofit().create(ApiClient.Collection.class).wanAndroidArticleCancelCollect(id)
                            .enqueue(new Callback<ArticleListBean>() {
                                @Override
                                public void onResponse(Call<ArticleListBean> call, Response<ArticleListBean> response) {
                                    if (response == null) {
                                        throw new NullPointerException("请求结果为空，请稍候或者检查后再试");
                                    }
                                    ArticleListBean listBean = response.body();
                                    if ("0".equals(listBean.errorCode) && StringUtils.isTrimEmpty(listBean.errorMsg)) {
                                        itemDetailList.get(position).collect = !itemDetailList.get(position).collect;
                                        holder.ivFocus.setBackgroundResource(R.mipmap.focus_un);
                                    }
                                    notifyItemChanged(position);
                                }

                                @Override
                                public void onFailure(Call<ArticleListBean> call, Throwable t) {

                                }
                            });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return itemDetailList.size() > 0 ? itemDetailList.size() : 0;
    }

    static class Viewholder extends RecyclerView.ViewHolder {

        @BindView(R.id.civAuthorPhoto)
        CircleImageView civAuthorPhoto;
        @BindView(R.id.tvAuthorName)
        TextView tvAuthorName;
        @BindView(R.id.tvRelease)
        TextView tvRelease;
        @BindView(R.id.tvArticleContent)
        TextView tvArticleContent;
        @BindView(R.id.tvArticleType)
        TextView tvArticleType;
        @BindView(R.id.ivFocus)
        ImageView ivFocus;
        @BindView(R.id.llWanAndroidItem)
        LinearLayout llWanAndroidItem;
        @BindView(R.id.rlFocus)
        RelativeLayout rlFocus;


        public Viewholder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
