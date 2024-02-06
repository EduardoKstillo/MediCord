package com.unsapp.medicord.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import com.unsapp.medicord.R;
import com.unsapp.medicord.data.database.AppDatabase;
import com.unsapp.medicord.data.models.User;
import com.unsapp.medicord.data.viewmodels.UserViewModel;

public class LoginFragment extends Fragment {

    private EditText etEmail, etPassword;
    private AppDatabase appDatabase;
    private OnRegisterClickListener registerClickListener;
    private OnLoginSuccessListener loginSuccessListener;
    private UserViewModel userViewModel;


    // Interface to handle clicks on the registration button
    public interface OnRegisterClickListener { void onRegisterClick(); }

    // interface to handle successful logins
    public interface OnLoginSuccessListener { void onLoginSuccess(); }

    public LoginFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Convert fragment_login to view object
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        // attributes instance
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        // room instance
        appDatabase = Room.databaseBuilder(getContext(),
                AppDatabase.class, "medicord_db").allowMainThreadQueries().build();

        // login button reference and event click
        Button btnLogin = view.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // function to login
                loginUser();
            }
        });

        // register button reference and click event
        Button btnSingUp = view.findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // notify the MainActivity that "Register User" was clicked
                if (registerClickListener != null) {
                    registerClickListener.onRegisterClick();
                }
            }
        });

        return view;
    }

    private void loginUser(){

        //get attributes and convert them to string
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // sim os campos tão empty
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(getContext(), "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // userDao -> findByEmailAndPassword
        User user = appDatabase.userDao().findByEmailAndPassword(email, password);

        // user not found
        if (user == null){
            Toast.makeText(getContext(), "Incorrect credentials", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(getContext(), "Successful login", Toast.LENGTH_SHORT).show();

        // more functionalities
        if (loginSuccessListener != null) {
            userViewModel.setUser(user);
            loginSuccessListener.onLoginSuccess();
        }

    }

    // set registerClickListener
    public void setOnRegisterClickListener(OnRegisterClickListener listener) {
        this.registerClickListener = listener;
    }

    // set loginSuccessListener
    public void setOnLoginSuccessListener(OnLoginSuccessListener listener) {
        this.loginSuccessListener = listener;
    }

    public void setUserViewModel(UserViewModel userViewModel) {
        this.userViewModel = userViewModel;
    }
}
