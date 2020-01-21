package com.example.ecommerce.ui.category4;

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

public class Category4Fragment extends Fragment {

    private Category4ViewModel category4ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        category4ViewModel =
                ViewModelProviders.of(this).get(Category4ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category4, container, false);
        final TextView textView = root.findViewById(R.id.category4text);
        category4ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}
