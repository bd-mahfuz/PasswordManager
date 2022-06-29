package com.jfaq.passmanager.viewModelFactory;

import android.app.Application;
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

    private final Application application;

    public CustomViewModelFactory(Application context) {
        this.application = context;
    }

    @SneakyThrows
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        try {
            return (T) modelClass.getConstructor(Application.class).newInstance(application);
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
