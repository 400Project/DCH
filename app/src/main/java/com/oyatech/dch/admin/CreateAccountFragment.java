package com.oyatech.dch.admin;

import static com.oyatech.dch.alerts.ProduceSnackbarKt.isEmptyView;
import static com.oyatech.dch.alerts.ProduceSnackbarKt.snackForError;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.oyatech.dch.databinding.FragmentCreateAccountBinding;

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.signUp.setOnClickListener(view1 -> {
            String email = binding.staffEmail.getText().toString().trim();
            String password = binding.password.getText().toString().trim();
            String repeat_password = binding.repeatPassword.getText().toString().trim();
            if (isEmptyView(binding.staffEmail) ||
                    isEmptyView(binding.password) ||
                    isEmptyView(binding.repeatPassword)) {
                snackForError(requireContext(), view, "Enter data please");
            } else if ((!email.isEmpty()) && (!email.contains("@"))) {
                binding.staffEmail.setError("Missing @ symbol");

            } else if (!password.equals(repeat_password)) {

                Toast.makeText(requireContext(), "Password Not match", Toast.LENGTH_LONG * 2).show();

            } else {
//creating a new account to a user and signing him/her out
                signUp(email, password);
                signOut();
                //      findNavController(this).navigate(R.id.staffing);
            }
        });
    }

    private void signUp(String email, String password) {

        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {

                Toast.makeText(requireContext(), "Account created successfully", Toast.LENGTH_SHORT).show();
                //Get the current user details
                FirebaseUser user = auth.getCurrentUser();

                assert user != null;
                Log.i(TAG, "signUp: " + user.getUid());
                Toast.makeText(requireContext(), user.getUid(), Toast.LENGTH_SHORT).show();
            } else {
                // If sign in fails, display a message to the user.
                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                Toast.makeText(requireContext(), "Authentication failed.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void signOut() {
        auth.signOut();
    }
}