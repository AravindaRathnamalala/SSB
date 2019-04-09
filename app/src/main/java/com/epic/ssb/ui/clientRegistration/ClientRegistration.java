package com.epic.ssb.ui.clientRegistration;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.epic.ssb.R;
import com.epic.ssb.ui.base.BaseActivity;
import com.epic.ssb.util.Constant;

import java.util.ArrayList;
import java.util.List;

public class ClientRegistration extends BaseActivity implements ClientRegistrationView {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_registration);


    }



}
