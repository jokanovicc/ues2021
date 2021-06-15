package com.example.taverna.ui.korpa;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.taverna.R;
import com.example.taverna.ui.home.HomeViewModel;

public class KorpaFragment extends Fragment {


    private KorpaViewModel korpaViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        korpaViewModel =
                new ViewModelProvider(this).get(KorpaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_korpa, container, false);
        final TextView textView = root.findViewById(R.id.text_korpa);
        korpaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }
}
