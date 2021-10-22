package com.jfaq.passmanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.internal.NavigationMenu;
import com.jfaq.passmanager.adapter.CredentialAdapter;
import com.jfaq.passmanager.databinding.ActivityMainBinding;
import com.jfaq.passmanager.enums.Action;
import com.jfaq.passmanager.model.ActionModel;
import com.jfaq.passmanager.viewModelFactory.CustomViewModelFactory;
import com.jfaq.passmanager.viewmodel.MainViewModel;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new ViewModelProvider(this, new CustomViewModelFactory(this)).get(MainViewModel.class);
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


        mainViewModel.getAllCredentialInfos()
                .observe(this, credentialInfos -> {
                    System.out.println("ajfiehif aifjae  --------------");
                    if (credentialInfos.size() > 0) {
                        credentialAdapter = new CredentialAdapter(MainActivity.this, credentialInfos);
                        credentialAdapter.notifyDataSetChanged();
                    } else {
                        noDataTv.setVisibility(View.VISIBLE);
                    }

                });

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