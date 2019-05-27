package com.example.mobdevtask.task1;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.example.mobdevtask.R;
import com.example.mobdevtask.task1.model.User;
import com.example.mobdevtask.task1.viewmodel.UserViewModel;

public class Tab1Fragment extends Fragment {

    private EditText editTextName, editTextPassword;
    private Button btn;

    private UserViewModel userViewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_1, container, false);

        editTextName = view.findViewById(R.id.name);
        editTextPassword = view.findViewById(R.id.password);
        btn = view.findViewById(R.id.button);
        btn.setOnClickListener(listener);


        return view;

    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            getInput();
            clearTextField();


        }
    };



    private void getInput() {
        String name = editTextName.getText().toString();
        String pass = editTextPassword.getText().toString();

        if(name.trim().isEmpty() || pass.trim().isEmpty()) {
            Toast.makeText(getActivity(), "Fill all views", Toast.LENGTH_LONG).show();
        }else {
            User user = new User(name, pass);
            userViewModel.insert(user);
        }
    }


    private void clearTextField() {

        editTextName.setText("");
        editTextPassword.setText("");


    }


}
