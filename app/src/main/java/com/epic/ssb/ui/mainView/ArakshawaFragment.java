package com.epic.ssb.ui.mainView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.epic.ssb.R;
import com.epic.ssb.data.ArakshawaModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.remote.ArakshawaSync;
import com.epic.ssb.ui.base.BaseFragment;
import com.epic.ssb.util.MyDatePicker;

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
    EditText monthlyPension;
    Unbinder unbinder;
    @BindView(R.id.btnSurekumaSubmit)
    Button btnSurekumaSubmit;
    ArakshawaModel arakshawaModel;
    @Inject
    MainApplicationPresenter mainApplicationPresenter;

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
        arakshawaModel = ((MainApplicationActivity)getActivity()).arakshawaModel;
        activityComponent().inject(this);
        mainApplicationPresenter.attachView(this);

        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.editDOB,R.id.btnSurekumaSubmit})
    public void onViewClicked(View view) {

        switch (view.getId()){
            case R.id.editDOB:
                MyDatePicker.showDatePicker(getActivity(), new MyDatePicker.DatePickerEvent() {
                    @Override
                    public void onDatePicked(String date) {
                        editDOB.setText(date);
                    }
                });
            case R.id.btnSurekumaSubmit:
                arakshawaModel.setName(editName.getText().toString());
                arakshawaModel.setAddress(editAddress.getText().toString());
                arakshawaModel.setDob(editDOB.getText().toString());
                arakshawaModel.setNameOfGuardian(editGuardianName.getText().toString());
                arakshawaModel.setNicOfGuardian(editNIC.getText().toString());
                arakshawaModel.setMonthlyPayment(editTypePremium.getText().toString());
                arakshawaModel.setTypeOfPensionPayment(monthlyPension.getText().toString());
                mainApplicationPresenter.clientRegistrationArakshawa(arakshawaModel);

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
