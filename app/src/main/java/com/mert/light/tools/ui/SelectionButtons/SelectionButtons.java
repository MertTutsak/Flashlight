package com.mert.light.tools.ui.SelectionButtons;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;

import com.mert.light.R;

public class SelectionButtons {
    private Button[] buttons;

    private OnClickListenerSelectionButtons onClickListenerSelectionButtons;

    private int selection;

    public SelectionButtons(final Button[] buttons, final OnClickListenerSelectionButtons onClickListenerSelectionButtons) {
        this.buttons = buttons;
        this.onClickListenerSelectionButtons = onClickListenerSelectionButtons;

        this.selection = -1;

        for (int i = 0; i < buttons.length; i++) {
            final int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selection = finalI;
                    colorClear(finalI);
                    onClickListenerSelectionButtons.OnClick(selection);

                }
            });
        }
    }

    public Button[] getButtons() {
        return buttons;
    }

    @SuppressLint("ResourceAsColor")
    private void colorClear(int position) {
        for (int i = 0; i < buttons.length; i++) {
            if (position != i) {
                buttons[i].setBackgroundColor(R.attr.color);
            } else {
                buttons[i].setBackgroundColor(R.color.colorPrimary);
            }
        }
    }

    public int getSelection() {
        return selection;
    }
}
