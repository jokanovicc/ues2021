package com.example.taverna.ui.odjava;

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
import com.example.taverna.ui.korpa.KorpaViewModel;

public class OdjavaFragment extends Fragment {

    private OdjavaViewModel odjavaViewModel;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {



        odjavaViewModel =
                new ViewModelProvider(this).get(OdjavaViewModel.class);
        View root = inflater.inflate(R.layout.fragment_odjava, container, false);
        final TextView textView = root.findViewById(R.id.text_odjava);
        odjavaViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });



        return root;
    }
}
