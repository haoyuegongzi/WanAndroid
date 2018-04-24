package com.wanandroid.logic.home.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/21 12
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class BannerBean implements Parcelable {

    public String errorCode;
    public String errorMsg;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "BannerBean{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Parcelable {
        /**
         * desc : 一起来做个App吧
         * id : 10
         * imagePath : http://www.wanandroid.com/blogimgs/50c115c2-cf6c-4802-aa7b-a4334de444cd.png
         * isVisible : 1
         * order : 0
         * title : 一起来做个App吧
         * type : 0
         * url : http://www.wanandroid.com/blog/show/2
         */

        public String desc;
        public String id;
        public String imagePath;
        public String isVisible;
        public String order;
        public String title;
        public String type;
        public String url;

        @Override
        public String toString() {
            return "DataBean{" +
                    "desc='" + desc + '\'' +
                    ", id='" + id + '\'' +
                    ", imagePath='" + imagePath + '\'' +
                    ", isVisible='" + isVisible + '\'' +
                    ", order='" + order + '\'' +
                    ", title='" + title + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.desc);
            dest.writeString(this.id);
            dest.writeString(this.imagePath);
            dest.writeString(this.isVisible);
            dest.writeString(this.order);
            dest.writeString(this.title);
            dest.writeString(this.type);
            dest.writeString(this.url);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.desc = in.readString();
            this.id = in.readString();
            this.imagePath = in.readString();
            this.isVisible = in.readString();
            this.order = in.readString();
            this.title = in.readString();
            this.type = in.readString();
            this.url = in.readString();
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

    public BannerBean() {
    }

    protected BannerBean(Parcel in) {
        this.errorCode = in.readString();
        this.errorMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<BannerBean> CREATOR = new Parcelable.Creator<BannerBean>() {
        @Override
        public BannerBean createFromParcel(Parcel source) {
            return new BannerBean(source);
        }

        @Override
        public BannerBean[] newArray(int size) {
            return new BannerBean[size];
        }
    };
}
