package com.jfaq.passmanager.data.repository;

import android.content.Context;

import com.bumptech.glide.util.Util;
import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.VaultDao;
import com.jfaq.passmanager.data.entities.Vault;
import com.jfaq.passmanager.util.AppConstant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class VaultRepository {

    private VaultDao vaultDao;

    private VaultRepository(Context context) {
        this.vaultDao = AppDatabase.getAppDatabase(context).vaultDao();
    }


    public void insert(Vault vault) {
        ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.threadPoolCount);
        executorService.execute(() -> {
            vaultDao.insert(vault);
        });
    }

    public void update(Vault vault) {
        ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.threadPoolCount);
        executorService.execute(() -> {
            vaultDao.insert(vault);
        });
    }

    public void get(int vaultId) {
        ExecutorService executorService = Executors.newFixedThreadPool(AppConstant.threadPoolCount);

    }

}
