package com.epic.ssb.ui.mainView;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.epic.ssb.R;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.epic.ssb.ui.base.BaseFragment;
import com.epic.ssb.ui.menu.MenuActivity;
import com.epic.ssb.util.MyDatePicker;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SurekumaFragment extends BaseFragment implements MainApplicationView {

    @BindView(R.id.editName)
    EditText editName;
    @BindView(R.id.editAddress)
    EditText editAddress;
    @BindView(R.id.editDOB)
    EditText editDOB;
    @BindView(R.id.editNIC)
    EditText editNIC;
    @BindView(R.id.editTypePremium)
    EditText editTypePremium;
    @BindView(R.id.monthlyPension)
    TextView monthlyPension;
    @BindView(R.id.btnSurekumaSubmit)
    Button btnSurekumaSubmit;

    Unbinder unbinder;

    @Inject
    MainApplicationPresenter mainApplicationPresenter;
    public SurekumaModel surekumaModel;
    @BindView(R.id.btnCal)
    Button btnCal;


    public SurekumaFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_surekuma, container, false);
        surekumaModel = ((MainApplicationActivity) getActivity()).surekumaModel;
        activityComponent().inject(this);
        mainApplicationPresenter.attachView(this);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.editDOB, R.id.btnSurekumaSubmit, R.id.btnCal})
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
                                surekumaModel.setName(editName.getText().toString());
                                surekumaModel.setAddress(editAddress.getText().toString());
                                surekumaModel.setDob(editDOB.getText().toString());
                                surekumaModel.setNic(editNIC.getText().toString());
                                surekumaModel.setTypeOfPensionPayment(editTypePremium.getText().toString());
                                surekumaModel.setMonthlyPayment(monthlyPension.getText().toString());
                                mainApplicationPresenter.clientRegistrationSurekuma(surekumaModel);
                                startActivity(new Intent(getContext(), MenuActivity.class));
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
                break;

            case R.id.btnCal:
                monthlyPension.setText(calculateMonthlyPayment(Double.parseDouble(editTypePremium.getText().toString())));
                break;


        }

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

    String calculateMonthlyPayment(double amount){
        double monthlyPaymeent = (amount*150)/1.2;
        String monthlyPayment = Double.toString(monthlyPaymeent);
        return monthlyPayment;
    }


}
