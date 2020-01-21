package com.example.ecommerce.ui.category5;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecommerce.R;
import com.example.ecommerce.ui.account.AccountViewModel;


public class Category5Fragment extends Fragment {


    private Category5ViewModel category5ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        category5ViewModel =
                ViewModelProviders.of(this).get(Category5ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category5, container, false);
        final TextView textView = root.findViewById(R.id.category5text);
        category5ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
