package com.epic.ssb.ui.mainView;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.epic.ssb.R;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.epic.ssb.ui.base.BaseFragment;
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
    EditText monthlyPension;
    @BindView(R.id.btnSurekumaSubmit)
    Button btnSurekumaSubmit;

    Unbinder unbinder;

    @Inject
    MainApplicationPresenter mainApplicationPresenter;
    public SurekumaModel surekumaModel;


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
        surekumaModel = ((MainApplicationActivity)getActivity()).surekumaModel;
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

    @OnClick({R.id.editDOB, R.id.btnSurekumaSubmit})
    public void onViewClicked(View view) {

        switch (view.getId()){

            case R.id.editDOB:
                MyDatePicker.showDatePicker(getActivity(), new MyDatePicker.DatePickerEvent() {
                    @Override
                    public void onDatePicked(String date) {
                        editDOB.setText(date);
                    }
                });
                break;

            case R.id.btnSurekumaSubmit:
                surekumaModel.setName(editName.getText().toString());
                surekumaModel.setAddress(editAddress.getText().toString());
                surekumaModel.setDob(editDOB.getText().toString());
                surekumaModel.setNic(editNIC.getText().toString());
                surekumaModel.setTypeOfPensionPayment(editTypePremium.getText().toString());
                surekumaModel.setMonthlyPayment(monthlyPension.getText().toString());
                mainApplicationPresenter.clientRegistrationSurekuma(surekumaModel);
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
}
