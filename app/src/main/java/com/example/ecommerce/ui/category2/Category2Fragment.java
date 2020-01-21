package com.example.ecommerce.ui.category2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.ecommerce.R;
import com.example.ecommerce.cart.cart;

public class Category2Fragment extends Fragment {


    private Category2ViewModel category2ViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        category2ViewModel =
                ViewModelProviders.of(this).get(Category2ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category2, container, false);
        final TextView textView = root.findViewById(R.id.category2text);
        final Button button=root.findViewById(R.id.addtocartbutton);
        category2ViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);

            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), cart.class);
                startActivity(intent);

            }
        });
        return root;
    }

}
