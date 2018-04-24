package com.wanandroid.logic.login.moudle;

import android.os.Parcel;
import android.os.Parcelable;

import com.wanandroid.common.base.BaseLogicBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by Administrator on 2018/4/18.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class LoginBean extends BaseLogicBean implements Parcelable {

    /**
     * data : {"collectIds":[],"email":"","icon":"","id":4812,"password":"chen126JIE","type":0,"username":"chen126jie@163.com"}
     * errorCode : 0
     * errorMsg :
     */

    private DataBean data;

    @Override
    public String toString() {
        return "LoginBean{" +
                "; data=" + data +
                '}' +
                "; errorCode==" + getErrorCode() +
                "; errorMsg===" + getErrorMsg();
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Parcelable {
        /**
         * collectIds : []
         * email :
         * icon :
         * id : 4812
         * password : chen126JIE
         * type : 0
         * username : chen126jie@163.com
         */
        private String email;
        private String icon;
        private String id;
        private String password;
        private String type;
        private String username;
        private List<Object> collectIds;

        @Override
        public String toString() {
            return "DataBean{" +
                    "email='" + email + '\'' +
                    ", icon='" + icon + '\'' +
                    ", id='" + id + '\'' +
                    ", password='" + password + '\'' +
                    ", type='" + type + '\'' +
                    ", username='" + username + '\'' +
                    ", collectIds=" + collectIds +
                    '}';
        }

        public String getEmail() {
            return email == null ? "" : email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getIcon() {
            return icon == null ? "" : icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getId() {
            return id == null ? "" : id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password == null ? "" : password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getType() {
            return type == null ? "" : type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUsername() {
            return username == null ? "" : username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public List<Object> getCollectIds() {
            if (collectIds == null) {
                return new ArrayList<>();
            }
            return collectIds;
        }

        public void setCollectIds(List<Object> collectIds) {
            this.collectIds = collectIds;
        }

        public static Creator<DataBean> getCREATOR() {
            return CREATOR;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.email);
            dest.writeString(this.icon);
            dest.writeString(this.id);
            dest.writeString(this.password);
            dest.writeString(this.type);
            dest.writeString(this.username);
            dest.writeList(this.collectIds);
        }

        public DataBean() {
        }

        protected DataBean(Parcel in) {
            this.email = in.readString();
            this.icon = in.readString();
            this.id = in.readString();
            this.password = in.readString();
            this.type = in.readString();
            this.username = in.readString();
            this.collectIds = new ArrayList<Object>();
            in.readList(this.collectIds, Object.class.getClassLoader());
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
    }

    public LoginBean() {
    }

    protected LoginBean(Parcel in) {
        this.data = in.readParcelable(DataBean.class.getClassLoader());
    }

    public static final Parcelable.Creator<LoginBean> CREATOR = new Parcelable.Creator<LoginBean>() {
        @Override
        public LoginBean createFromParcel(Parcel source) {
            return new LoginBean(source);
        }

        @Override
        public LoginBean[] newArray(int size) {
            return new LoginBean[size];
        }
    };
}
