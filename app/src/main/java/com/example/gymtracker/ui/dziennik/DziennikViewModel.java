package com.example.gymtracker.ui.dziennik;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DziennikViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public DziennikViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Dziennik");
    }

    public LiveData<String> getText() {
        return mText;
    }


}
