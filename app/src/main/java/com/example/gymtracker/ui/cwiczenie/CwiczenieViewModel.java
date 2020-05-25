package com.example.gymtracker.ui.cwiczenie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


    public class CwiczenieViewModel extends ViewModel {

        private MutableLiveData<String> mText;

        public CwiczenieViewModel() {
            mText = new MutableLiveData<>();
            mText.setValue("This is Cwiczenie");
        }

        public LiveData<String> getText() {
            return mText;
        }
    }

