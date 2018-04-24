package com.wanandroid.logic.search.moudle;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 *
 * @author Administrator
 * @date 2018/4/10
 * 邮箱：chen126jie@163.com
 * TODO：
 */

@Entity
public class SearchGreenDaoBean {

    //Id表示主键且主键不能用int;autoincrement=true表示主键Id自增
    @org.greenrobot.greendao.annotation.Id(autoincrement = true)
    private Long Id;

    //商品名称 (unique 表示该属性必须在数据库中是唯一的值)
    @Unique
    private String name;

    @Generated(hash = 907873349)
    public SearchGreenDaoBean(Long Id, String name) {
        this.Id = Id;
        this.name = name;
    }

    @Generated(hash = 957288656)
    public SearchGreenDaoBean() {
    }

    public Long getId() {
        return this.Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
