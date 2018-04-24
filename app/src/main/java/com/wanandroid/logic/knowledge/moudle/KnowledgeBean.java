package com.wanandroid.logic.knowledge.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/23 17
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class KnowledgeBean implements Parcelable {

    public String errorCode;
    public String errorMsg;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "KnowledgeBean{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Parcelable {

        public String courseId;
        public String id;
        public String name;
        public String order;
        public String parentChapterId;
        public String visible;
        public List<ChildrenBean> children;

        @Override
        public String toString() {
            return "DataBean{" +
                    "courseId='" + courseId + '\'' +
                    ", id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", order='" + order + '\'' +
                    ", parentChapterId='" + parentChapterId + '\'' +
                    ", visible='" + visible + '\'' +
                    ", children=" + children +
                    '}';
        }

        public static class ChildrenBean implements Parcelable {

            public String courseId;
            public String id;
            public String name;
            public String order;
            public String parentChapterId;
            public String visible;
            public List<Object> children;

            @Override
            public String toString() {
                return "ChildrenBean{" +
                        "courseId='" + courseId + '\'' +
                        ", id='" + id + '\'' +
                        ", name='" + name + '\'' +
                        ", order='" + order + '\'' +
                        ", parentChapterId='" + parentChapterId + '\'' +
                        ", visible='" + visible + '\'' +
                        ", children=" + children +
                        '}';
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeString(this.courseId);
                dest.writeString(this.id);
                dest.writeString(this.name);
                dest.writeString(this.order);
                dest.writeString(this.parentChapterId);
                dest.writeString(this.visible);
                dest.writeList(this.children);
            }

            public ChildrenBean() {
            }

            protected ChildrenBean(Parcel in) {
                this.courseId = in.readString();
                this.id = in.readString();
                this.name = in.readString();
                this.order = in.readString();
                this.parentChapterId = in.readString();
                this.visible = in.readString();
                this.children = new ArrayList<Object>();
                in.readList(this.children, Object.class.getClassLoader());
            }

            public static final Creator<ChildrenBean> CREATOR = new Creator<ChildrenBean>() {
                @Override
                public ChildrenBean createFromParcel(Parcel source) {
                    return new ChildrenBean(source);
                }

                @Override
                public ChildrenBean[] newArray(int size) {
                    return new ChildrenBean[size];
                }
            };
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.courseId);
            dest.writeString(this.id);
            dest.writeString(this.name);
            dest.writeString(this.order);
            dest.writeString(this.parentChapterId);
            dest.writeString(this.visible);
            dest.writeList(this.children);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.courseId = in.readString();
            this.id = in.readString();
            this.name = in.readString();
            this.order = in.readString();
            this.parentChapterId = in.readString();
            this.visible = in.readString();
            this.children = new ArrayList<ChildrenBean>();
            in.readList(this.children, ChildrenBean.class.getClassLoader());
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
        dest.writeString(this.errorCode);
        dest.writeString(this.errorMsg);
        dest.writeList(this.data);
    }

    public KnowledgeBean() {
    }

    protected KnowledgeBean(Parcel in) {
        this.errorCode = in.readString();
        this.errorMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<KnowledgeBean> CREATOR = new Parcelable.Creator<KnowledgeBean>() {
        @Override
        public KnowledgeBean createFromParcel(Parcel source) {
            return new KnowledgeBean(source);
        }

        @Override
        public KnowledgeBean[] newArray(int size) {
            return new KnowledgeBean[size];
        }
    };
}
