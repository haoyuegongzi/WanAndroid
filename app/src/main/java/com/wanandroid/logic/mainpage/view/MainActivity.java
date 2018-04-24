package com.wanandroid.logic.mainpage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.wanandroid.R;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.common.base.BaseApplication;
import com.wanandroid.logic.drawer.collection.view.CollectionActivity;
import com.wanandroid.logic.firehot.view.FireHotFragment;
import com.wanandroid.logic.home.view.HomeFragment;
import com.wanandroid.logic.knowledge.view.KnowledgeFragment;
import com.wanandroid.logic.login.moudle.LogicGreenDaoBean;
import com.wanandroid.logic.login.view.LoginActivity;
import com.wanandroid.logic.search.view.SearchActivity;
import com.wanandroid.utils.auxiliary.LoginGreenDaoUtils;
import com.wanandroid.utils.auxiliary.SharedPreferencesUtils;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.CircleImageView;
import com.wanandroid.utils.viewutils.ToastInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author chen1
 */
public class MainActivity extends BaseActivity implements MainViewInterface {
    ActionBarDrawerToggle drawerToggle;
    DrawerLayoutAdapter drawerAdapter;
    FireHotFragment fireHotFragment;
    HomeFragment homeFragment;
    KnowledgeFragment knowledgeFragment;
    LoginGreenDaoUtils greenDaoUtils;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    List<String> drawerItemTitle;
    List<Integer> drawerItemIcon;
    boolean isLogin = false;
    String id;
    String username;
    String type;
    String email;

