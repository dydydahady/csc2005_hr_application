package com.example.csc2005_team04_alt.ui.payslips;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PayslipsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PayslipsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Payslips fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}