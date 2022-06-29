package com.jfaq.passmanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jfaq.passmanager.adapter.CredentialAdapter;
import com.jfaq.passmanager.data.repository.CredentialCategoryRepository;
import com.jfaq.passmanager.data.repository.CredentialInfoRepository;
import com.jfaq.passmanager.databinding.ActivityMainBinding;
import com.jfaq.passmanager.viewModelFactory.CustomViewModelFactory;
import com.jfaq.passmanager.viewmodel.MainViewModel;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private MainViewModel mainViewModel;
    private ActivityMainBinding activityMainBinding;

    private BottomNavigationView bottomNavigationView;
    private View searchBar;
    private NestedScrollView nestedScrollView;
    private RecyclerView credentialRv;
    private TextView noDataTv;
    private CredentialAdapter credentialAdapter;
/*
    private CredentialCategoryRepository credentialCategoryRepository;
    private CredentialInfoRepository credentialInfoRepository;*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, new CustomViewModelFactory(getApplication())).get(MainViewModel.class);

       /* credentialCategoryRepository = CredentialCategoryRepository.getInstance(this);
        credentialInfoRepository = CredentialInfoRepository.getInstance(this);
        mainViewModel.init(credentialCategoryRepository, credentialInfoRepository);*/

        activityMainBinding.setMainViewModel(mainViewModel);
        bottomNavigationView = activityMainBinding.navigation;
        searchBar = activityMainBinding.searchBar;
        nestedScrollView = activityMainBinding.nestedScrollView;
        credentialRv = activityMainBinding.credentialRv;
        credentialRv.setLayoutManager(new LinearLayoutManager(this));
        credentialRv.setHasFixedSize(true);
        noDataTv = activityMainBinding.noDataTv;


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Toast.makeText(MainActivity.this, "sajhfihe", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY < oldScrollY) { // up
                    animateNavigation(false);
                    animateSearchBar(false);
                }
                if (scrollY > oldScrollY) { // down
                    animateNavigation(true);
                    animateSearchBar(true);
                }
            }
        });


        try {
            mainViewModel.getAllCredentialInfos()
                    .observe(this, credentialInfos -> {
                        System.out.println("ajfiehif aifjae  --------------"+ credentialInfos.size());
                        if (credentialInfos.size() > 0) {
                            credentialAdapter = new CredentialAdapter(MainActivity.this, credentialInfos);
                            credentialRv.setAdapter(credentialAdapter);
                            credentialAdapter.notifyDataSetChanged();
                        } else {
                            noDataTv.setVisibility(View.VISIBLE);
                        }

                    });
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        mainViewModel.getActionLiveData().observe(this, actionModel -> {
            switch (actionModel.getAction()) {
                case GOTO_ADD_CREDENTIAL_ACTIVITY:
                    Intent intent = new Intent(MainActivity.this, AddCredentialActivity.class);
                    startActivity(intent);
                    break;
            }
        });


    }

    boolean isNavigationHide = false;
    private void animateNavigation(final boolean hide) {
        if (isNavigationHide && hide || !isNavigationHide && !hide) return;
        isNavigationHide = hide;
        int moveY = hide ? (2 * bottomNavigationView.getHeight()) : 0;
        bottomNavigationView.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    boolean isSearchBarHide = false;
    private void animateSearchBar(final boolean hide) {
        if (isSearchBarHide && hide || !isSearchBarHide && !hide) return;
        isSearchBarHide = hide;
        int moveY = hide ? -(2 * searchBar.getHeight()) : 0;
        searchBar.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

}