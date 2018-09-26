package com.mert.light.ui.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import com.mert.light.R;

public class ProgressDialog extends Dialog {

    public ProgressDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public void create() {
        super.create();
        this.setContentView(R.layout.dialog_progress);
        this.setCancelable(false);

        if (!isShowing()) {
            show();
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
    }
}


