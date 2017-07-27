package com.ikemura.mvpviewmodeldatabinding.front.home;

import android.support.annotation.NonNull;
import android.view.MenuItem;

import com.ikemura.mvpviewmodeldatabinding.core.BasePresenter;
import com.ikemura.mvpviewmodeldatabinding.core.BaseView;
import com.ikemura.mvpviewmodeldatabinding.core.Navigator;

public class HomeConstract {

    public interface Presenter extends BasePresenter, Navigator {
        void onClick(int id);

        boolean onNavigationClick(@NonNull MenuItem item);
    }

    public interface View extends BaseView<Presenter> {
        boolean closeDrawer();
    }
}
