package com.example.ecommerce.ui.account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ecommerce.R;
import com.example.ecommerce.address.UserAddress;
import com.example.ecommerce.app.App;
import com.example.ecommerce.login.APIInterface;

import okhttp3.Address;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import java.util.List;

public class AddressUpdate extends AppCompatActivity {
Retrofit retrofit= App.getRetrofit();
    APIInterface apiInterface=retrofit.create(APIInterface.class);
    Call<ResponseBody> call;
    Call<List<String>> callAddress;
    TextView streetvalue;
    TextView cityvalue;
    TextView statevalue;
    TextView countryvalue;
    TextView pincodevalue;
    TextView existingAddess;
    Button save;
    SharedPreferences preferences;
    String text="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_update);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        streetvalue=findViewById(R.id.streetvalue);
        cityvalue=findViewById(R.id.cityvalue);
        statevalue=findViewById(R.id.statevalue);
        countryvalue=findViewById(R.id.countryvalue);
        pincodevalue=findViewById(R.id.pincodevalue);
        existingAddess=findViewById(R.id.valueexistingAddress);
        save=findViewById(R.id.save);


        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Address Details");

        if(existingAddess.getText().toString().equals(""))
        {
            existingAddess.setText("Address not Updated");
        }

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 text=streetvalue.getText()+","+cityvalue.getText()+","+statevalue.getText()+","+countryvalue.getText()+
                        "-"+pincodevalue.getText();
                System.out.println(text);
                UserAddress address=new UserAddress(preferences.getString("username",""),text);
                call=apiInterface.setAddress("Bearer "+preferences.getString("accessToken",""),address);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            existingAddess.setText(text);

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
                Toast.makeText(getApplicationContext(),"Address Updated Successfully",Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        callAddress=apiInterface.getAddress("Bearer "+preferences.getString("accessToken",""),preferences.getString("username",""));
        callAddress.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                existingAddess.setText(response.body().get(0));

            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;

    }
}
