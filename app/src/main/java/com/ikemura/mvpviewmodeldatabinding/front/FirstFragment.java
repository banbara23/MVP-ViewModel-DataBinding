package com.ikemura.mvpviewmodeldatabinding.front;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.databinding.FragmentFirstBinding;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;
import com.ikemura.mvpviewmodeldatabinding.front.home.ChildHomeTabFragment;

public class FirstFragment extends ChildHomeTabFragment {
    public static final String FRAGMENT_TAG = FirstFragment.class.getSimpleName();
    public static final String TAG = "Fragment";

    private final DebugLogger mLogger = new DebugLogger(FirstFragment.class);

    private FragmentFirstBinding mBinding;

    public static FirstFragment newInstance() {
        FirstFragment fragment = new FirstFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_first, container, false);

        mBinding = DataBindingUtil.bind(contentView);
        mBinding.setNavigator(getRootNavigator());

        return contentView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initToolBarToggle(mBinding.header.toolbar);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBinding.unbind();
    }
}
