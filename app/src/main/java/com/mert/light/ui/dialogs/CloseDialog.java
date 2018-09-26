package com.mert.light.ui.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;

import com.mert.light.R;

public class CloseDialog extends Dialog {

    private Activity activity;

    public CloseDialog(@NonNull Activity activity) {
        super(activity);
        this.activity = activity;
    }

    @Override
    public void create() {
        super.create();

        this.setContentView(R.layout.dialog_close);

        final Button button_positive = (Button) this.findViewById(R.id.buttonPositive);
        final Button button_negative = (Button) this.findViewById(R.id.buttonNegative);

        this.setCancelable(false);

        button_positive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });

        button_negative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        if (!this.isShowing()) {
            this.show();
        }
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
    }

}
