package com.example.ecommerce.ui.category3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Category3ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Category3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category3 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
