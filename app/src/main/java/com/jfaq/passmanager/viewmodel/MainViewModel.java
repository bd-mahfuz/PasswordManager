package com.jfaq.passmanager.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.data.repository.CredentialInfoRepository;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.model.ActionModel;

import java.util.List;

public class MainViewModel extends ViewModel {

    private static String TAG = MainViewModel.class.toString();

    private Context context;
    private CredentialCategoryRepository credentialCategoryRepository;
    private CredentialInfoRepository credentialInfoRepository;
    private MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();

    public MainViewModel(Context context) {
        this.context = context;
        credentialCategoryRepository = CredentialCategoryRepository.getInstance(context);
        credentialInfoRepository = CredentialInfoRepository.getInstance(context);
    }

    public MutableLiveData<ActionModel> getActionLiveData() {
        return actionLiveData;
    }

    public LiveData<List<CredentialCategory>> getAllCredentialCategory() {
        return credentialCategoryRepository.getAll();
    }

    public MutableLiveData<List<CredentialInfo>> getAllCredentialInfos() {
        Log.d( "getAllCredentialInfos: ", "c");
        return credentialInfoRepository.getAll();
    }

    public void onClickAddCredentialButton() {
        actionLiveData.setValue(new ActionModel(Action.GOTO_ADD_CREDENTIAL_ACTIVITY, null));
    }



}
