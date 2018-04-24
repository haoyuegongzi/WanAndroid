package com.wanandroid.logic.search.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by Administrator on 2018/4/10.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class SearchBean implements Parcelable {
    /**
     * data : {"curPage":1,"datas":[{"apkLink":"","author":"挖掘匠","chapterId":60,"chapterName":"Android Studio相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1479,"link":"http://www.jianshu.com/p/9b25087a5d7d","niceDate":"2017-10-31","origin":"","projectLink":"","publishTime":1509450445000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"Android <em class='highlight'>Studio3<\/em>.0正式版填坑路","type":0,"visible":1,"zan":0},{"apkLink":"","author":"24K纯帅豆","chapterId":60,"chapterName":"Android Studio相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1478,"link":"http://www.jianshu.com/p/15afb8234d19","niceDate":"2017-10-31","origin":"","projectLink":"","publishTime":1509450421000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"Android <em class='highlight'>Studio3<\/em>.0更新之路（遇坑必入）","type":0,"visible":1,"zan":0}],"offset":0,"over":true,"pageCount":1,"size":20,"total":2}
     * errorCode : 0
     * errorMsg : 
     */

    private DataBean data;
    private String errorCode;
    private String errorMsg;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode == null ? "" : errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg == null ? "" : errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "SearchBean{" +
                "data=" + data +
                ", errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }

    public static class DataBean implements Parcelable {
        /**
         * curPage : 1
         * datas : [{"apkLink":"","author":"挖掘匠","chapterId":60,"chapterName":"Android Studio相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1479,"link":"http://www.jianshu.com/p/9b25087a5d7d","niceDate":"2017-10-31","origin":"","projectLink":"","publishTime":1509450445000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"Android <em class='highlight'>Studio3<\/em>.0正式版填坑路","type":0,"visible":1,"zan":0},{"apkLink":"","author":"24K纯帅豆","chapterId":60,"chapterName":"Android Studio相关","collect":false,"courseId":13,"desc":"","envelopePic":"","fresh":false,"id":1478,"link":"http://www.jianshu.com/p/15afb8234d19","niceDate":"2017-10-31","origin":"","projectLink":"","publishTime":1509450421000,"superChapterId":60,"superChapterName":"开发环境","tags":[],"title":"Android <em class='highlight'>Studio3<\/em>.0更新之路（遇坑必入）","type":0,"visible":1,"zan":0}]
         * offset : 0
         * over : true
         * pageCount : 1
         * size : 20
         * total : 2
         */

        private String curPage;
        private String offset;
        private boolean over;
        private String pageCount;
        private String size;
        private String total;
        private List<DatasBean> datas;

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

        public String getCurPage() {
            return curPage == null ? "" : curPage;
        }

        public void setCurPage(String curPage) {
            this.curPage = curPage;
        }

        public String getOffset() {
            return offset == null ? "" : offset;
        }

        public void setOffset(String offset) {
            this.offset = offset;
        }

        public boolean isOver() {
            return over;
        }

        public void setOver(boolean over) {
            this.over = over;
        }

        public String getPageCount() {
            return pageCount == null ? "" : pageCount;
        }

        public void setPageCount(String pageCount) {
            this.pageCount = pageCount;
        }

        public String getSize() {
            return size == null ? "" : size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public String getTotal() {
            return total == null ? "" : total;
        }

        public void setTotal(String total) {
            this.total = total;
        }

        public List<DatasBean> getDatas() {
            if (datas == null) {
                return new ArrayList<>();
            }
            return datas;
        }

        public void setDatas(List<DatasBean> datas) {
            this.datas = datas;
        }

        public static class DatasBean implements Parcelable {
            /**
             * apkLink : 
             * author : 挖掘匠
             * chapterId : 60
             * chapterName : Android Studio相关
             * collect : false
             * courseId : 13
             * desc : 
             * envelopePic : 
             * fresh : false
             * id : 1479
             * link : http://www.jianshu.com/p/9b25087a5d7d
             * niceDate : 2017-10-31
             * origin : 
             * projectLink : 
             * publishTime : 1509450445000
             * superChapterId : 60
             * superChapterName : 开发环境
             * tags : []
             * title : Android <em class='highlight'>Studio3</em>.0正式版填坑路
             * type : 0
             * visible : 1
             * zan : 0
             */

            private String apkLink;
            private String author;
            private String chapterId;
            private String chapterName;
            private boolean collect;
            private String courseId;
            private String desc;
            private String envelopePic;
            private boolean fresh;
            private String id;
            private String link;
            private String niceDate;
            private String origin;
            private String projectLink;
            private String publishTime;
            private String superChapterId;
            private String superChapterName;
            private String title;
            private String type;
            private String visible;
            private String zan;
            private List<?> tags;

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
                        ", publishTime='" + publishTime + '\'' +
                        ", superChapterId='" + superChapterId + '\'' +
                        ", superChapterName='" + superChapterName + '\'' +
                        ", title='" + title + '\'' +
                        ", type='" + type + '\'' +
                        ", visible='" + visible + '\'' +
                        ", zan='" + zan + '\'' +
                        ", tags=" + tags +
                        '}';
            }

            public String getApkLink() {
                return apkLink == null ? "" : apkLink;
            }

            public void setApkLink(String apkLink) {
                this.apkLink = apkLink;
            }

            public String getAuthor() {
                return author == null ? "" : author;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public String getChapterId() {
                return chapterId == null ? "" : chapterId;
            }

            public void setChapterId(String chapterId) {
                this.chapterId = chapterId;
            }

            public String getChapterName() {
                return chapterName == null ? "" : chapterName;
            }

            public void setChapterName(String chapterName) {
                this.chapterName = chapterName;
            }

            public boolean isCollect() {
                return collect;
            }

            public void setCollect(boolean collect) {
                this.collect = collect;
            }

            public String getCourseId() {
                return courseId == null ? "" : courseId;
            }

            public void setCourseId(String courseId) {
                this.courseId = courseId;
            }

            public String getDesc() {
                return desc == null ? "" : desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getEnvelopePic() {
                return envelopePic == null ? "" : envelopePic;
            }

            public void setEnvelopePic(String envelopePic) {
                this.envelopePic = envelopePic;
            }

            public boolean isFresh() {
                return fresh;
            }

            public void setFresh(boolean fresh) {
                this.fresh = fresh;
            }

            public String getId() {
                return id == null ? "" : id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLink() {
                return link == null ? "" : link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getNiceDate() {
                return niceDate == null ? "" : niceDate;
            }

            public void setNiceDate(String niceDate) {
                this.niceDate = niceDate;
            }

            public String getOrigin() {
                return origin == null ? "" : origin;
            }

            public void setOrigin(String origin) {
                this.origin = origin;
            }

            public String getProjectLink() {
                return projectLink == null ? "" : projectLink;
            }

            public void setProjectLink(String projectLink) {
                this.projectLink = projectLink;
            }

            public String getPublishTime() {
                return publishTime == null ? "" : publishTime;
            }

            public void setPublishTime(String publishTime) {
                this.publishTime = publishTime;
            }

            public String getSuperChapterId() {
                return superChapterId == null ? "" : superChapterId;
            }

            public void setSuperChapterId(String superChapterId) {
                this.superChapterId = superChapterId;
            }

            public String getSuperChapterName() {
                return superChapterName == null ? "" : superChapterName;
            }

            public void setSuperChapterName(String superChapterName) {
                this.superChapterName = superChapterName;
            }

            public String getTitle() {
                return title == null ? "" : title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getType() {
                return type == null ? "" : type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getVisible() {
                return visible == null ? "" : visible;
            }

            public void setVisible(String visible) {
                this.visible = visible;
            }

            public String getZan() {
                return zan == null ? "" : zan;
            }

            public void setZan(String zan) {
                this.zan = zan;
            }

            public List<?> getTags() {
                if (tags == null) {
                    return new ArrayList<>();
                }
                return tags;
            }

            public void setTags(List<?> tags) {
                this.tags = tags;
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
                dest.writeString(this.publishTime);
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
                this.publishTime = in.readString();
                this.superChapterId = in.readString();
                this.superChapterName = in.readString();
                this.title = in.readString();
                this.type = in.readString();
                this.visible = in.readString();
                this.zan = in.readString();
                this.tags = new ArrayList<Object>();
                in.readList(this.tags, Object.class.getClassLoader());
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
        dest.writeParcelable(this.data, flags);
        dest.writeString(this.errorCode);
        dest.writeString(this.errorMsg);
    }

    public SearchBean() {
    }

    protected SearchBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
        this.errorCode = in.readString();
        this.errorMsg = in.readString();
    }

    public static final Parcelable.Creator<SearchBean> CREATOR = new Parcelable.Creator<SearchBean>() {
        @Override
        public SearchBean createFromParcel(Parcel source) {
            return new SearchBean(source);
        }

        @Override
        public SearchBean[] newArray(int size) {
            return new SearchBean[size];
        }
    };
}
