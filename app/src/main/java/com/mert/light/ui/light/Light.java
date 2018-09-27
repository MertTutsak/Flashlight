package com.mert.light.ui.light;


import com.mert.light.ui.base.MvpPresenter;
import com.mert.light.ui.base.MvpView;

public interface Light {
    //View işlemleri burada olur.
    interface View extends MvpView {
    }

    //Data işlemleri burada olur.
    interface Presenter<V extends View> extends MvpPresenter<V> {
        int state();
        void On();
        void GoOn();
        void Off();
    }
}
