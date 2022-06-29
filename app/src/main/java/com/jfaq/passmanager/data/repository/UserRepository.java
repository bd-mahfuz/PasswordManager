package com.jfaq.passmanager.data.repository;

import android.content.Context;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.UserDao;
import com.jfaq.passmanager.data.entities.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private UserDao userDao;
    private static UserRepository userRepository;

    private UserRepository(Context context) {
        this.userDao = AppDatabase.getAppDatabase(context).userDao();
    }

    public static UserRepository getInstance(Context context) {
        if (userRepository == null) {
            return new UserRepository(context);
        }
        return userRepository;
    }

    public void addUser(User user) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(()->{
            Log.d( "addUser: ", "creating user:"+ user.getName());
            userDao.insert(user);
        });
    }

    public LiveData<User> getUserByEmail(String email) {
        MutableLiveData<User> userLiveData = new MutableLiveData<>();
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.submit(() -> {
            Log.d("find by email: ", email);
            userLiveData.postValue(userDao.findByEmail(email));
        });
        return userLiveData;
    }

    public LiveData<List<User>> getAllUser() {
        MutableLiveData<List<User>> userLiveData = new MutableLiveData<>();
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        executorService.submit(() -> {
            userLiveData.postValue(userDao.getAll());
        });
        executorService.shutdown();
        return userLiveData;
    }
}
