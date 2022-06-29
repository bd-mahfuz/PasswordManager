package com.jfaq.passmanager.viewmodel;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputEditText;
import com.jfaq.passmanager.data.AppDatabase;
import com.jfaq.passmanager.data.dao.CredentialCategoryDao;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.data.repository.CredentialInfoRepository;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.model.ActionModel;
import com.jfaq.passmanager.model.AddCredentialModel;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AddCredentialViewModel extends ViewModel {

    private static final String TAG = AddCredentialViewModel.class.getName();
    private final Context context;

    private CredentialCategoryRepository credentialCategoryRepository;
    private CredentialInfoRepository credentialInfoRepository;

    public AddCredentialModel addCredentialModel = new AddCredentialModel();
    public MutableLiveData<ActionModel> actionLiveData = new MutableLiveData<>();

    //private View.OnFocusChangeListener onFocusTitle;
    private AdapterView.OnItemClickListener onItemClickListener;


    /*public AddCredentialViewModel(@NonNull Application application) {
        super(application);
        this.context = application;
        credentialCategoryRepository = CredentialCategoryRepository.getInstance(context);
        credentialInfoRepository = CredentialInfoRepository.getInstance(context);
    }*/

    public AddCredentialViewModel(Application context) {
        this.context = context;
        credentialCategoryRepository = CredentialCategoryRepository.getInstance(context);
        credentialInfoRepository = CredentialInfoRepository.getInstance(context);
    }

    public void init() {


        onItemClickListener = (parent, view, position, id) -> {
            CredentialCategory credentialCategory = (CredentialCategory) parent.getItemAtPosition(position);
            addCredentialModel.setCategoryId(credentialCategory.getId());
            Toast.makeText(view.getContext(), "feij"+ credentialCategory.getTitle(), Toast.LENGTH_SHORT).show();
        };

    }

    public LiveData<List<CredentialCategory>> getAllCredentialCategory() {
        return credentialCategoryRepository.getAll();
    }

    public void saveCredentialInfo(CredentialInfo credentialInfo) {
        credentialInfoRepository.save(credentialInfo);
    }

    public void onClickAddButton() {
        Log.i(TAG, "onClickAddButton: "+ addCredentialModel);
        actionLiveData.setValue(new ActionModel(Action.SAVE_CREDENTIAL, null));
    }



    /*@BindingAdapter(value = {"android:categoryListener"}, requireAll = false)
    public static void categoryListener(AutoCompleteTextView view, int catName) {
        view.setOnItemClickListener(view.getOnItemClickListener());
    }*/

    @BindingAdapter(value = {"errorMsg", "textValidator", "minLength"}, requireAll = false)
    public static void validateField(TextInputEditText textInputEditText, Object strOrResId, String text, int minLength) {
        if (TextUtils.isEmpty(text)) {
            textInputEditText.setError(null);
            return;
        }
        if (textInputEditText.getText().toString().length() < minLength) {
            if (strOrResId instanceof Integer) {
                textInputEditText.setError(
                        textInputEditText.getContext()
                                .getString((Integer) strOrResId));
            } else {
                textInputEditText.setError((String) strOrResId);
            }
        } else textInputEditText.setError(null);
    }

    @BindingAdapter(value = {"onItemClickListener", "selectedText"}, requireAll = false)
    public static void bindFocusChange(AutoCompleteTextView view, AdapterView.OnItemClickListener onItemClickListener, int selectedText) {
        System.out.println("sljfi"+ selectedText+": "+ view.getText());
        if (selectedText == 0) {
            view.setError("error");
        } else view.setError(null);
        if (view.getOnItemClickListener() == null) {
            view.setOnItemClickListener(onItemClickListener);
        }
    }

    public AdapterView.OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void initRepo(CredentialCategoryRepository credentialCategoryRepository,
                     CredentialInfoRepository credentialInfoRepository) {
        this.credentialCategoryRepository = credentialCategoryRepository;
        this.credentialInfoRepository = credentialInfoRepository;
    }
}
