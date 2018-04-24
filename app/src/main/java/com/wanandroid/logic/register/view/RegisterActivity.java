package com.wanandroid.logic.register.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.wanandroid.R;
import com.wanandroid.common.base.BaseActivity;
import com.wanandroid.logic.login.view.LoginActivity;
import com.wanandroid.logic.register.moudle.RegisterBean;
import com.wanandroid.logic.register.moudle.RegisterResult;
import com.wanandroid.logic.register.presenter.RegisterPresenter;
import com.wanandroid.utils.auxiliary.AppManager;
import com.wanandroid.utils.auxiliary.StringUtils;
import com.wanandroid.utils.viewutils.ToastInfo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Administrator
 */
public class RegisterActivity extends BaseActivity implements ViewRegisterInterface {
    RegisterPresenter registerPresenter;

    @BindView(R.id.tvRegisterBack)
    TextView tvRegisterBack;
    @BindView(R.id.etInputRegisterAccount)
    EditText etInputRegisterAccount;
    @BindView(R.id.etInputRegisterUserPSW)
    EditText etInputRegisterUserPSW;
    @BindView(R.id.etSureRegisterUserPSW)
    EditText etSureRegisterUserPSW;
    @BindView(R.id.btRegister)
    Button btRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);

        registerPresenter = new RegisterPresenter(this);


    }

    @Override
    public String getUsername() {

        return etInputRegisterAccount.getText().toString();
    }

    @Override
    public String getPassword() {
        return etInputRegisterUserPSW.getText().toString();
    }

    @Override
    public String getRepassword() {
        return etSureRegisterUserPSW.getText().toString();
    }

    @Override
    public void initiateRegister() {
        if (StringUtils.isTrimEmpty(getUsername())) {
            ToastInfo.longToast(RegisterActivity.this, "对不起，您还没有 输入 账号");
            throw new NullPointerException("对不起，您还没有 输入 账号");
        }
        if (StringUtils.isTrimEmpty(getPassword())) {
            ToastInfo.longToast(RegisterActivity.this, "对不起，您还没有 输入 密码");
            throw new NullPointerException("对不起，您还没有 输入 密码");
        }
        if (StringUtils.isTrimEmpty(getRepassword())) {
            ToastInfo.longToast(RegisterActivity.this, "对不起，您还没有 确认 密码");
            throw new NullPointerException("对不起，您还没有 确认 密码");
        }
        RegisterBean registerBean = new RegisterBean();
        registerBean.setUserName(getUsername());
        registerBean.setPassWord(getPassword());
        registerBean.setRepassWord(getRepassword());


        registerPresenter.register(registerBean);
    }

    @Override
    public void registerSuccess(int registerCode, RegisterResult registerResult) {
        ToastInfo.longToast(RegisterActivity.this, "恭喜您，注册成功，现在登录");
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("registerResult", registerResult);
        startActivity(intent);
    }

    @Override
    public void registerFailed(int registerCode, String smg) {
        ToastInfo.longToast(RegisterActivity.this, smg);
    }

    @OnClick({R.id.tvRegisterBack, R.id.btRegister})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            /**返回*/
            case R.id.tvRegisterBack:
                onBackPressed();
                AppManager.getAppManager().finishActivity(this);
                break;
            /**注册*/
            case R.id.btRegister:
                initiateRegister();
                break;
            default:
                break;
        }
    }
}
