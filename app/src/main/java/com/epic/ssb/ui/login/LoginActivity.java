package com.epic.ssb.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.epic.ssb.R;
import com.epic.ssb.ui.base.BaseActivity;
import com.epic.ssb.ui.menu.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginView {

    @BindView(R.id.login_emailid)
    EditText loginEmailid;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.show_hide_password)
    CheckBox showHidePassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.loginBtn)
    void OnClickBtn(){
        startActivity(new Intent(LoginActivity.this, MenuActivity.class));
    }
}
