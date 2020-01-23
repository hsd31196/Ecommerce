package com.example.ecommerce;

        import androidx.appcompat.app.AppCompatActivity;

        import android.content.Intent;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.google.gson.Gson;

        import java.io.IOException;
        import java.util.List;

        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {

    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText firstName=findViewById(R.id.editfname);
        //final EditText lastName=findViewById(R.id.editlname);
        final EditText email=findViewById(R.id.editemail);
        final EditText password=findViewById(R.id.editpass);
//        final EditText mobile=findViewById(R.id.editmobile);
//        final EditText address=findViewById(R.id.editaddress);

        final Intent intent=new Intent(getBaseContext(),SecondActivity.class);

        Button button=findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sendPost(firstName.getText().toString()/*lastName.getText().toString()*/,email.getText().toString()),password.getText().toString());/*,mobile.getText().toString(),address.getText().toString()*/
                //sendPost(firstName.getText().toString(),email.getText().toString(),password.getText().toString());
                apiInterface=App.getRetrofit().create(APIInterface.class);
                RegistrationDTO registrationDTO = new RegistrationDTO(firstName.getText().toString(),email.getText().toString(),password.getText().toString());
                Call<ResponseBody> call=apiInterface.postRegistrationDetails(registrationDTO);
                call.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        int code = response.code();
                        Toast.makeText(RegistrationActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(RegistrationActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

//   public void sendPost(String firstName,/*String lastName,*/String email,String password/*,String mobile,String address*/)
//    {
//        apiInterface = API.getClient().create(APIInterface.class);
//        Call<ResponseBody> call= apiInterface.postRegistrationDetails(firstName/*,lastName*/,email,password/*,mobile,address*/);
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                Toast.makeText(RegistrationActivity.this,"Registration Success",Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(RegistrationActivity.this,"Registration failed",Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}

