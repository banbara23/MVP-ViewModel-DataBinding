package com.ikemura.mvpviewmodeldatabinding.common.lifecycle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public interface Lifecycle {

    void onCreate(@Nullable Bundle savedInstanceState);

    void onSaveInstanceState(@NonNull Bundle outState);

    void onStart();

    void onResume();

    void onPause();

    void onStop();
}
