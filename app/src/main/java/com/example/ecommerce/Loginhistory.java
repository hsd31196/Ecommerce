package com.example.ecommerce;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ecommerce.app.App;
import com.example.ecommerce.login.APIInterface;
import com.example.ecommerce.login.LoginHistoryPOJO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
public class Loginhistory extends AppCompatActivity {

    ListView listView;
    TextView textView;
    List<String> loginDetails=new ArrayList<String>();
    Retrofit retrofit= App.getRetrofit();
    APIInterface apiInterface=retrofit.create(APIInterface.class);
    Call<List<LoginHistoryPOJO>> call;
    SharedPreferences preferences;
int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        getSupportActionBar().setTitle("Login Details");

        setContentView(R.layout.activity_loginhistory);
        listView=findViewById(R.id.listView);
        textView=findViewById(R.id.textLoginHistory);
        call=apiInterface.getloginHistory("Bearer"+preferences.getString("accessToken",""),preferences.getString("username",""));
        call.enqueue(new Callback<List<LoginHistoryPOJO>>() {
            @Override
            public void onResponse(Call<List<LoginHistoryPOJO>> call, Response<List<LoginHistoryPOJO>> response) {
                count=response.body().size();
                for(int i=0;i<count;i++)
                {
                    loginDetails.add(response.body().get(i).getLoginDate());
                }

                ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,
                        android.R.id.text1,loginDetails);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<LoginHistoryPOJO>> call, Throwable t) {

            }
        });



    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
