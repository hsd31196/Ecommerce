package com.example.ecommerce.ui.category3;

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

public class Category3Fragment  extends Fragment {

    private Category3ViewModel category3ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        category3ViewModel =
                ViewModelProviders.of(this).get(Category3ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category3, container, false);
        final TextView textView = root.findViewById(R.id.category3text);
        category3ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}
