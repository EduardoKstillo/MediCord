package com.unsapp.medicord.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.navigation.NavigationBarView;
import com.unsapp.medicord.R;
import com.unsapp.medicord.data.models.User;
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

     private UserViewModel userViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater()); //--
        setContentView(binding.getRoot()); //--

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        // LoginFragment instance
        LoginFragment loginFragment = new LoginFragment();

        loginFragment.setUserViewModel(userViewModel);

        loginFragment.setOnRegisterClickListener(this);
        loginFragment.setOnLoginSuccessListener(this);
        // load fragment -> LoginFragment
        loadFragment(loginFragment);

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
    }

    @Override
    public void onLoginSuccess() {
        // when login is successful
        loadFragment(new HomeFragment());
    }

    @Override
    public void onRegisterSuccess() {
        // when user registration is successful
        loadFragment(new HomeFragment());
    }
}



/*
public class MainActivity extends AppCompatActivity {

    private EditText etName, etEmail, etPassword;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);

        appDatabase = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "medicord_db").allowMainThreadQueries().build();


        //appDatabase = AppDatabase.getInstance(this);


        // button to register
        Button btnSingUp = findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // function for register to user
                registerUser();
            }
        });

        Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // function to login
                loginUser();
            }
        });
    }

    // function for register to user
    private void registerUser(){

        // get attributes and convert them to string
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // sim os campos tão empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // check if the user already exists
        if (userAlready(email)){
            Toast.makeText(this, "The user is already registered", Toast.LENGTH_SHORT).show();
            return;
        }

        // register new user
        User user = new User();
        user.username = name;
        user.email = email;
        user.password = password;

        UserDao userDao = appDatabase.userDao();
        userDao.insertUser(user);

        Toast.makeText(this,"Successfully registered user", Toast.LENGTH_SHORT).show();

        // more functionalities

    }

    private boolean userAlready(String email){
        return appDatabase.userDao().findByEmail(email);
    }

    private void loginUser(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // sim os campos tão empty
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        User user = appDatabase.userDao().findByEmailAndPassword(email, password);

        if (user == null){
            Toast.makeText(this, "Incorrect credentials", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(this, "Successful login", Toast.LENGTH_SHORT).show();
        // more functionalities

    }
}
*/