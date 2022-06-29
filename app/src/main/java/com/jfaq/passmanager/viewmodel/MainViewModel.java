package com.jfaq.passmanager.viewmodel;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
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
import java.util.concurrent.ExecutionException;

public class MainViewModel extends ViewModel {

    private static String TAG = MainViewModel.class.toString();
    private final Application context;

    private final CredentialCategoryRepository credentialCategoryRepository;
    private final CredentialInfoRepository credentialInfoRepository;
    private final MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();

    /*public MainViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        credentialCategoryRepository = CredentialCategoryRepository.getInstance(context);
        credentialInfoRepository = CredentialInfoRepository.getInstance(context);
    }*/

    public MainViewModel(Application context) {
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

    public LiveData<List<CredentialInfo>> getAllCredentialInfos() throws ExecutionException, InterruptedException {
        Log.d( "getAllCredentialInfos: ", "c");
        return credentialInfoRepository.getAll();
    }

    public void onClickAddCredentialButton() {
        actionLiveData.setValue(new ActionModel(Action.GOTO_ADD_CREDENTIAL_ACTIVITY, null));
    }


   /* public void init(CredentialCategoryRepository credentialCategoryRepository,
                     CredentialInfoRepository credentialInfoRepository) {
        this.credentialCategoryRepository = credentialCategoryRepository;
        this.credentialInfoRepository = credentialInfoRepository;
    }
*/

}
