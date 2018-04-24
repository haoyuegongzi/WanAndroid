package com.wanandroid.utils.auxiliary;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.wanandroid.greendao.DaoMaster;
import com.wanandroid.greendao.DaoSession;
import com.wanandroid.greendao.SearchGreenDaoBeanDao;
import com.wanandroid.logic.search.moudle.SearchGreenDaoBean;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen1 on 2017/11/29.
 * TO DO:
 */

/**
 * 使用GreenDao 实现简单的增删改查，下面是基本方法
 * 增加单个数据
 * getShopDao().insert(shop);
 * getShopDao().insertOrReplace(shop);
 *
 * 增加多个数据
 * getShopDao().insertInTx(shopList);
 * getShopDao().insertOrReplaceInTx(shopList);
 *
 * 查询全部
 * List< Shop> list = getShopDao().loadAll();
 * List< Shop> list = getShopDao().queryBuilder().list();
 *
 * 查询附加单个条件
 * .where()
 * .whereOr()
 *
 * 查询附加多个条件
 * .where(, , ,)
 * .whereOr(, , ,)
 *
 * 查询附加排序
 * .orderDesc()
 * .orderAsc()
 *
 * 查询限制当页个数
 * .limit()
 *
 * 查询总个数
 * .count()
 *
 * 修改单个数据
 * getShopDao().update(shop);
 *
 * 修改多个数据
 * getShopDao().updateInTx(shopList);
 *
 * 删除单个数据
 * getTABUserDao().delete(user);
 *
 * 删除多个数据
 * getUserDao().deleteInTx(userList);
 *
 * 删除数据ByKey
 * getTABUserDao().deleteByKey();
 */
public class GreenDaoUtils {

    /**
     * DevOpenHelper：创建SQLite数据库的SQLiteOpenHelper的具体实现
     * DaoMaster：GreenDao的顶级对象，作为数据库对象、用于创建表和删除表
     * DaoSession：管理所有的Dao对象，Dao对象中存在着增删改查等API
     */
    public static final String SHOP_DB_Name = "ShopBean.db";
    public static DaoSession sDaoSession;
    public static DaoMaster.DevOpenHelper helper;
    public static DaoMaster master;
    public static GreenDaoUtils instance;
    public static Context sContext;

    public static GreenDaoUtils getInstance(){
        if(instance == null){
            synchronized (GreenDaoUtils.class){
                if(instance == null){
                    instance = new GreenDaoUtils();
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
     *  getShopDao().insert(shop);
     *  getShopDao().insertOrReplace(shop);
     */
    public boolean insertOne(SearchGreenDaoBean search){
        boolean insertFlag = sDaoSession.getSearchGreenDaoBeanDao().insert(search) == -1 ? false : true;
        return insertFlag;
    }

    public boolean insertOrReplaceOne(SearchGreenDaoBean search){
        boolean insertOrReplaceFlag = sDaoSession.getSearchGreenDaoBeanDao().insertOrReplace(search) == -1 ? false : true;
        return insertOrReplaceFlag;
    }

    /** 插入多个数据
     *  getShopDao().insertInTx(shopList);
     *  getShopDao().insertOrReplaceInTx(shopList);
     */
    public boolean insertManyData(List<SearchGreenDaoBean> searchList){
        boolean insertManyFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().insertInTx(searchList);
            insertManyFlag = true;
        }catch (Exception e){
            Log.i("TAG", "insertManyData: e.printStackTrace()" + e);
        }
        return insertManyFlag;
    }

    public boolean insertInTxManyData(List<SearchGreenDaoBean> searchList){
        boolean insertInTxFlag = false;
        try {
            sDaoSession.getSearchGreenDaoBeanDao().insertOrReplaceInTx(searchList);
            insertInTxFlag = true;
        }catch (Exception e){
            Log.i("TAG", "insertInTxManyData: e.printStackTrace()" + e);
        }
        return insertInTxFlag;
    }

    /** 查询全部
     *  List< Shop> list = getShopDao().loadAll();
     *  List< Shop> list = getShopDao().queryBuilder().list();
     */

    public List<SearchGreenDaoBean> querryAll(){
        List<SearchGreenDaoBean> list = new ArrayList<>();
        try{
            list = sDaoSession.getSearchGreenDaoBeanDao().loadAll();
            Log.i("TAG", "queryBuilder查询到的数据长度 list.size() ==" + list.size());
        }catch (Exception e){
            Log.i("TAG", "querryAll: e.printStackTrace()" + e);
        }
        return list;
    }

    public List<SearchGreenDaoBean> queryBuilder(){
        List<SearchGreenDaoBean> list = new ArrayList<>();
        try{
            list = sDaoSession.getSearchGreenDaoBeanDao().queryBuilder().list();
            Log.i("TAG", "queryBuilder查询到的数据长度 list.size() ==" + list.size());
        }catch (Exception e){
            Log.i("TAG", "queryBuilder: e.printStackTrace()" + e);
        }
        return list;
    }

    /** 查询总个数
     *  .count()
     */
    public long querryCount(){
        long count = 0;
        try{
            count = sDaoSession.getSearchGreenDaoBeanDao().count();
            Log.i("TAG", "querryCount查询到的总个数count ==" + count);
        }catch (Exception e){
            Log.i("TAG", "querryCount: e.printStackTrace()" + e);
        }
        return count;
    }

    /**
     * 修改单个数据
     * getShopDao().update(shop);
     */
    public boolean updateOneData(SearchGreenDaoBean shop){
        boolean updateOneFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().update(shop);
            updateOneFlag = true;
        }catch (Exception e){
            Log.i("TAG", "updateOneData: e.printStackTrace()" + e);
        }
        return updateOneFlag;
    }

    /**
     * 修改多个数据
     * getShopDao().updateInTx(shopList);
     */
    public boolean updateList(List<SearchGreenDaoBean> list){
        boolean updateListFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().updateInTx(list);
            updateListFlag = true;
        }catch (Exception e){
            Log.i("TAG", "updateList: e.printStackTrace()" + e);
        }
        return updateListFlag;
    }

    /**
     * 删除单个数据
     * getTABUserDao().delete(user);
     */
    public boolean deleteOneData(SearchGreenDaoBean searchGreenDaoBean){
        boolean deleteOneFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().delete(searchGreenDaoBean);
            deleteOneFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteOneData: e.printStackTrace()" + e);
        }
        return deleteOneFlag;
    }

