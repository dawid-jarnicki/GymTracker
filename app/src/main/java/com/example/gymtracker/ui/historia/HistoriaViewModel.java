package com.example.gymtracker.ui.historia;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HistoriaViewModel extends ViewModel {
    private MutableLiveData<String> mText;

    public HistoriaViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Historia");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
