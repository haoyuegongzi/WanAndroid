package com.wanandroid.logic.login.view;

import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.common.CommonVariable;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.login.moudle.LogicGreenDaoBean;
import com.wanandroid.logic.login.moudle.LoginBean;
import com.wanandroid.logic.login.moudle.UserInfo;
import com.wanandroid.logic.login.presenter.LoginPresenter;
import com.wanandroid.logic.mainpage.view.MainActivity;
import com.wanandroid.logic.register.moudle.RegisterResult;
import com.wanandroid.logic.register.view.RegisterActivity;
import com.wanandroid.utils.auxiliary.AppManager;
import com.wanandroid.utils.auxiliary.LoginGreenDaoUtils;
import com.wanandroid.utils.auxiliary.SharedPreferencesUtils;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.ToastInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 * TODO：登录首页
 */
public class LoginActivity extends BaseActivity implements ViewLoginInterface {
    LoginPresenter loginPresenter;
    LoginGreenDaoUtils greenDaoUtils;

    @BindView(R.id.tvBack)
    TextView tvBack;
    @BindView(R.id.etUserAccount)
    EditText etUserAccount;
    @BindView(R.id.etUserPSW)
    EditText etUserPSW;
    @BindView(R.id.btLogin)
    Button btLogin;
    @BindView(R.id.tvForgetPSW)
    TextView tvForgetPSW;
    @BindView(R.id.tvRegisterAccount)
    TextView tvRegisterAccount;
    @BindView(R.id.llWeiBo)
    LinearLayout llWeiBo;
    @BindView(R.id.llWeChat)
    LinearLayout llWeChat;
    @BindView(R.id.llGithub)
    LinearLayout llGithub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        greenDaoUtils = LoginGreenDaoUtils.getInstance();
        greenDaoUtils.init(this);
        greenDaoUtils.setUpdataBase();
        greenDaoUtils.initLogInfo();

        loginPresenter = new LoginPresenter(this);

        RegisterResult registerResult = getIntent().getParcelableExtra("registerResult");
        if (registerResult != null) {
            etUserAccount.setText(registerResult.getData().getUsername());
            etUserPSW.setText(registerResult.getData().getPassword());
        }
    }

    @OnClick({R.id.tvBack, R.id.btLogin, R.id.tvForgetPSW, R.id.tvRegisterAccount, R.id.llWeiBo, R.id.llWeChat, R.id.llGithub})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tvBack:
                onBackPressed();
                AppManager.getAppManager().finishActivity(LoginActivity.this);
                break;
            case R.id.btLogin:
                UserInfo userInfo = getUserInfo(etUserAccount.getText().toString().trim(), etUserPSW.getText().toString().trim());
                if (userInfo == null) {
                    throw new NullPointerException("用户登录信息为空");
                }
                loginPresenter.doLoin(userInfo);
                break;
            case R.id.tvForgetPSW:
                ToastInfo.longToast(LoginActivity.this, "对不起，该功能暂未开放，敬请期待");
//                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;
            case R.id.tvRegisterAccount:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            case R.id.llWeiBo:
                ToastInfo.longToast(LoginActivity.this, "对不起，该功能暂未开放，敬请期待");
                break;
            case R.id.llWeChat:
                ToastInfo.longToast(LoginActivity.this, "对不起，该功能暂未开放，敬请期待");
                break;
            case R.id.llGithub:
                ToastInfo.longToast(LoginActivity.this, "对不起，该功能暂未开放，敬请期待");
                break;
            default:
                break;
        }
    }

    @Override
    public UserInfo getUserInfo(String username, String password) {
        UserInfo userInfo = null;
        if ( !StringUtils.isTrimEmpty(username) && !StringUtils.isTrimEmpty(password)) {
            userInfo = new UserInfo(username, password);
            return userInfo;
        }
        if (StringUtils.isTrimEmpty(password)) {
            ToastInfo.longToast(this, CommonVariable.PASSWORD_IS_EMPTY);
            throw new NullPointerException("密码为空");

        }
        if (StringUtils.isTrimEmpty(username)) {
            ToastInfo.longToast(this, CommonVariable.ACCOUNT_IS_EMPTY);
            throw  new NullPointerException("账号为空");
        }
        return userInfo;
    }

    @Override
    public void loginSuccess(int loginCode, LoginBean loginBean) {
        if (loginBean == null) {
            throw new UnsupportedOperationException("数据为空，请检查后再试");
        }

        /**errorCode有时为-1，有时为1.0，不固定，无法准确适用，故用errorMsg来判断*/
        if (!StringUtils.isTrimEmpty(loginBean.getErrorMsg())) {
            ToastInfo.longToast(this, loginBean.getErrorMsg());
            throw new IllegalArgumentException(loginBean.getErrorMsg() + "的异常");
        }

        /**登录成功 数据库保存数据*/
        if ("0".equals(loginBean.getErrorCode()) && StringUtils.isTrimEmpty(loginBean.getErrorMsg())) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);

            intent.putExtra(CommonVariable.LOGINSTATE, true );
            startActivity(intent);
            /**往数据库存储登录信息，true：保存成功；false：保存失败*/
            boolean insertFlag = operationSQLite(loginBean);
            if (insertFlag) {
                /**往SharedPreferences里面存储登录状态*/
                boolean saveLogin = SharedPreferencesUtils.setBoolean(LoginActivity.this, CommonVariable.LOGINSTATE, insertFlag);
                Log.i(TAG, "loginSuccess: saveLogin ===" + saveLogin + "\n insertFlag ===" + insertFlag);
            }
        }
    }

    @Override
    public void loginFailed(int lofinCode, LoginBean loginBean) {
        ToastInfo.longToast(this, "登录失败，请稍候再试");
    }

    private boolean operationSQLite(LoginBean loginBean){
        boolean insertFlag = false;
        LogicGreenDaoBean logicGreenDaoBean = new LogicGreenDaoBean();

        logicGreenDaoBean.setEmail(loginBean.getData().getEmail());
        logicGreenDaoBean.setIcon(loginBean.getData().getIcon());
        logicGreenDaoBean.setId(Long.parseLong(loginBean.getData().getId()));
        logicGreenDaoBean.setPassword(loginBean.getData().getPassword());
        logicGreenDaoBean.setType(loginBean.getData().getType());
        logicGreenDaoBean.setUsername(loginBean.getData().getUsername());

        try {
            insertFlag = greenDaoUtils.insertOrReplaceOne(logicGreenDaoBean);
        }catch ( SQLiteException e){
            Log.i(TAG, "operationSQLite: e===" + e);
        }
        return insertFlag;
    }
}
