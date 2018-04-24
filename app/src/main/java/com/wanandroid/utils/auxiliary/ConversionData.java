package com.wanandroid.utils.auxiliary;

import com.wanandroid.logic.home.moudle.ArticleListBean;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.ItemDetail;
import com.wanandroid.logic.knowledge.detail.detailfragment.moudle.KnowledgeDetailBean;
import com.wanandroid.logic.search.moudle.SearchBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by Administrator on 2018/4/13.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class ConversionData {
    //知识体系数据转化
    public static List<ItemDetail> ConversionknowledgeDetailBeanList(List<KnowledgeDetailBean.DataBean.DatasBean> knowledgeDetailList) {
        List<ItemDetail> itemList = new ArrayList<>();
        for (int i = 0; i < knowledgeDetailList.size(); i++) {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.apkLink = knowledgeDetailList.get(i).apkLink;
            itemDetail.author = knowledgeDetailList.get(i).author;
            itemDetail.chapterId = knowledgeDetailList.get(i).chapterId;
            itemDetail.chapterName = knowledgeDetailList.get(i).chapterName;
            itemDetail.collect = knowledgeDetailList.get(i).collect;
            itemDetail.courseId = knowledgeDetailList.get(i).courseId;
            itemDetail.desc = knowledgeDetailList.get(i).desc;
            itemDetail.envelopePic = knowledgeDetailList.get(i).envelopePic;
            itemDetail.fresh = knowledgeDetailList.get(i).fresh;
            itemDetail.id = knowledgeDetailList.get(i).id;
            itemDetail.link = knowledgeDetailList.get(i).link;
            itemDetail.niceDate = knowledgeDetailList.get(i).niceDate;
            itemDetail.origin = knowledgeDetailList.get(i).origin;
            itemDetail.projectLink = knowledgeDetailList.get(i).projectLink;
            itemDetail.publishTime = String.valueOf(knowledgeDetailList.get(i).publishTime);
            itemDetail.superChapterId = knowledgeDetailList.get(i).superChapterId;
            itemDetail.superChapterName = knowledgeDetailList.get(i).superChapterName;
            itemDetail.title = knowledgeDetailList.get(i).title;
            itemDetail.type = knowledgeDetailList.get(i).type;
            itemDetail.visible = knowledgeDetailList.get(i).visible;
            itemDetail.zan = knowledgeDetailList.get(i).zan;

            itemList.add(itemDetail);
        }

        return itemList;
    }

    //搜索界面数据转化
    public static List<ItemDetail> searchList(List<SearchBean.DataBean.DatasBean> searchList) {
        List<ItemDetail> itemList = new ArrayList<>();
        for (int i = 0; i < searchList.size(); i++) {

            ItemDetail itemDetail = new ItemDetail();
            itemDetail.apkLink = searchList.get(i).getApkLink();
            itemDetail.author = searchList.get(i).getAuthor();
            itemDetail.chapterId = searchList.get(i).getChapterId();
            itemDetail.chapterName = searchList.get(i).getChapterName();
            itemDetail.collect = searchList.get(i).isCollect();
            itemDetail.courseId = searchList.get(i).getCourseId();
            itemDetail.desc = searchList.get(i).getDesc();
            itemDetail.envelopePic = searchList.get(i).getEnvelopePic();
            itemDetail.fresh = searchList.get(i).isFresh();
            itemDetail.id = searchList.get(i).getId();
            itemDetail.link = searchList.get(i).getLink();
            itemDetail.niceDate = searchList.get(i).getNiceDate();
            itemDetail.origin = searchList.get(i).getOrigin();
            itemDetail.projectLink = searchList.get(i).getProjectLink();
            itemDetail.publishTime = searchList.get(i).getPublishTime();
            itemDetail.superChapterId = searchList.get(i).getSuperChapterId();
            itemDetail.superChapterName = searchList.get(i).getSuperChapterName();
            itemDetail.title = searchList.get(i).getTitle();
            itemDetail.type = searchList.get(i).getType();
            itemDetail.visible = searchList.get(i).getVisible();
            itemDetail.zan = searchList.get(i).getZan();

            itemList.add(itemDetail);
        }
        return itemList;
    }

    //首页文章列表数据转化
    public static List<ItemDetail> homeArticle(List<ArticleListBean.DataBean.DatasBean> articledata){
        List<ItemDetail> itemList = new ArrayList<>();
        for (int i = 0; i < articledata.size(); i++) {
            ItemDetail itemDetail = new ItemDetail();
            itemDetail.apkLink = articledata.get(i).apkLink;
            itemDetail.author = articledata.get(i).author;
            itemDetail.chapterId = articledata.get(i).chapterId;
            itemDetail.chapterName = articledata.get(i).chapterName;
            itemDetail.collect = articledata.get(i).collect;
            itemDetail.courseId = articledata.get(i).courseId;
            itemDetail.desc = articledata.get(i).desc;
            itemDetail.envelopePic = articledata.get(i).envelopePic;
            itemDetail.fresh = articledata.get(i).fresh;
            itemDetail.id = articledata.get(i).id;
            itemDetail.link = articledata.get(i).link;
            itemDetail.niceDate = articledata.get(i).niceDate;
            itemDetail.origin = articledata.get(i).origin;
            itemDetail.projectLink = articledata.get(i).projectLink;
            itemDetail.publishTime = String.valueOf(articledata.get(i).publishTime);
            itemDetail.superChapterId = articledata.get(i).superChapterId;
            itemDetail.superChapterName = articledata.get(i).superChapterName;
            itemDetail.title = articledata.get(i).title;
            itemDetail.type = articledata.get(i).type;
            itemDetail.visible = articledata.get(i).visible;
            itemDetail.zan = articledata.get(i).zan;

            itemList.add(itemDetail);
        }

        return itemList;
    }
}
