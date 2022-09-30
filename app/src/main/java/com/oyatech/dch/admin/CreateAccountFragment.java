package com.oyatech.dch.admin;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.oyatech.dch.databinding.FragmentCreateAccountBinding;
import java.util.Objects;

public class CreateAccountFragment extends Fragment {
    private FirebaseAuth auth;
    private String TAG = CreateAccountFragment.class.getSimpleName();
    private FragmentCreateAccountBinding binding;

    public CreateAccountFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCreateAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void signUp(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                Toast.makeText(requireContext(), "Staff Created", Toast.LENGTH_SHORT).show();
                    //Get the current user details
                FirebaseUser user = auth.getCurrentUser();
                Log.i(TAG, "signUp: " + user.toString());

            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void newAccount() {

        String email = Objects.requireNonNull(binding.staffEmail.getText()).toString().trim();
        String password = Objects.requireNonNull(binding.password.getText()).toString().trim();
        String repeat_password = Objects.requireNonNull(binding.repeatPassword.getText()).toString().trim();
    }
}