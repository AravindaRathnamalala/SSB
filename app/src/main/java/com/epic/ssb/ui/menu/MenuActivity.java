package com.epic.ssb.ui.menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.epic.ssb.R;
import com.epic.ssb.ui.agentRegistration.AgentRegistrationActivity;
import com.epic.ssb.ui.clientRegistration.ClientRegistration;
import com.epic.ssb.ui.mainView.MainApplicationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.btnClientRegistration)
    Button btnClientRegistration;
    @BindView(R.id.btnAgentRegistration)
    Button btnAgentRegistration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnClientRegistration, R.id.btnAgentRegistration})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnClientRegistration:
                startActivity(new Intent(MenuActivity.this, MainApplicationActivity.class));
                break;
            case R.id.btnAgentRegistration:
                startActivity(new Intent(MenuActivity.this, AgentRegistrationActivity.class));
                break;
        }
    }
}
