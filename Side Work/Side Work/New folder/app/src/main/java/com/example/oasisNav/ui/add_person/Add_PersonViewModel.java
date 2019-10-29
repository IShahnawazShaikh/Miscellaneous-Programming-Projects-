package com.example.oasisNav.ui.add_person;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Add_PersonViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Add_PersonViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}