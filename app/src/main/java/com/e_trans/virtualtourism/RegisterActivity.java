package com.e_trans.virtualtourism;

import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.e_trans.virtualtourism.Base.BaseActivity;
import com.e_trans.virtualtourism.Base.Config;
import com.e_trans.virtualtourism.utils.PrefUtils;
import com.e_trans.virtualtourism.utils.StatusBarCompat;
import com.e_trans.virtualtourism.utils.statusbar.StatusBarFontHelper;
import com.e_trans.virtualtourism.view.Code;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    @BindView(R.id.iv_login_user)
    ImageView ivLoginUser;
    @BindView(R.id.et_user)
    EditText etUser;
    @BindView(R.id.iv_select_user)
    ImageView ivSelectUser;
    @BindView(R.id.iv_login_pass_word)
    ImageView ivLoginPassWord;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.realcode)
    EditText realcode;

    @BindView(R.id.iv_remove_password)
    ImageView ivRemovePassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_code)
    ImageView iv_code;
    private String realCode;
    private boolean islook = true;
    @Override
    protected int layoutRes() {
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        return  R.layout.activity_register;
    }

    @Override
    protected void initView() {
        ButterKnife.bind(this);
        StatusBarCompat.setStatusBarColor(this, 0xfffffff);
        StatusBarFontHelper.setStatusBarMode(this, true);
        etUser.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                setClearIconVisible();
            }
        });
        setClearIconVisible();
        //将验证码用图片的形式显示出来
        iv_code.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }

    /**
     * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
     *
     */
    private void setClearIconVisible() {
         ivSelectUser.setVisibility( etUser.getText().length()>0?View.VISIBLE:View.GONE);
    }
    @OnClick({R.id.iv_login_user, R.id.iv_select_user, R.id.iv_login_pass_word, R.id.iv_remove_password, R.id.btn_login,R.id.iv_code})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_login_user:
                break;
            case R.id.iv_select_user:
                etUser.setText("");
                ivSelectUser.setVisibility(View.GONE);
                break;
            case R.id.iv_login_pass_word:
                break;
            case R.id.iv_remove_password:
                if (islook) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    etPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                islook = !islook;
                break;
            case R.id.btn_login:
                String etCode=realcode.getText().toString().toLowerCase();
                    if(!realCode.equals(etCode)){
                        Toast.makeText(getApplicationContext(),"验证码错误",Toast.LENGTH_SHORT).show();
                    return;
                    }
                getLogin(etUser.getText().toString().trim(), etPassword.getText().toString().trim());
                break;
            case R.id.iv_code:
                iv_code.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;
        }
    }


    /**
     * 登录
     */
    private void getLogin(String userName, String pwd) {
        if (TextUtils.isEmpty(userName)) {
            Toast.makeText(RegisterActivity.this, "用户名不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            Toast.makeText(RegisterActivity.this, "密码不能为空", Toast.LENGTH_LONG).show();
            return;
        }
        Map<String, String> maps = new HashMap<>();
        maps.put("userName", userName);
        maps.put("pwd", pwd);
        PrefUtils.putString(getApplication(), Config.USER_NAME, etUser.getText().toString().trim());
        PrefUtils.putString(getApplication(), Config.PASS_WORD, etPassword.getText().toString().trim());
        PrefUtils.putInt(getApplicationContext(), Config.LOGIN_DATA_BEAN_STATUS, 1);
        Toast.makeText(getApplicationContext(),"注册成功",Toast.LENGTH_SHORT).show();
        finish();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
