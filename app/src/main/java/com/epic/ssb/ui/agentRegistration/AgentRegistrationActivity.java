package com.epic.ssb.ui.agentRegistration;

import android.Manifest;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.epic.ssb.R;
import com.epic.ssb.data.AgentModel;
import com.epic.ssb.ui.base.BaseActivity;
import com.epic.ssb.ui.menu.MenuActivity;
import com.epic.ssb.util.AppDialog;
import com.epic.ssb.util.AppPref;
import com.epic.ssb.util.Constant;
import com.epic.ssb.util.ImageControl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AgentRegistrationActivity extends BaseActivity implements AgentRegistrationView {

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editEmail)
    EditText editEmail;
    @BindView(R.id.editGender)
    EditText editGender;
    @BindView(R.id.editNIC)
    EditText editNIC;
    @BindView(R.id.editTelephone)
    EditText editTelephone;
    @BindView(R.id.editDistrict)
    EditText editDistrict;
    @BindView(R.id.editDsOffice)
    EditText editDsOffice;
    @BindView(R.id.ivPhoto)
    ImageView ivPhoto;
    @BindView(R.id.btnPhoto)
    Button btnPhoto;
    @BindView(R.id.btnSurekumaSubmit)
    Button btnSurekumaSubmit;
    private Uri imageUri;
    private String[] permissions;
    Context context;

    AppPref appPref;
    AgentModel agentModel = new AgentModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agent_registration);
        ButterKnife.bind(this);

        appPref = new AppPref(this);

        permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        checkPermissions();

        ivPhoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!((BitmapDrawable) ivPhoto.getDrawable()).getBitmap().sameAs(((BitmapDrawable) getResources().getDrawable(R.drawable.empty_img_capture)).getBitmap()))
                    AppDialog.showQuestionDialog(context, "Do you want to remove this photo", new AppDialog.DialogEvent() {
                        @Override
                        protected void onPositiveClicked() {
                            super.onPositiveClicked();
                            agentModel.setPhotoOfAgent("");
                            appPref.putImageUri("", Constant.PREF_APPLICANT_IMAGE_URI);
                            ivPhoto.setImageDrawable(getResources().getDrawable(R.drawable.empty_img_capture));
                        }

                        @Override
                        protected void onNegativeClicked() {
                            super.onNegativeClicked();
                        }
                    });
                return false;
            }
        });

    }

    private void captureImage() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Constant.PICTURE_RESULT);
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
                Toast.makeText(this, "Permissions granted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @OnClick({R.id.btnPhoto,R.id.btnSurekumaSubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case  R.id.btnPhoto:
                try {
                    captureImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btnSurekumaSubmit:

                final AlertDialog.Builder builder = new AlertDialog.Builder(this);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        builder.setTitle("Confirmation Alert !");
                        builder.setMessage("Are you sure to submit details?");
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                agentModel.setNameOfAgent(editName.getText().toString());
                                agentModel.setAddress(editAddress.getText().toString());
                                agentModel.setEmail(editEmail.getText().toString());
                                agentModel.setGender(editGender.getText().toString());
                                agentModel.setNic(editNIC.getText().toString());
                                agentModel.setTelephone(editTelephone.getText().toString());
                                agentModel.setDistrict(editDistrict.getText().toString());
                                agentModel.setDsOffice(editDsOffice.getText().toString());
                                String s = new Gson().toJson(agentModel);
                                System.out.println(s);
                                startActivity(new Intent(AgentRegistrationActivity.this, MenuActivity.class));

                            }
                        });
                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // User cancelled the dialog
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.PICTURE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String uri = String.valueOf(imageUri);
                    appPref.putImageUri(uri, Constant.PREF_APPLICANT_IMAGE_URI);
                    Glide.with(ivPhoto.getContext()).load(uri).asBitmap().override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL).fitCenter().into(ivPhoto);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    Bitmap scalableBitmap = ImageControl.scaleBitmap(bitmap, 800, true);
                    String pic = ImageControl.bitmapToString(scalableBitmap);
                    agentModel.setPhotoOfAgent(pic);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


}
