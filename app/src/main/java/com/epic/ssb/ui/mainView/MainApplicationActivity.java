package com.epic.ssb.ui.mainView;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import com.epic.ssb.R;
import com.epic.ssb.data.ArakshawaModel;
import com.epic.ssb.data.ResponseBean;
import com.epic.ssb.data.SurekumaModel;
import com.epic.ssb.ui.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainApplicationActivity extends BaseActivity implements MainApplicationView{

    @Inject
    MainApplicationPresenter presenter;
    @BindView(R.id.tabs)
    TabLayout tabs;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
//    @BindView(R.id.toolbar)
//    Toolbar toolbar;

    public ArakshawaModel arakshawaModel = new ArakshawaModel();
    public SurekumaModel surekumaModel = new SurekumaModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_application);
        ButterKnife.bind(this);
        activityComponent().inject(MainApplicationActivity.this);
        presenter.attachView(this);


//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setupViewPager(viewpager);
//        setSupportActionBar(toolbar);
        tabs.setupWithViewPager(viewpager);

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ArakshawaFragment(), "Arakshawa");
        adapter.addFragment(new SurekumaFragment(), "Surekuma");
        viewPager.setAdapter(adapter);
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

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
