package com.jfaq.passmanager.data.repository;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.CredentialInfoDao;
import com.jfaq.passmanager.data.dao.UserDao;
import com.jfaq.passmanager.data.entities.CredentialInfo;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CredentialInfoRepository {

    private Context context;
    private static CredentialInfoRepository credentialInfoRepository;
    private CredentialInfoDao credentialInfoDao;


    private CredentialInfoRepository(Context context) {
        this.context = context;
        credentialInfoDao = AppDatabase.getAppDatabase(context).credentialInfoDao();
    }

    public static CredentialInfoRepository getInstance(Context context) {
        if (credentialInfoRepository == null) {
            return new CredentialInfoRepository(context);
        }
        return credentialInfoRepository;
    }

    public LiveData<List<CredentialInfo>> getAll() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(2);
        Future<LiveData<List<CredentialInfo>>> submit = executorService.submit(() -> {
            //List<CredentialInfo> credentialInfos = credentialInfoDao.getAllCredentialInfo();
            return credentialInfoDao.getAllCredentialInfo();
            //mutableLiveData.postValue(liveData.getValue());
        });
        return submit.get();
    }

    public void save(CredentialInfo credentialInfo) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(() -> {
            Log.d( "saving credential ", ".............");
            try {
                credentialInfoDao.save(credentialInfo);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }


}
