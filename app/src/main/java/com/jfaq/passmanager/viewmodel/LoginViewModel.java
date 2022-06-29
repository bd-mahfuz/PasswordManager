package com.jfaq.passmanager.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.jfaq.passmanager.MainActivity;
import com.jfaq.passmanager.data.entities.User;
import com.jfaq.passmanager.data.repository.UserRepository;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.model.ActionModel;

import java.util.List;

public class LoginViewModel extends ViewModel {

    private static final String TAG = "LoginViewModel";


    public ObservableField<String> email = new ObservableField<>("");
    public ObservableField<String> pass = new ObservableField<>("");
    private Context context;
    private UserRepository userRepository;

    private MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();


    public LoginViewModel(Context context) {
        this.context = context;
        userRepository = UserRepository.getInstance(context);
    }

    public MutableLiveData<ActionModel> getActionLiveData() {
        return actionLiveData;
    }

    public void loginAction() {
        if (email != null && !email.get().equals("bt.mahfuz@gmail.com")) {
            actionLiveData.setValue(new ActionModel(Action.SHOW_ERROR, "Email is not correct"));
            return;
        }
        System.out.println("pass:"+ pass.get());
        if (pass != null && !pass.get().equals("1234")) {
            actionLiveData.setValue(new ActionModel(Action.SHOW_ERROR, "Password is not correct"));
            return;
        }

        actionLiveData.setValue(new ActionModel(Action.GO_TO_MAIN_ACTIVITY, null));

    }

    public void onEnableOffline() {
        Toast.makeText(context, "offline", Toast.LENGTH_SHORT).show();
    }

    public LiveData<User> showDefault() {
        return userRepository.getUserByEmail("bt.mahfuz@gmail.com");
    }

    public void test() {
        LiveData<User> userLiveData = userRepository.getUserByEmail("bt.mahfuz@gmail.com");
        userLiveData.observeForever( new Observer<User>() {
            @Override
            public void onChanged(User user) {
                Toast.makeText(context, "user name:"+ user.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public LiveData<List<User>> getAllUser() {
        return userRepository.getAllUser();
    }


}
