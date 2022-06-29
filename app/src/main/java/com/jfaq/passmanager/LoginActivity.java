package com.jfaq.passmanager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.jfaq.passmanager.databinding.ActivityLoginBinding;
import com.jfaq.passmanager.data.entities.User;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.viewmodel.LoginViewModel;
import com.jfaq.passmanager.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel mLoginViewModel;
    private ActivityLoginBinding mLoginBinding;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        mLoginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        mLoginBinding.setLoginViewModel(mLoginViewModel);
        mLoginViewModel.getActionLiveData().observe(this, (actionModel) -> {
            switch (actionModel.getAction()) {
                case SHOW_ERROR:
                    Toast.makeText(LoginActivity.this, actionModel.getMessage(), Toast.LENGTH_SHORT).show();
                    break;
                case GO_TO_MAIN_ACTIVITY:
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    break;
            }
        });


    }
}