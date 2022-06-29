package com.jfaq.passmanager.data.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.CredentialCategoryDao;
import com.jfaq.passmanager.data.entities.CredentialCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CredentialCategoryRepository {

    private Context context;
    private static CredentialCategoryRepository credentialCategoryRepository;
    private CredentialCategoryDao credentialCategoryDao;

    private CredentialCategoryRepository(Context context) {
        this.context = context;
        this.credentialCategoryDao = AppDatabase.getAppDatabase(context).credentialCategoryDao();
    }

    public static CredentialCategoryRepository getInstance(Context context) {
        if (credentialCategoryRepository == null) {
            return new CredentialCategoryRepository(context);
        }
        return credentialCategoryRepository;
    }

    public LiveData<Boolean> save(CredentialCategory credentialCategory) {
        MutableLiveData<Boolean> isSaved = new MutableLiveData<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            try {
                credentialCategoryDao.save(credentialCategory);
                isSaved.postValue(true);
            } catch (Exception e) {
                isSaved.postValue(false);
                e.printStackTrace();
            }
        });
        executorService.shutdown();
        return isSaved;
    }

    public MutableLiveData<CredentialCategory> getById(int id) {
        MutableLiveData<CredentialCategory> categoryMutableLiveData = new MutableLiveData<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            categoryMutableLiveData.postValue(credentialCategoryDao.getById(id));
        });
        return categoryMutableLiveData;
    }

    public LiveData<List<CredentialCategory>> getAll() {
        MutableLiveData<List<CredentialCategory>> categoryMutableLiveData = new MutableLiveData<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            categoryMutableLiveData.postValue(credentialCategoryDao.getAllCredentialCategory());
        });
        return categoryMutableLiveData;
    }


}
