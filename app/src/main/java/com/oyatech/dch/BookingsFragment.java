package com.oyatech.dch;

import static com.oyatech.dch.alerts.ProduceSnackbarKt.call;
import static com.oyatech.dch.alerts.ProduceSnackbarKt.location;
import static com.oyatech.dch.alerts.ProduceSnackbarKt.whatsApp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.oyatech.dch.databinding.FragmentBookingsBinding;

public class BookingsFragment extends Fragment implements View.OnClickListener {

    private FragmentBookingsBinding binding = null;


    public BookingsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookingsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     * Called immediately after {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}
     * has returned, but before any saved state has been restored in to the view.
     * This gives subclasses a chance to initialize themselves once
     * they know their view hierarchy has been completely created.  The fragment's
     * view hierarchy is not however attached to its parent at this point.
     *
     * @param view               The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     */
    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.footer.call.setOnClickListener(this);
        binding.footer.whatApp.setOnClickListener(this);
        binding.footer.location.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.call: {

                call(requireContext());
                break;
            }
            case R.id.whatApp: {
                whatsApp(requireContext());
                break;
            }
            case R.id.location: {
                location(requireContext());
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + view.getId());
        }
    }
}