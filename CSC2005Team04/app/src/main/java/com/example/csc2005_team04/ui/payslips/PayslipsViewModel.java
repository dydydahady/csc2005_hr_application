package com.example.csc2005_team04.ui.payslips;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayslipsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PayslipsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is payslips fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}