package com.example.ecommerce.ui.category4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Category4ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Category4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category4 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
