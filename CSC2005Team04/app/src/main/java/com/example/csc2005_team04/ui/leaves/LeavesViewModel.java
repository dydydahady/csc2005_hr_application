package com.example.csc2005_team04.ui.leaves;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LeavesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LeavesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is leaves fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}