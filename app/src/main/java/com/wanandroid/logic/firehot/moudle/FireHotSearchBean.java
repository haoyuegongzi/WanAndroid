package com.wanandroid.logic.firehot.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：chen1 on 2018/3/23 11
 * E—Mail：chen126jie@163.com
 * TODO：
 * @author chen1
 */

public class FireHotSearchBean implements Parcelable {


    public int errorCode;
    public String errorMsg;
    public List<DataBean> data;

    @Override
    public String toString() {
        return "FireHotSearchBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }

    public static class DataBean implements Parcelable {
        public int id;
        public String link;
        public String name;
        public int order;
        public int visible;

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", link='" + link + '\'' +
                    ", name='" + name + '\'' +
                    ", order=" + order +
                    ", visible=" + visible +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.link);
            dest.writeString(this.name);
            dest.writeInt(this.order);
            dest.writeInt(this.visible);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.id = in.readInt();
            this.link = in.readString();
            this.name = in.readString();
            this.order = in.readInt();
            this.visible = in.readInt();
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
        dest.writeInt(this.errorCode);
        dest.writeString(this.errorMsg);
        dest.writeList(this.data);
    }

    public FireHotSearchBean() {
    }

    protected FireHotSearchBean(Parcel in) {
        this.errorCode = in.readInt();
        this.errorMsg = in.readString();
        this.data = new ArrayList<DataBean>();
        in.readList(this.data, DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<FireHotSearchBean> CREATOR = new Parcelable.Creator<FireHotSearchBean>() {
        @Override
        public FireHotSearchBean createFromParcel(Parcel source) {
            return new FireHotSearchBean(source);
        }

        @Override
        public FireHotSearchBean[] newArray(int size) {
            return new FireHotSearchBean[size];
        }
    };
}
