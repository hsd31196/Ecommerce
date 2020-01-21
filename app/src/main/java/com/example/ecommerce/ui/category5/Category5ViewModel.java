package com.example.ecommerce.ui.category5;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Category5ViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public Category5ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is category5 fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

}