    @BindView(R.id.toolBar)
    Toolbar toolBar;
    @BindView(R.id.civHeaderPhoto)
    CircleImageView civHeaderPhoto;
    @BindView(R.id.tvnickName)
    TextView tvnickName;
    @BindView(R.id.lvDrawerLayout)
    ListView lvDrawerLayout;
    @BindView(R.id.dlDrawerLayout)
    DrawerLayout dlDrawerLayout;
    @BindView(R.id.flMainContent)
    FrameLayout flMainContent;
    @BindView(R.id.ivHome)
    ImageView ivHome;
    @BindView(R.id.tvHome)
    TextView tvHome;
    @BindView(R.id.llHome)
    LinearLayout llHome;
    @BindView(R.id.ivKnowledge)
    ImageView ivKnowledge;
    @BindView(R.id.tvKnowledge)
    TextView tvKnowledge;
    @BindView(R.id.llKnowledge)
    LinearLayout llKnowledge;
    @BindView(R.id.ivFireHot)
    ImageView ivFireHot;
    @BindView(R.id.tvFireHot)
    TextView tvFireHot;
    @BindView(R.id.llFireHot)
    LinearLayout llFireHot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /**requestWindowFeature(Window.FEATURE_NO_TITLE))是继承自Activity的方法
         隐藏掉系统原先的导航栏,用Toolbar代替(是继承自AppcompatActivity的方法)**/
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        /**获取登录信息*/
        getLoginState();
        /**先处理ToolBar**/
        initToolBar();
        /**然后加载主页View：默认0，默认加载第一个*/
        loadFragmentView(0);
        /**最后 三个方法 处理侧边栏的逻辑*/
        initDrawerLayout();
        addDrawerSlideData();
        initDrawerSlideData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tool_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
//            case R.id.fireHot:
//                //主界面加载fire_hot的Fragment
//                break;
            case R.id.search:
                startActivity(new Intent(MainActivity.this, SearchActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getLoginState(){
        isLogin = SharedPreferencesUtils.getBoolean(this, CommonVariable.LOGINSTATE);
        if (isLogin) {
            greenDaoUtils = LoginGreenDaoUtils.getInstance();
            greenDaoUtils.init(this);
            greenDaoUtils.setUpdataBase();
            greenDaoUtils.initLogInfo();

            List<LogicGreenDaoBean> accountList = greenDaoUtils.querryAll();
            if (accountList.size() > 0) {
                BaseApplication.logicBean = accountList.get(0);
                id = String.valueOf(BaseApplication.logicBean.getId());
                username = BaseApplication.logicBean.getUsername();
                type = BaseApplication.logicBean.getType();
                email = BaseApplication.logicBean.getEmail();
                tvnickName.setText(username);
            }
        }
    }

    private void initToolBar() {
        //设置导航的图标：类似点击它就出现侧滑菜单的那个
        toolBar.setNavigationIcon(R.mipmap.menu);
        //设置标题及颜色
        toolBar.setTitle(getResources().getString(R.string.tool_bar_title));
        toolBar.setTitleTextColor(getResources().getColor(R.color.write));
        setSupportActionBar(toolBar);
    }

    /**
     * 关联DrawerLayout的状态
     */
    private void initDrawerLayout() {
        civHeaderPhoto.setImageResource(R.mipmap.ear);

        drawerToggle = new ActionBarDrawerToggle(this, dlDrawerLayout, toolBar, R.string.open, R.string.close);
        //将ActionDrawerToggle与DrawerLayout的状态同步
        drawerToggle.syncState();
        dlDrawerLayout.addDrawerListener(drawerToggle);
    }

    /**
     * 处理侧边栏初始数据
     */
    private void initDrawerSlideData() {
        drawerAdapter = new DrawerLayoutAdapter(this, drawerItemTitle, drawerItemIcon);
        lvDrawerLayout.setAdapter(drawerAdapter);
        lvDrawerLayout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String drawerItemTitleType = drawerItemTitle.get(position);
                if (!StringUtils.isTrimEmpty(drawerItemTitleType)) {
                    listViewAdapterItemClick(drawerItemTitleType);
                } else {
                    Log.i("TAG", "onItemClick: drawerItemTitleType is empty");
                }
            }
        });
    }

    private void listViewAdapterItemClick(String drawerItemTitleType) {
        switch (drawerItemTitleType) {
            case "我的收藏":
                startActivity(new Intent(MainActivity.this, CollectionActivity.class));
                break;
            case "我的书签":
                ToastInfo.longToast(this, "对不起，该功能暂未开放，敬请期待");
//                startActivity(new Intent(MainActivity.this, BookMarksActivity.class));
                break;
            case "浏览记录":
                ToastInfo.longToast(this, "对不起，该功能暂未开放，敬请期待");
//                startActivity(new Intent(MainActivity.this, BrowseHistoryActivity.class));
                break;
            case "退出登录":
                boolean outLogin = greenDaoUtils.deleteAll();
                if (outLogin){
                    boolean outLoginSp = SharedPreferencesUtils.setBoolean(this, CommonVariable.LOGINSTATE, false);
                    if (outLoginSp){
                        ToastInfo.longToast(this, "您已退出登录");
                        tvnickName.setText("点击登录");
                        BaseApplication.cookieJar.clear();
                        BaseApplication.cookieJar.clearSession();
                    }
                }
//                startActivity(new Intent(MainActivity.this, SettingActivity.class));
                break;
            default:
                break;
        }
    }

    private void addDrawerSlideData() {
        drawerItemTitle = new ArrayList<>();
        drawerItemIcon = new ArrayList<>();
        drawerItemTitle.add("我的收藏");
        drawerItemTitle.add("我的书签");
        drawerItemTitle.add("浏览记录");
        drawerItemTitle.add("退出登录");
        drawerItemIcon.add(R.mipmap.collection);
        drawerItemIcon.add(R.mipmap.bookmarks);
        drawerItemIcon.add(R.mipmap.browse_history);
        drawerItemIcon.add(R.mipmap.setting);
    }

    private void loadFragmentView(int position) {
        clearNavigationButton();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        hideFragment(fragmentTransaction);
        selectedFragment(position, fragmentTransaction);
    }

    private void selectedFragment(int position, FragmentTransaction fragmentTransaction){
        setNavigationButton(position);
        switch (position){
            case 0:
                if (homeFragment == null) {
                    homeFragment = new HomeFragment();
                    fragmentTransaction.add(R.id.flMainContent, homeFragment);
                }else {
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case 1:
                if (knowledgeFragment == null) {
                    knowledgeFragment = new KnowledgeFragment();
                    fragmentTransaction.add(R.id.flMainContent, knowledgeFragment);
                }else {
                    fragmentTransaction.show(knowledgeFragment);
                }
                break;
            case 2:
                if (fireHotFragment == null){
                    fireHotFragment = new FireHotFragment();
                    fragmentTransaction.add(R.id.flMainContent, fireHotFragment);
                }else {
                    fragmentTransaction.show(fireHotFragment);
                }
                break;
            default:
                break;
        }
        fragmentTransaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (fireHotFragment != null) {
            fragmentTransaction.hide(fireHotFragment);
        }
        if (homeFragment != null) {
            fragmentTransaction.hide(homeFragment);
        }
        if (knowledgeFragment != null) {
            fragmentTransaction.hide(knowledgeFragment);
        }
    }

    private void setNavigationButton(int indexPosition){
        switch (indexPosition){
            case 0:
                ivHome.setImageResource(R.mipmap.home);
                tvHome.setTextColor(getResources().getColor(R.color.tab_navigation));
                break;
            case 1:
                ivKnowledge.setImageResource(R.mipmap.content);
                tvKnowledge.setTextColor(getResources().getColor(R.color.tab_navigation));
                break;
            case 2:
                ivFireHot.setImageResource(R.mipmap.fire_hot);
                tvFireHot.setTextColor(getResources().getColor(R.color.tab_navigation));
                break;
            default:
                break;
        }
    }

    private void clearNavigationButton() {
        ivHome.setImageResource(R.mipmap.home_un);
        tvHome.setTextColor(getResources().getColor(R.color.tab_navigation_un));

        ivKnowledge.setImageResource(R.mipmap.content_un);
        tvKnowledge.setTextColor(getResources().getColor(R.color.tab_navigation_un));

        ivFireHot.setImageResource(R.mipmap.fire_hot_un);
        tvFireHot.setTextColor(getResources().getColor(R.color.tab_navigation_un));
    }

    @Override
    protected void onStop() {
        super.onStop();
        dlDrawerLayout.closeDrawers();
    }

    @OnClick({R.id.llHome, R.id.llKnowledge, R.id.llFireHot, R.id.civHeaderPhoto, R.id.tvnickName})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            //加载首页
            case R.id.llHome:
                loadFragmentView(0);
                break;
            //加载知识体系
            case R.id.llKnowledge:
                loadFragmentView(1);
                break;
            //加载 热门关注
            case R.id.llFireHot:
                loadFragmentView(2);
                break;
            //侧边菜单 更换头像
            case R.id.civHeaderPhoto:
                Toast.makeText(this, "要更换头像么？", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tvnickName:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                Toast.makeText(this, "要登录么？", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
