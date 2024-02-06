package com.unsapp.medicord.ui.fragments;

import android.os.AsyncTask;
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
import com.unsapp.medicord.data.dao.UserDao;
import com.unsapp.medicord.data.database.AppDatabase;
import com.unsapp.medicord.data.models.User;

public class RegisterFragment extends Fragment {

    private EditText etName, etEmail, etPassword;
    private OnRegisterSuccessListener registerSuccessListener;

    private AppDatabase appDatabase;

    public RegisterFragment() {}

    // Interface to handle successful registration
    public interface OnRegisterSuccessListener { void onRegisterSuccess(); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Convert fragment_register to view object
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        // attributes instance
        etName = view.findViewById(R.id.etName);
        etEmail = view.findViewById(R.id.etEmail);
        etPassword = view.findViewById(R.id.etPassword);

        // room instance
        appDatabase = Room.databaseBuilder(requireContext(),
                AppDatabase.class, "medicord_db").allowMainThreadQueries().build();
        //appDatabase = Room.databaseBuilder(getContext(), AppDatabase.class, "medicord_db").allowMainThreadQueries().build();

        // register button reference and event click
        Button btnSingUp = view.findViewById(R.id.btnSingUp);
        btnSingUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Function to register user
                registerUser();
            }
        });

        return view;
    }

    private void registerUser() {
        // get attributes and convert them to string
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();

        // sim os campos tão empty
        if (name.isEmpty() || email.isEmpty() || password.isEmpty()){
            Toast.makeText(requireContext(), "Complete all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // check if the user already exists
        if (userAlready(email)){
            Toast.makeText(requireContext(), "The user is already registered", Toast.LENGTH_SHORT).show();
            return;
        }

        // register new user
        User user = new User();
        user.username = name;
        user.email = email;
        user.password = password;

        UserDao userDao = appDatabase.userDao();
        userDao.insertUser(user);

        Toast.makeText(requireContext(),"Successfully registered user", Toast.LENGTH_SHORT).show();

        // more functionalities
        System.out.println(registerSuccessListener);
        if (registerSuccessListener != null){
            registerSuccessListener.onRegisterSuccess();
        }
    }

    private boolean userAlready(String email){
        return appDatabase.userDao().findByEmail(email);
    }

    // Method to set the OnRegisterSuccessListener
    public void setOnRegisterSuccessListener(OnRegisterSuccessListener listener) {
        this.registerSuccessListener = listener;
    }
}