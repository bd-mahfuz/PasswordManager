package com.jfaq.passmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.jfaq.passmanager.adapter.CredentialArrayAdapter;
import com.jfaq.passmanager.data.entities.CredentialCategory;
import com.jfaq.passmanager.databinding.ActivityAddCredentialBinding;
import com.jfaq.passmanager.viewModelFactory.CustomViewModelFactory;
import com.jfaq.passmanager.viewmodel.AddCredentialViewModel;

import java.util.Objects;

public class AddCredentialActivity extends AppCompatActivity {

    private static final String TAG = AddCredentialActivity.class.getName();

    private ActivityAddCredentialBinding binding;
    private Toolbar toolbar;
    private AddCredentialViewModel addCredentialViewModel;
    private AppCompatSpinner categorySpinner;
    private AppCompatSpinner hintSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_credential);
        addCredentialViewModel = new ViewModelProvider(this, new CustomViewModelFactory(this)).get(AddCredentialViewModel.class);
        binding.setAddCredentialViewModel(addCredentialViewModel);
        initViews();

        String[] stringArray = getResources().getStringArray(R.array.password_hint);
        ArrayAdapter<String> hintAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, stringArray);
        hintSpinner.setAdapter(hintAdapter);

        // getting credential category data
        addCredentialViewModel.getAllCredentialCategory()
                .observe(this, credentialCategories -> {
                    Log.i(TAG, "onCreate: "+ credentialCategories.size());
                    CredentialArrayAdapter credentialArrayAdapter = new CredentialArrayAdapter(AddCredentialActivity.this, R.layout.spinner_item, credentialCategories);
                    credentialArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    categorySpinner.setAdapter(credentialArrayAdapter);
                });


        addCredentialViewModel.actionLiveData.observe(this, actionModel -> {
            switch (actionModel.getAction()) {
                case SAVE_CREDENTIAL:
                    validateFields();
                    break;
            }
        });

    }

    private boolean validateFields() {


        return false;
    }

    private void initViews() {
        categorySpinner = binding.categorySpinner;
        hintSpinner = binding.hintSpinner;
        toolbar = binding.toolbarL.toolbar;
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Add New Credential info");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    protected void onStart() {
        super.onStart();

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CredentialCategory credentialCategory = (CredentialCategory) parent.getItemAtPosition(position);
                Toast.makeText(AddCredentialActivity.this, "fajei:"+ credentialCategory.getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}