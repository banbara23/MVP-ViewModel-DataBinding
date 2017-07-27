package com.ikemura.mvpviewmodeldatabinding.front;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ikemura.mvpviewmodeldatabinding.R;
import com.ikemura.mvpviewmodeldatabinding.databinding.FragmentFourBinding;
import com.ikemura.mvpviewmodeldatabinding.debug.DebugLogger;
import com.ikemura.mvpviewmodeldatabinding.front.home.ChildHomeTabFragment;

public class FourFragment extends ChildHomeTabFragment {
    public static final String FRAGMENT_TAG = FourFragment.class.getSimpleName();
    public static final String TAG = "Fragment";

    private final DebugLogger mLogger = new DebugLogger(FourFragment.class);

    private FragmentFourBinding mBinding;

    public static FourFragment newInstance() {
        FourFragment fragment = new FourFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_four, container, false);

        mBinding = DataBindingUtil.bind(contentView);

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
