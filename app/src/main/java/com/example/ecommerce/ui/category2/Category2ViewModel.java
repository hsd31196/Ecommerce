package com.example.ecommerce.ui.category2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Category2ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Category2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category2 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}

