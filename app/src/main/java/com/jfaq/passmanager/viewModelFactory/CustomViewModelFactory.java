package com.jfaq.passmanager.viewModelFactory;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.jfaq.passmanager.LoginActivity;
import com.jfaq.passmanager.data.entities.User;
import com.jfaq.passmanager.viewmodel.LoginViewModel;

import java.lang.reflect.InvocationTargetException;

import lombok.SneakyThrows;


public class CustomViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private Context context;

    public CustomViewModelFactory(Context context) {
        this.context = context;
    }

    @SneakyThrows
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) modelClass.getConstructor(Context.class).newInstance(context);
        /*if (context instanceof LoginActivity) {
            return (T) new LoginViewModel(context);
        }
        return null;*/
    }
}
