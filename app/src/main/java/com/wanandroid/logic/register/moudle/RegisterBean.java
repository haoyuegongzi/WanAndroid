package com.wanandroid.logic.register.moudle;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 作者：Created by Administrator on 2018/4/21.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class RegisterBean implements Parcelable {
    String userName;
    String passWord;
    String repassWord;

    @Override
    public String toString() {
        return "RegisterBean{" +
                "userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", repassWord='" + repassWord + '\'' +
                '}';
    }

    public String getUserName() {
        return userName == null ? "" : userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord == null ? "" : passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRepassWord() {
        return repassWord == null ? "" : repassWord;
    }

    public void setRepassWord(String repassWord) {
        this.repassWord = repassWord;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userName);
        dest.writeString(this.passWord);
        dest.writeString(this.repassWord);
    }

    public RegisterBean() {
    }

    protected RegisterBean(Parcel in) {
        this.userName = in.readString();
        this.passWord = in.readString();
        this.repassWord = in.readString();
    }

    public static final Parcelable.Creator<RegisterBean> CREATOR = new Parcelable.Creator<RegisterBean>() {
        @Override
        public RegisterBean createFromParcel(Parcel source) {
            return new RegisterBean(source);
        }

        @Override
        public RegisterBean[] newArray(int size) {
            return new RegisterBean[size];
        }
    };
}
