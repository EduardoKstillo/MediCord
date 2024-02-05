package com.unsapp.medicord.ui;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.dao.UserDao;
import com.unsapp.medicord.data.database.AppDatabase;
import com.unsapp.medicord.data.models.User;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnRegisterClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LoginFragment instance
        LoginFragment loginFragment = new LoginFragment();
        loginFragment.setOnRegisterClickListener(this);

        // load fragment -> LoginFragment
        loadFragment(loginFragment);
    }

    // Function to load new fragment to container fragment
    private void loadFragment(Fragment fragment) {
        // Replaces the contents of the container with the new fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    // Implemented method of OnRegisterClickListener interface in login fragment
    @Override
    public void onRegisterClick() {
        // When "Register user" is clicked from the login fragment
        // Replace the current fragment with register fragment
        loadFragment(new RegisterFragment());
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