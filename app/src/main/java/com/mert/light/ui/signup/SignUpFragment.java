package com.mert.light.ui.signup;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mert.light.R;
import com.mert.light.ui.base.BaseFragment;

public class SignUpFragment extends BaseFragment {

    private EditText editTextUserName;
    private EditText editTextName;
    private EditText editTextSurname;
    private EditText editTextPassword;
    private EditText editTextPasswordAgain;

    private Button buttonSignUp;

    private EditText[] editTexts;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        editTextUserName = view.findViewById(R.id.editText_username_signup);
        editTextName = view.findViewById(R.id.editText_name_signup);
        editTextSurname = view.findViewById(R.id.editText_surname_signup);
        editTextPassword = view.findViewById(R.id.editText_password_signup);
        editTextPasswordAgain = view.findViewById(R.id.editText_password_again_signup);
        buttonSignUp = view.findViewById(R.id.button_sign_up_signup);

        editTexts = new EditText[]{
                editTextUserName,
                editTextName,
                editTextSurname,
                editTextPassword,
                editTextPasswordAgain};

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmptyField()) {
                    Toast.makeText(baseActivity, "KAYIT OLDUNUZ !", Toast.LENGTH_SHORT).show();
                    backPressButton();
                }
            }
        });

        return view;
    }

    private boolean isEmptyField() {
        for (int i = 0; i < editTexts.length; i++) {
            if (editTexts[i].getText().equals("")) {
                return true;
            }
        }
        return false;
    }

}
