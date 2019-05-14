package com.epic.ssb.ui.alert;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.epic.ssb.R;
import com.epic.ssb.ui.login.LoginActivity;
import com.epic.ssb.ui.mainView.MainApplicationActivity;
import com.epic.ssb.ui.menu.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertActivity extends AppCompatActivity {

    @BindView(R.id.login_emailid)
    TextView loginEmailid;
    @BindView(R.id.login_password)
    TextView loginPassword;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.login_layout)
    LinearLayout loginLayout;
    @BindView(R.id.createAccount)
    TextView createAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alert);
        ButterKnife.bind(this);

//        new CountDownTimer(1000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//            }
//
//            public void onFinish() {
//                startActivity(new Intent(AlertActivity.this, LoginActivity.class));
//                finish();
//            }
//
//        }.start();

    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {
        startActivity(new Intent(AlertActivity.this, MenuActivity.class));
//                finish();
    }
}
