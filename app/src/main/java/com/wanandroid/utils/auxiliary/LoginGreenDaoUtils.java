package com.wanandroid.utils.auxiliary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import com.wanandroid.greendao.DaoMaster;
import com.wanandroid.greendao.DaoSession;
import com.wanandroid.logic.login.moudle.LogicGreenDaoBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：Created by Administrator on 2018/4/18.
 * 邮箱：chen126jie@163.com
 * TODO：
 */

public class LoginGreenDaoUtils {

    /**
     * DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
     * DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
     * DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
     */
    public static final String SHOP_DB_Name = "loginInfo.db";
    public static DaoSession sDaoSession;
    public static DaoMaster.DevOpenHelper helper;
    public static DaoMaster master;
    public static LoginGreenDaoUtils instance;
    public static Context sContext;

    public static LoginGreenDaoUtils getInstance(){
        if(instance == null){
            synchronized (LoginGreenDaoUtils.class){
                if(instance == null){
                    instance = new LoginGreenDaoUtils();
                }
            }
        }
        return instance;
    }

    public void init(Context context){
        sContext = context;
    }

    public void setUpdataBase(){
        //创建数据库shop.db
        helper = new DaoMaster.DevOpenHelper(sContext, SHOP_DB_Name, null);
        //获取可写数据库
        SQLiteDatabase db = helper.getWritableDatabase();
        //获取数据库对象
        master = new DaoMaster(db);
        //获取dao对象管理者
        sDaoSession = master.newSession();
    }
    //开启打印日志
    public void initLogInfo(){
        QueryBuilder.LOG_SQL = true;
        QueryBuilder.LOG_VALUES = true;
    }

    //关闭数据库。在onDestroy方法调用。（调用这个方法会出现IllegalStateException异常）
    public void closedDb(){
        if(helper != null){
            helper.close();
            helper = null;
        }
        if(sDaoSession != null){
            sDaoSession.clear();
            sDaoSession = null;
        }
    }


    /** 插入单个数据
     */
    public boolean insertOne(LogicGreenDaoBean logic){
        boolean insertFlag = sDaoSession.getLogicGreenDaoBeanDao().insert(logic) == -1 ? false : true;
        return insertFlag;
    }

    public boolean insertOrReplaceOne(LogicGreenDaoBean logic){
        boolean insertOrReplaceFlag = sDaoSession.getLogicGreenDaoBeanDao().insertOrReplace(logic) == -1 ? false : true;
        return insertOrReplaceFlag;
    }

    /** 插入多个数据
     *  getShopDao().insertInTx(shopList);
     *  getShopDao().insertOrReplaceInTx(shopList);
     */
    public boolean insertManyData(List<LogicGreenDaoBean> logicList){
        boolean insertManyFlag = false;
        try{
            sDaoSession.getLogicGreenDaoBeanDao().insertInTx(logicList);
            insertManyFlag = true;
        }catch (Exception e){
            Log.i("TAG", "insertManyData: e.printStackTrace()" + e);
            throw new SQLiteException("登录信息插入异常");
        }
        return insertManyFlag;
    }

    public List<LogicGreenDaoBean> querryAll(){
        List<LogicGreenDaoBean> list = new ArrayList<>();
        try{
            list = sDaoSession.getLogicGreenDaoBeanDao().loadAll();
            Log.i("TAG", "queryBuilder查询到的数据长度 list.size() ==" + list.size());
        }catch (Exception e){
            Log.i("TAG", "querryAll: e.printStackTrace()" + e);
            throw new SQLiteException("登录信息查询异常");
        }
        return list;
    }

    public long querryCount(){
        long count = 0;
        try{
            count = sDaoSession.getLogicGreenDaoBeanDao().count();
            Log.i("TAG", "querryCount查询到的总个数count ==" + count);
        }catch (Exception e){
            Log.i("TAG", "querryCount: e.printStackTrace()" + e);
            throw new SQLiteException("登录信息查询异常");
        }
        return count;
    }

    public boolean updateOneData(LogicGreenDaoBean login){
        boolean updateOneFlag = false;
        try{
            sDaoSession.getLogicGreenDaoBeanDao().update(login);
            updateOneFlag = true;
        }catch (Exception e){
            Log.i("TAG", "updateOneData: e.printStackTrace()" + e);
            throw new SQLiteException("登录信息查询异常");
        }
        return updateOneFlag;
    }

    /**
     * 删除单个数据
     * getTABUserDao().delete(user);
     */
    public boolean deleteOneData(LogicGreenDaoBean login){
        boolean deleteOneFlag = false;
        try{
            sDaoSession.getLogicGreenDaoBeanDao().delete(login);

            deleteOneFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteOneData: e.printStackTrace()" + e);
        }
        return deleteOneFlag;
    }

    public boolean deleteAll(){
        boolean deleteAllFlag = false;
        try{
            sDaoSession.getLogicGreenDaoBeanDao().deleteAll();
            deleteAllFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteAll: e.printStackTrace()" + e);
        }
        return deleteAllFlag;
    }
}
