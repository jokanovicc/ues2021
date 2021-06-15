package com.example.taverna.ui.porudzbine;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PorudzbinaViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PorudzbinaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
