package com.epic.ssb.ui.mainView;

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
import android.support.v7.widget.AppCompatSpinner;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;
import com.epic.ssb.R;
import com.epic.ssb.data.ArakshawaModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.ui.alert.AlertActivity;
import com.epic.ssb.ui.base.BaseFragment;
import com.epic.ssb.ui.menu.MenuActivity;
import com.epic.ssb.util.AppDialog;
import com.epic.ssb.util.AppPref;
import com.epic.ssb.util.Constant;
import com.epic.ssb.util.ImageControl;
import com.epic.ssb.util.MyDatePicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ArakshawaFragment extends BaseFragment implements MainApplicationView {
    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editDOB)
    EditText editDOB;
    @BindView(R.id.editGuardianName)
    EditText editGuardianName;
    @BindView(R.id.editNIC)
    EditText editNIC;
    @BindView(R.id.editTypePremium)
    EditText editTypePremium;
    @BindView(R.id.monthlyPension)
    TextView monthlyPension;
    Unbinder unbinder;
    @BindView(R.id.btnSurekumaSubmit)
    Button btnSurekumaSubmit;
    ArakshawaModel arakshawaModel;
    @Inject
    MainApplicationPresenter mainApplicationPresenter;
    @BindView(R.id.framelay_loan)
    FrameLayout framelayLoan;
    @BindView(R.id.btnCal)
    Button btnCal;
    @BindView(R.id.ivIDPhoto)
    ImageView ivIDPhoto;
    @BindView(R.id.btnIDPhoto)
    Button btnIDPhoto;
    @BindView(R.id.ivBCPhoto)
    ImageView ivBCPhoto;
    @BindView(R.id.btnBCPhoto)
    Button btnBCPhoto;
    @BindView(R.id.spinnerPaymentMethod)
    AppCompatSpinner spinnerPaymentMethod;
    @BindView(R.id.spinnerPaymentTpe)
    AppCompatSpinner spinnerPaymentTpe;
    private String[] permissions;
    Context context;
    private Uri imageUri;
    AppPref appPref;
    private List<String> paymentMethod;
    private List<String> paymentType;

    String[] PaymentMethod = new String[]{"Bank", "PostOffice", "Through Agent", "AG-Office"};
    String[] PayementType = new String[]{"Monthly", "Quarterly", "Annually", "lum-sum"};

//    private OnFragmentInteractionListener mListener;

    public ArakshawaFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_arakshawa, container, false);
        unbinder = ButterKnife.bind(this, view);
        arakshawaModel = ((MainApplicationActivity) getActivity()).arakshawaModel;
        activityComponent().inject(this);
        mainApplicationPresenter.attachView(this);
        appPref = new AppPref(getContext());
        init();

        permissions = new String[]{
                Manifest.permission.CAMERA,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
        };
        checkPermissions();

        ivIDPhoto.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (!((BitmapDrawable) ivIDPhoto.getDrawable()).getBitmap().sameAs(((BitmapDrawable) getResources().getDrawable(R.drawable.empty_img_capture)).getBitmap()))
                    AppDialog.showQuestionDialog(context, "Do you want to remove this photo", new AppDialog.DialogEvent() {
                        @Override
                        protected void onPositiveClicked() {
                            super.onPositiveClicked();
//                            agentModel.setPhotoOfAgent("");
                            appPref.putImageIDUri("", Constant.PREF_APPLICANT_IMAGE_ID_URI);
                            ivIDPhoto.setImageDrawable(getResources().getDrawable(R.drawable.empty_img_capture));
                        }

                        @Override
                        protected void onNegativeClicked() {
                            super.onNegativeClicked();
                        }
                    });
                return false;
            }
        });

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    void init() {
        paymentType = new ArrayList<String>(Arrays.asList(PayementType));
        setSpinnerData(spinnerPaymentTpe, paymentType);

        paymentMethod =  new ArrayList<String>(Arrays.asList(PaymentMethod));
        setSpinnerData(spinnerPaymentMethod, paymentMethod);


    }

    @OnClick({R.id.editDOB, R.id.btnSurekumaSubmit, R.id.btnCal, R.id.btnIDPhoto})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.editDOB:
                MyDatePicker.showDatePicker(getActivity(), new MyDatePicker.DatePickerEvent() {
                    @Override
                    public void onDatePicked(String date) {
                        editDOB.setText(date);
                    }
                });
                break;

            case R.id.btnSurekumaSubmit:

                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        builder.setTitle("Confirmation Alert !");
                        builder.setMessage("Are you sure to submit details?");
                        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                arakshawaModel.setName(editName.getText().toString());
                                arakshawaModel.setAddress(editAddress.getText().toString());
                                arakshawaModel.setDob(editDOB.getText().toString());
                                arakshawaModel.setNameOfGuardian(editGuardianName.getText().toString());
                                arakshawaModel.setNicOfGuardian(editNIC.getText().toString());
                                arakshawaModel.setMonthlyPayment(editTypePremium.getText().toString());
                                arakshawaModel.setTypeOfPensionPayment(monthlyPension.getText().toString());
                                mainApplicationPresenter.clientRegistrationArakshawa(arakshawaModel);
//                                Toast.makeText(context, "Reference no is : REF000001323", Toast.LENGTH_LONG).show();
                                startActivity(new Intent(getContext(), AlertActivity.class));
//                                Toast.makeText(context, "Reference no is : REF000001323", Toast.LENGTH_LONG).show();

                            }
                        });
                        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
                break;
            case R.id.btnCal:
                monthlyPension.setText(calculateMonthlyPayment(Double.parseDouble(editTypePremium
                        .getText().toString())));
                break;
            case R.id.btnIDPhoto:
                try {
                    captureImage();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }

    }

    private void captureImage() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "New Picture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri = getContext().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
        startActivityForResult(intent, Constant.PICTURE_RESULT);
    }


    @Override
    public void showErrorMessage(String msg) {

        super.showErrorMessage(msg);
    }

    @Override
    public void showToast(String msg) {
        super.showToast(msg);
    }

    @Override
    public void showProgressDialog() {
        super.showProgressDialog();
    }

    @Override
    public void hideProgressDialog() {
        super.hideProgressDialog();
    }

    @Override
    public void registerSuccessful(ResponseBean responseBean) {

    }

    String calculateMonthlyPayment(double amount) {
        double monthlyPaymeent = (amount * 150) / 1.2;
        String monthlyPayment = Double.toString(monthlyPaymeent);
        return monthlyPayment;
    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getContext(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // do something
                Toast.makeText(getContext(), "Permissions granted!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Constant.PICTURE_RESULT) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String uri = String.valueOf(imageUri);
                    appPref.putImageUri(uri, Constant.PREF_APPLICANT_IMAGE_URI);
                    Glide.with(ivIDPhoto.getContext()).load(uri).asBitmap().override(Target.SIZE_ORIGINAL,
                            Target.SIZE_ORIGINAL).fitCenter().into(ivIDPhoto);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageUri);
                    Bitmap scalableBitmap = ImageControl.scaleBitmap(bitmap, 800, true);
                    String pic = ImageControl.bitmapToString(scalableBitmap);
//                    agentModel.setPhotoOfAgent(pic);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void setSpinnerData(AppCompatSpinner spinner, List<String> list) {
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(),
                R.layout.spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
    }


}
