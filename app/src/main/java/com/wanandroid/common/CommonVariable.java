package com.wanandroid.common;

import io.reactivex.disposables.Disposable;

/**
 * 作者：chen1 on 2018/3/16 17
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class CommonVariable {

    public static final String BASEURL = "http://www.wanandroid.com/";

    public static final int CONNECTTIMEOUT = 3;
    public static final int READTIMEOUT = 3;
    public static final int WRITETIMEOUT = 3;

    public static final String LOGINSTATE = "isLogin";

    /**requestCode:  0:数据请求正常；1：数据请求失败；2：参数异常**/
    public static final int REQUESTCODE_SECCESS = 0;
    public static final int REQUESTCODE_FAILED = 1;
    public static final int REQUESTCODE_PARAMS_ERROR = 2;


    public static final String REQUEST_MSG_FAILED = "数据请求失败，请稍候再试";
    public static final String REQUEST__PARAMS_ERROR = "参数异常";
    public static final String ACCOUNT_IS_EMPTY = "您输入的账号为空";
    public static final String PASSWORD_IS_EMPTY = "您输入的密码为空";


    /**首页Banner的接**/
    public static final String BANNERURL = "banner/json";
    /**热门关注：大家都在搜的接口**/
    public static final String HOT_SEARCH = "hotkey/json";
    /**热门关注：常用网站的接口**/
    public static final String COMMONWEBSITE = "friend/json";
    /**知识体系的接**/
    public static final String KNOWLEDGE = "tree/json";
    /**知识详情*/
    public static final String KNOWLEDGEDETAIL = "article/list";
    /**搜索*/
    public static final String SEARCH = "article/query";
    /**登录接口*/
    public static final String LOGIN = "user/login";
    /**注册接口*/
    public static final String REGISTER = "user/register";
    /**收藏文章列表*/
    public static final String COLLECTION_LIST = "lg/collect/list";
    /**添加收藏**/
    public static final String ADD_COLLECTION = "lg/collect";
    /**文章列表 取消收藏**/
    public static final String ARTICLE_CANCEL_COLLECT = "lg/uncollect_originId";
    /**收藏页面 取消收藏**/
    public static final String COLLECT_PAGE_CANCEL_COLLECT = "lg/uncollect";
    /**收藏站内文章**/
    public static final String COLLECT_STATE_ARTICLE = "lg/collect";









    /**文章列表默认从0开始分页加载**/
    public static final String ARTICLELISTURL = "0";
    /**首页：文章列表的Disposable**/
    public static Disposable disposableArticle;
    /**热门关注：大家都在搜的Disposable**/
    public static Disposable disposableFireHot;
    /**热门关注：常用网站的Disposable**/
    public static Disposable disposableCommonWebSite;
    /**知识体系的的Disposable**/
    public static Disposable disposableKnowledge;

    public static final int IO_BUFFER_SIZE = 1024 * 2;



    public static String SAVE_COOKIE_BY_SP = "SharedPreferences";

    public static String SAVE_COOKIE_BY_SQL = "SQLite";

    public static String SAVE_COOKIE_BY_MEMORY = "MEMORY";

    public static String errorCode = "1.0";


}
