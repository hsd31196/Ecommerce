package com.example.ecommerce.ui.category1;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Category1ViewModel  extends ViewModel {


    private MutableLiveData<String> mText;

    public Category1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category1 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}


