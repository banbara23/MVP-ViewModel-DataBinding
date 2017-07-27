package com.ikemura.mvpviewmodeldatabinding.front.home;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.common.lifecycle.OnCreateListener;
import com.ikemura.mvpviewmodeldatabinding.core.BaseFragment;
import com.ikemura.mvpviewmodeldatabinding.databinding.HomeFragmentBinding;

public class HomeFragment extends BaseFragment implements HomeConstract.View {
    public static final String TAG = HomeFragment.class.getSimpleName();

    private HomePresenter mPresenter = new HomePresenter(this, new OnCreateListener() {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            mPresenter.onCreate(savedInstanceState, HomeFragment.this, getRootNavigator(), getChildFragmentManager());
        }
    });

    private HomeFragmentBinding mBinding;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public HomeConstract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public android.view.View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        android.view.View contentView = inflater.inflate(R.layout.home_fragment, container, false);

        mBinding = DataBindingUtil.bind(contentView);
        mBinding.setNavigator(getRootNavigator());
        mBinding.setPresenter(getPresenter());

        mBinding.homeWrapper.homeBottomView.enableAnimation(false);
        mBinding.homeWrapper.homeBottomView.enableShiftingMode(false);
        mBinding.homeWrapper.homeBottomView.enableItemShiftingMode(false);

        return contentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //removeLifecycle(mPresenter);
    }

    @Override
    public boolean closeDrawer() {
        int gravity = Gravity.LEFT;
        if (mBinding.rootDrawerLayout.isDrawerOpen(gravity)) {
            mBinding.rootDrawerLayout.closeDrawer(gravity);
            return true;
        }
        return false;
    }
}
