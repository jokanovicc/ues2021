package com.example.taverna.ui.korisnici;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class KorisniciViewModal extends ViewModel {


    private MutableLiveData<String> mText;

    public KorisniciViewModal() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
