package com.jfaq.passmanager.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.model.ActionModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private Context context;
    private CredentialCategoryRepository credentialCategoryRepository;
    private MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();

    public MainViewModel(Context context) {
        this.context = context;
    }

    public MutableLiveData<ActionModel> getActionLiveData() {
        return actionLiveData;
    }

    public LiveData<List<CredentialCategory>> getAllCredentialCategory() {
        return credentialCategoryRepository.getAll();
    }





}
