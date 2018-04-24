package com.wanandroid.common;

import com.wanandroid.logic.firehot.moudle.CommonWebSiteBean;
import com.wanandroid.logic.firehot.moudle.FireHotSearchBean;
import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.home.moudle.BannerBean;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.KnowledgeDetailBean;
import com.wanandroid.logic.knowledge.moudle.KnowledgeBean;
import com.wanandroid.logic.login.moudle.LoginBean;
import com.wanandroid.logic.register.moudle.RegisterResult;
import com.wanandroid.logic.search.moudle.SearchBean;

import org.json.JSONObject;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 作者：chen1 on 2018/3/21 12
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author Administrator
 */

public class ApiClient {

    public interface HomeInfo{
        /**首页Banner的API**/
        @GET(CommonVariable.BANNERURL)
        Call<BannerBean> wanAndroidBanner();

        /**首页:文章列表的API**/
        @GET(CommonVariable.KNOWLEDGEDETAIL + "/{id}/json")
        Observable<ArticleListBean> wanAndroidArticleList(@Path("id")String id);

    }

    public interface FireHotInfo{
        /**热门关注：热搜的API**/
        @GET(CommonVariable.HOT_SEARCH)
        Observable<FireHotSearchBean> wanAndroidFireHot();

        /**热门关注：常用网站的API**/
        @GET(CommonVariable.COMMONWEBSITE)
        Observable<CommonWebSiteBean> wanAndroidCommon();
    }

    public interface KnowledgeInfo{
        @GET(CommonVariable.KNOWLEDGE)
        Observable<KnowledgeBean> wanAndroidKnowledge();

        @GET(CommonVariable.KNOWLEDGEDETAIL + "/{page}/json")
        Observable<KnowledgeDetailBean> wanAndroidKnowledgeDetail(@Path("page")String page, @Query("cid") String id);
    }

    public interface Search{
        @FormUrlEncoded
        @POST(CommonVariable.SEARCH + "/{id}/json")
        Observable<SearchBean> wanAndroidSearchObservable(@Path("id") String id, @FieldMap Map<String, String> map);

        /** http://www.wanandroid.com/ article/query /0/json**/
        @FormUrlEncoded
        @POST(CommonVariable.SEARCH + "/{id}/json")
        Call<SearchBean> wanAndroidSearchCall(@Path("id") String id, @FieldMap Map<String, String> map);
    }

    public interface Login{
        /** http://www.wanandroid.com/ user/login
         * username，password*/
        @FormUrlEncoded
        @POST(CommonVariable.LOGIN)
        Observable<LoginBean> wanAndroidLogin(@FieldMap Map<String, String> map);

        @FormUrlEncoded
        @POST(CommonVariable.LOGIN)
        Call<LoginBean> wanAndroidLoginCall(@FieldMap Map<String, String>map);
    }

    public interface  Register{
        /** http://www.wanandroid.com/ user/register
         * username,  password,  repassword*/
        @FormUrlEncoded
        @POST(CommonVariable.REGISTER)
        Call<RegisterResult> wanAndroidRegister(@FieldMap Map<String, String>map);
    }

    public interface Collection{

        /**收藏文章列表:  lg/collect/list/0/json*/
        @GET(CommonVariable.COLLECTION_LIST + "/{id}/json")
        Call<ArticleListBean> wanAndroidCollectionlist(@Path("id")String id);

        /** http://www.wanandroid.com/lg/collect/1165/json**/

        @POST(CommonVariable.ADD_COLLECTION + "/{id}/json")
        Call<ArticleListBean> wanAndroidAddCollection(@Path("id") String id);

        /** 文章列表 取消收藏：http://www.wanandroid.com/lg/uncollect_originId/2333/json */
        @POST(CommonVariable.ARTICLE_CANCEL_COLLECT + "/{id}/json")
        Call<ArticleListBean> wanAndroidArticleCancelCollect(@Path("id")String id);

        /** 我的收藏页面 取消收藏：http://www.wanandroid.com/lg/uncollect/2805/json originId:列表页下发，无则为-1 */
        @POST(CommonVariable.COLLECT_PAGE_CANCEL_COLLECT + "/{id}/json")
        Call<JSONObject> wanAndroidCollectPageCancelCollect(@Path("id")String id, String originId);

        /** 收藏站内文章 http://www.wanandroid.com/lg/collect/1165/json */
        @POST(CommonVariable.COLLECT_STATE_ARTICLE + "/{id}/json")
        Observable<Object> wanAndroidCollectState(@Path("id")String id);

        @POST(CommonVariable.COLLECT_STATE_ARTICLE + "/{id}/json")
        Call<Object>wanAndroidPostCollectState(@Path("id")String id);


    }
}
