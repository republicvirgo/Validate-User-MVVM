package com.visualteknologi.validateusermvvm.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.visualteknologi.validateusermvvm.R;
import com.visualteknologi.validateusermvvm.model.User;

import java.util.Objects;
import java.util.regex.Pattern;

public class MainFragment extends Fragment {

    private MainViewModel mViewModel;

    EditText etEmail, etPassword;
    Button btnInput;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // TODO: Use the ViewModel
        btnInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String stringEmail, stringPassword;
                stringEmail = etEmail.getText().toString();
                stringPassword = etPassword.getText().toString();

                //TODO: Validasi
                if(TextUtils.isEmpty(stringEmail)){
                    etEmail.setError("Email tidak boleh kosong");
                    etEmail.requestFocus();
                } else if(!Patterns.EMAIL_ADDRESS.matcher(stringEmail).matches()){
                    etEmail.setError("Format Email tidak benar");
                    etEmail.requestFocus();
                } else if(TextUtils.isEmpty(stringPassword)){
                    etPassword.setError("Password tidak boleh kosong");
                    etPassword.requestFocus();
                } else if(etPassword.length() < 6) {
                    etPassword.setError("Password tidak boleh kurang dari 6 Karakter");
                    etPassword.requestFocus();
                } else {
                    mViewModel.inputUser().observe(Objects.requireNonNull(getActivity()), new Observer<User>() {
                        @Override
                        public void onChanged(@Nullable User user) {
                            Toast.makeText(getContext(), user.getLoginBerhasil(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);
        btnInput = view.findViewById(R.id.btnInput);
    }
}