    /**
     * 删除多个数据
     * getUserDao().deleteInTx(userList);
     */
    public boolean deleteDataList(List<SearchGreenDaoBean> list){
        boolean deleteDataFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().deleteInTx(list);
            deleteDataFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteDataList: e.printStackTrace()" + e);
        }
        return deleteDataFlag;
    }


    /**
     * 删除全部
     */
    public boolean deleteAll(){
        boolean deleteAllFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().deleteAll();
            deleteAllFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteAll: e.printStackTrace()" + e);
        }
        return deleteAllFlag;
    }

    /**
     * 按照Key值删除某一个
     */
    public boolean deleteByKey(Long key){
        boolean deleteByKeyFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().deleteByKey(key);
            deleteByKeyFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteByKey: e.printStackTrace()" + e);
        }
        return deleteByKeyFlag;
    }

    /**
     * 按照Key批量删除
     */
    public boolean deleteByKeyList(List<Long> list){
        boolean deleteByKeyListFlag = false;
        try{
            sDaoSession.getSearchGreenDaoBeanDao().deleteByKeyInTx(list);
            deleteByKeyListFlag = true;
        }catch (Exception e){
            Log.i("TAG", "deleteByKeyList: e.printStackTrace()" + e);
        }
        return deleteByKeyListFlag;
    }

    /**
     * 按照Id的范围查找
     * @param starPostion
     * @param endPosition
     * @param ccount
     * @return
     */
    public List<SearchGreenDaoBean> queryById(int starPostion, int endPosition, int ccount){
        List<SearchGreenDaoBean> querryList = sDaoSession.getSearchGreenDaoBeanDao().queryBuilder()
                    .where(SearchGreenDaoBeanDao.Properties.Id.between(starPostion, endPosition))
                    .limit(ccount).build().list();
        return querryList;
    }

    /**
     * 查询某个表是否包含某个id
     * @param ID
     * @return
     */
    public boolean isContainId(Long ID){
        QueryBuilder<SearchGreenDaoBean> queryBuilder = sDaoSession.getSearchGreenDaoBeanDao().queryBuilder();
        queryBuilder.where(SearchGreenDaoBeanDao.Properties.Id.eq(ID));
        queryBuilder.buildCount().count();
        return queryBuilder.buildCount().count() > 0 ? true : false;
    }

    /**
     * 按照name查找(字符串的查找皆可用这个)
     * @param querryChar
     * @return
     */
    public List<SearchGreenDaoBean> querryByCharacter(String querryChar){
        List<SearchGreenDaoBean> querryNameList = sDaoSession.getSearchGreenDaoBeanDao().queryBuilder()
                .where(SearchGreenDaoBeanDao.Properties.Name.eq(querryChar))
                .list();
        return querryNameList;
    }
}
