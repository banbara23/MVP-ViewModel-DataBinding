package com.ikemura.mvpviewmodeldatabinding.common;

import android.support.v4.app.FragmentTransaction;

import com.ikemura.mvpviewmodeldatabinding.R;

public class FragmentUtils {

    /**
     * 遷移アニメーションのセット
     * Transition Animation
     */
    public static boolean setPredefinedTransitionAnimation(FragmentTransaction ft, int transition_type) {
        if (setPredefinedTransitionAnimationImpl(ft, transition_type)) {
            return true;
        }

        return false;
    }

    // %%

    /**
     * Public Constant Params for transition anmation.
     */
    // Transition Animation
    public static final int TRANSACTION_ANIMATION_TYPE_NONE = 0;
    public static final int TRANSACTION_ANIMATION_TYPE_NOANIM = 1;
    public static final int TRANSACTION_ANIMATION_TYPE_PUSH = 2;
    public static final int TRANSACTION_ANIMATION_TYPE_MODAL = 3;
    public static final int TRANSACTION_ANIMATION_TYPE_WIPE = 4;
    public static final int TRANSACTION_ANIMATION_TYPE_MODAL2 = 5;
    public static final int TRANSACTION_ANIMATION_TYPE_PUSH_BACK = 6;
    public static final int TRANSACTION_ANIMATION_TYPE_MODAL_VERTICAL = 7;

    private static boolean setPredefinedTransitionAnimationImpl(FragmentTransaction ft, int transition_type) {
        switch (transition_type) {
            case TRANSACTION_ANIMATION_TYPE_NONE:
                ft.setCustomAnimations(0, 0, 0, 0);
                break;
            case TRANSACTION_ANIMATION_TYPE_NOANIM:
                ft.setCustomAnimations(R.anim.no_anim, R.anim.no_anim, R.anim.no_anim, R.anim.no_anim);
                break;
            case TRANSACTION_ANIMATION_TYPE_PUSH:
                ft.setCustomAnimations(R.anim.side_right_in, R.anim.side_left_out, R.anim.side_left_in, R.anim.side_right_out);
                break;
            case TRANSACTION_ANIMATION_TYPE_MODAL:
                ft.setCustomAnimations(R.anim.delay_fade_in, R.anim.scale_fade_out, R.anim.scale_fade_in, R.anim.delay_fade_out);
                break;
            case TRANSACTION_ANIMATION_TYPE_WIPE:
                ft.setCustomAnimations(R.anim.menu_in, R.anim.menu_out, R.anim.menu_in, R.anim.menu_out);
                break;
            case TRANSACTION_ANIMATION_TYPE_MODAL2:
                ft.setCustomAnimations(R.anim.delay_fade_in, R.anim.scale_fade_out, R.anim.scale_fade_in, R.anim.delay_fade_out2);
                break;
            case TRANSACTION_ANIMATION_TYPE_PUSH_BACK:
                ft.setCustomAnimations(R.anim.side_left_in, R.anim.side_right_out, R.anim.side_right_in, R.anim.side_left_out);
                break;
            case TRANSACTION_ANIMATION_TYPE_MODAL_VERTICAL:
                ft.setCustomAnimations(R.anim.modal_in, R.anim.modal_out, R.anim.modal_in, R.anim.modal_out);
                break;
            default:
                return false;
        }

        return true;
    }
}
