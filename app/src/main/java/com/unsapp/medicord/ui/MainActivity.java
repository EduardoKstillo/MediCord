package com.unsapp.medicord.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.unsapp.medicord.R;
import com.unsapp.medicord.data.viewmodels.UserViewModel;
import com.unsapp.medicord.databinding.ActivityMainBinding;
import com.unsapp.medicord.ui.fragments.HomeFragment;
import com.unsapp.medicord.ui.fragments.LoginFragment;
import com.unsapp.medicord.ui.fragments.MedicineFragment;
import com.unsapp.medicord.ui.fragments.RegisterFragment;
import com.unsapp.medicord.ui.fragments.ReportFragment;

public class MainActivity extends AppCompatActivity implements
        LoginFragment.OnRegisterClickListener,
        LoginFragment.OnLoginSuccessListener,
        RegisterFragment.OnRegisterSuccessListener{

     ActivityMainBinding binding;
     private BottomNavigationView bottomNavigationView;

     private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //--
        setContentView(binding.getRoot()); //--

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        bottomNavigationView = binding.bottomNavigation;

        // LoginFragment instance
        LoginFragment loginFragment = new LoginFragment();

        loginFragment.setUserViewModel(userViewModel);

        loginFragment.setOnRegisterClickListener(this);
        loginFragment.setOnLoginSuccessListener(this);
        // load fragment -> LoginFragment
        loadFragment(loginFragment);
        hideBottomNavigationView();


        // navigation
        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int menuItemId = item.getItemId();

                if (menuItemId == R.id.action_home){
                    loadFragment(new HomeFragment());
                    return true;
                }
                else if (menuItemId == R.id.action_add_medicine) {
                    loadFragment(new MedicineFragment());
                    return true;
                }
                else if (menuItemId == R.id.action_report) {
                    loadFragment(new ReportFragment());
                    return true;
                }

                return false;
            }
        });

    }

    // Function to load new fragment to container fragment
    private void loadFragment(Fragment fragment) {
        // Replaces the contents of the container with the new fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
    }

    // Implemented method of OnRegisterClickListener interface in login fragment
    @Override
    public void onRegisterClick() {
        // When "Register user" is clicked from the login fragment
        // Replace the current fragment with register fragment
        RegisterFragment registerFragment = new RegisterFragment();
        registerFragment.setOnRegisterSuccessListener(this);
        loadFragment(registerFragment);
        hideBottomNavigationView();
    }

    @Override
    public void onLoginSuccess() {
        // when login is successful
        loadFragment(new HomeFragment());
        showBottomNavigationView();
    }

    @Override
    public void onRegisterSuccess() {
        // when user registration is successful
        loadFragment(new HomeFragment());
        showBottomNavigationView();
    }

    private void hideBottomNavigationView() {
        bottomNavigationView.setVisibility(View.GONE);
    }

    private void showBottomNavigationView() {
        bottomNavigationView.setVisibility(View.VISIBLE);
    }
}

