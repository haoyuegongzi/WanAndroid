package com.wanandroid.logic.firehot.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/23 16
 * E—Mail：chen126jie@163.com
 * TODO：
 */

public class CommonWebSiteBean implements Parcelable {

    public String errorCode;
    public String errorMsg;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "CommonWebSiteBean{" +
                "errorCode='" + errorCode + '\'' +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }


    public static class DataBean implements Parcelable {

        public String icon;
        public String id;
        public String link;
        public String name;
        public String order;
        public String visible;

        @Override
        public String toString() {
            return "DataBean{" +
                    "icon='" + icon + '\'' +
                    ", id='" + id + '\'' +
                    ", link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", order='" + order + '\'' +
                    ", visible='" + visible + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.icon);
            dest.writeString(this.id);
            dest.writeString(this.link);
            dest.writeString(this.name);
            dest.writeString(this.order);
            dest.writeString(this.visible);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.icon = in.readString();
            this.id = in.readString();
            this.link = in.readString();
            this.name = in.readString();
            this.order = in.readString();
            this.visible = in.readString();
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

    public CommonWebSiteBean() {
    }

    protected CommonWebSiteBean(Parcel in) {
        this.errorCode = in.readString();
        this.errorMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<CommonWebSiteBean> CREATOR = new Parcelable.Creator<CommonWebSiteBean>() {
        @Override
        public CommonWebSiteBean createFromParcel(Parcel source) {
            return new CommonWebSiteBean(source);
        }

        @Override
        public CommonWebSiteBean[] newArray(int size) {
            return new CommonWebSiteBean[size];
        }
    };
}
