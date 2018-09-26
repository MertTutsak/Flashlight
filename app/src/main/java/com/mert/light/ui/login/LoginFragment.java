package com.mert.light.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mert.light.R;
import com.mert.light.ui.base.BaseFragment;
import com.mert.light.ui.signup.SignUpFragment;

public class LoginFragment extends BaseFragment {

    private EditText editTextUserName;
    private EditText editTextPassword;
    private Button buttonSignIn;
    private Button buttonSignUp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        buttonSignIn = view.findViewById(R.id.button_sign_in_login);
        buttonSignUp = view.findViewById(R.id.button_sign_up_login);
        editTextPassword = view.findViewById(R.id.editText_password_login);
        editTextUserName = view.findViewById(R.id.editText_username_login);

        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(baseActivity, "HOŞGELDİN " + "!", Toast.LENGTH_SHORT).show();
                //initFragment(new ..());
            }

        });

        buttonSignUp.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                replaceFragment(new SignUpFragment());
            }
        });

        return view;
    }

}
