package com.epic.ssb.ui.clientRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.epic.ssb.R;
import com.epic.ssb.ui.base.BaseActivity;
import com.epic.ssb.ui.menu.MenuActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClientRegistration extends BaseActivity implements ClientRegistrationView {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);
        ButterKnife.bind(this);


    }

}
