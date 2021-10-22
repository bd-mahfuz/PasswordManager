package com.jfaq.passmanager.viewmodel;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.CredentialCategoryDao;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.model.ActionModel;

import java.util.List;

public class AddCredentialViewModel extends ViewModel {
    private Context context;
    private CredentialCategoryRepository credentialCategoryRepository;

    public MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();

    public AddCredentialViewModel(Context context) {
        this.context = context;
        credentialCategoryRepository = CredentialCategoryRepository.getInstance(context);
    }

    public LiveData<List<CredentialCategory>> getAllCredentialCategory() {
        return credentialCategoryRepository.getAll();
    }

    public void onClickAddButton() {
        actionLiveData.setValue(new ActionModel(Action.SAVE_CREDENTIAL, null));
    }



}
