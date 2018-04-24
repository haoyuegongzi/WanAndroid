package com.wanandroid.logic.knowledge.detail.detailfragment.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by Administrator on 2018/4/6.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class KnowledgeDetailBean implements Parcelable {


    public DataBean data;
    public String errorCode;
    public String errorMsg;

    @Override
    public String toString() {
        return "KnowledgeDetailBean{" +
                "data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public static class DataBean implements Parcelable {

        public String curPage;
        public String offset;
        public boolean over;
        public String pageCount;
        public String size;
        public String total;
        public List<DatasBean> datas;

        @Override
        public String toString() {
            return "DataBean{" +
                    "curPage='" + curPage + '\'' +
                    ", offset='" + offset + '\'' +
                    ", over=" + over +
                    ", pageCount='" + pageCount + '\'' +
                    ", size='" + size + '\'' +
                    ", total='" + total + '\'' +
                    ", datas=" + datas +
                    '}';
        }

        public static class DatasBean implements Parcelable {
            /**
             * apkLink : 
             * author : 亦枫
             * chapterId : 60
             * chapterName : Android Studio相关
             * collect : false
             * courseId : 13
             * desc : 
             * envelopePic : 
             * fresh : false
             * id : 2196
             * link : https://www.jianshu.com/p/985f788fae2c
             * niceDate : 2018-01-18
             * origin : 
             * projectLink : 
             * publishTime : 1516270173000
             * superChapterId : 60
             * superChapterName : 开发环境
             * tags : []
             * title : Android Studio 掌握这些调试技巧，Debug能力不能再高啦
             * type : 0
             * visible : 1
             * zan : 0
             */

            public String apkLink;
            public String author;
            public String chapterId;
            public String chapterName;
            public boolean collect;
            public String courseId;
            public String desc;
            public String envelopePic;
            public boolean fresh;
            public String id;
            public String link;
            public String niceDate;
            public String origin;
            public String projectLink;
            public long publishTime;
            public String superChapterId;
            public String superChapterName;
            public String title;
            public String type;
            public String visible;
            public String zan;
            public List<?> tags;

            @Override
            public String toString() {
                return "DatasBean{" +
                        "apkLink='" + apkLink + '\'' +
                        ", author='" + author + '\'' +
                        ", chapterId='" + chapterId + '\'' +
                        ", chapterName='" + chapterName + '\'' +
                        ", collect=" + collect +
                        ", courseId='" + courseId + '\'' +
                        ", desc='" + desc + '\'' +
                        ", envelopePic='" + envelopePic + '\'' +
                        ", fresh=" + fresh +
                        ", id='" + id + '\'' +
                        ", link='" + link + '\'' +
                        ", niceDate='" + niceDate + '\'' +
                        ", origin='" + origin + '\'' +
                        ", projectLink='" + projectLink + '\'' +
                        ", publishTime=" + publishTime +
                        ", superChapterId='" + superChapterId + '\'' +
                        ", superChapterName='" + superChapterName + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", visible='" + visible + '\'' +
                        ", zan='" + zan + '\'' +
                        ", tags=" + tags +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.apkLink);
                dest.writeString(this.author);
                dest.writeString(this.chapterId);
                dest.writeString(this.chapterName);
                dest.writeByte(this.collect ? (byte) 1 : (byte) 0);
                dest.writeString(this.courseId);
                dest.writeString(this.desc);
                dest.writeString(this.envelopePic);
                dest.writeByte(this.fresh ? (byte) 1 : (byte) 0);
                dest.writeString(this.id);
                dest.writeString(this.link);
                dest.writeString(this.niceDate);
                dest.writeString(this.origin);
                dest.writeString(this.projectLink);
                dest.writeLong(this.publishTime);
                dest.writeString(this.superChapterId);
                dest.writeString(this.superChapterName);
                dest.writeString(this.title);
                dest.writeString(this.type);
                dest.writeString(this.visible);
                dest.writeString(this.zan);
                dest.writeList(this.tags);
            }

            public DatasBean() {
            }

            protected DatasBean(Parcel in) {
                this.apkLink = in.readString();
                this.author = in.readString();
                this.chapterId = in.readString();
                this.chapterName = in.readString();
                this.collect = in.readByte() != 0;
                this.courseId = in.readString();
                this.desc = in.readString();
                this.envelopePic = in.readString();
                this.fresh = in.readByte() != 0;
                this.id = in.readString();
                this.link = in.readString();
                this.niceDate = in.readString();
                this.origin = in.readString();
                this.projectLink = in.readString();
                this.publishTime = in.readLong();
                this.superChapterId = in.readString();
                this.superChapterName = in.readString();
                this.title = in.readString();
                this.type = in.readString();
                this.visible = in.readString();
                this.zan = in.readString();
                this.tags = new ArrayList<>();
                in.readList(this.tags, null);
            }

            public static final Creator<DatasBean> CREATOR = new Creator<DatasBean>() {
                @Override
                public DatasBean createFromParcel(Parcel source) {
                    return new DatasBean(source);
                }

                @Override
                public DatasBean[] newArray(int size) {
                    return new DatasBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.curPage);
            dest.writeString(this.offset);
            dest.writeByte(this.over ? (byte) 1 : (byte) 0);
            dest.writeString(this.pageCount);
            dest.writeString(this.size);
            dest.writeString(this.total);
            dest.writeList(this.datas);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.curPage = in.readString();
            this.offset = in.readString();
            this.over = in.readByte() != 0;
            this.pageCount = in.readString();
            this.size = in.readString();
            this.total = in.readString();
            this.datas = new ArrayList<DatasBean>();
            in.readList(this.datas, DatasBean.class.getClassLoader());
        }

        public static final Creator<DataBean> CREATOR = new Creator<DataBean>() {
            @Override
            public DataBean createFromParcel(Parcel source) {
                return new DataBean(source);
            }

            @Override
            public DataBean[] newArray(int size) {
                return new DataBean[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable((Parcelable) this.data, flags);
        dest.writeString(this.errorCode);
        dest.writeString(this.errorMsg);
    }

    public KnowledgeDetailBean() {
    }

    protected KnowledgeDetailBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.errorCode = in.readString();
        this.errorMsg = in.readString();
    }

    public static final Parcelable.Creator<KnowledgeDetailBean> CREATOR = new Parcelable.Creator<KnowledgeDetailBean>() {
        @Override
        public KnowledgeDetailBean createFromParcel(Parcel source) {
            return new KnowledgeDetailBean(source);
        }

        @Override
        public KnowledgeDetailBean[] newArray(int size) {
            return new KnowledgeDetailBean[size];
        }
    };
}
