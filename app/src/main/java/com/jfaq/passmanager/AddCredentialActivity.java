package com.jfaq.passmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.jfaq.passmanager.adapter.CredentialAdapter;
import com.jfaq.passmanager.adapter.CredentialArrayAdapter;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.data.entities.CredentialInfo;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.data.repository.CredentialInfoRepository;
import com.jfaq.passmanager.databinding.ActivityAddCredentialBinding;
import com.jfaq.passmanager.model.AddCredentialModel;
import com.jfaq.passmanager.viewModelFactory.CustomViewModelFactory;
import com.jfaq.passmanager.viewmodel.AddCredentialViewModel;
import com.jfaq.passmanager.viewmodel.MainViewModel;

import java.util.Objects;
import java.util.concurrent.ExecutionException;

public class AddCredentialActivity extends AppCompatActivity {

    private static final String TAG = AddCredentialActivity.class.getName();

    private ActivityAddCredentialBinding binding;
    private Toolbar toolbar;
    private AddCredentialViewModel addCredentialViewModel;
    private AutoCompleteTextView categorySpinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_credential);
        addCredentialViewModel = new ViewModelProvider(this, new CustomViewModelFactory(getApplication())).get(AddCredentialViewModel.class);


        addCredentialViewModel.init();
        binding.setAddCredentialViewModel(addCredentialViewModel);
        initViews();

        // getting credential category data
        addCredentialViewModel.getAllCredentialCategory()
                .observe(this, credentialCategories -> {
                    Log.i(TAG, "onCreate: "+ credentialCategories.size());
                    CredentialArrayAdapter credentialArrayAdapter = new CredentialArrayAdapter(AddCredentialActivity.this, R.layout.spinner_item, credentialCategories);
                    //credentialArrayAdapter.setDropDownViewResource(R.layout.spinner_item);
                    categorySpinner.setAdapter(credentialArrayAdapter);
                });

        addCredentialViewModel.actionLiveData.observe(this, actionModel -> {
            switch (actionModel.getAction()) {
                case SAVE_CREDENTIAL:
                    AddCredentialModel addCredentialModel = addCredentialViewModel.addCredentialModel;
                    saveCredential(addCredentialModel);
                    finish();
                    break;
            }
        });



    }

    private void saveCredential(AddCredentialModel addCredentialModel) {
        CredentialInfo credentialInfo = new CredentialInfo();
        credentialInfo.setTitle(addCredentialModel.getTitle());
        credentialInfo.setImageUrl(addCredentialModel.getImageUrl());
        credentialInfo.setEmail(addCredentialModel.getEmail());
        credentialInfo.setHint(addCredentialModel.getPasswordHint());
        credentialInfo.setPassword(addCredentialModel.getPassword());
        credentialInfo.setUrl(addCredentialModel.getWebSiteUrl());
        credentialInfo.setUserName(addCredentialModel.getUserName());
        credentialInfo.setCredentialCategoryId(addCredentialModel.getCategoryId());
        addCredentialViewModel.saveCredentialInfo(credentialInfo);
    }

    private void initViews() {
        categorySpinner = binding.categorySpinner;
        toolbar = binding.toolbarL.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Add New Credential info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*categorySpinner.setOnItemClickListener((parent, view, position, id) -> {
            CredentialCategory credentialCategory = (CredentialCategory) parent.getItemAtPosition(position);

            //categorySpinner.setText(credentialCategory.getTitle());
            Toast.makeText(AddCredentialActivity.this, "fajei:"+ credentialCategory.getTitle(), Toast.LENGTH_SHORT).show();
        });*/
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }



}