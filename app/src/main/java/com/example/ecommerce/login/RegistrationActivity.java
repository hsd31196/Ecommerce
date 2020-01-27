package com.example.ecommerce.login;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.appcompat.widget.Toolbar;

        import android.content.Intent;
        import android.os.Bundle;
        import android.text.TextUtils;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.ecommerce.app.App;
        import com.example.ecommerce.R;

        import java.util.regex.Pattern;

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
        Toolbar toolbar=(Toolbar) findViewById(R.id.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Button button=findViewById(R.id.register);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(true) {


                    if (isValidPassword(password.getText().toString())) {

                        apiInterface = App.getRetrofit().create(APIInterface.class);
                        RegistrationDTO registrationDTO = new RegistrationDTO(firstName.getText().toString(), email.getText().toString(), password.getText().toString());
                        Call<ResponseBody> call = apiInterface.postRegistrationDetails(registrationDTO);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                int code = response.code();
                                if(response.code()==400)
                                {
                                    Toast.makeText(getApplicationContext(),"UserId already existing",Toast.LENGTH_SHORT).show();
                                }
                                else if(response.code()==201)
                                {
                                Toast.makeText(RegistrationActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        });

                    } else {
                        Toast.makeText(getApplicationContext(), "Password  is not Strong. It should contain a alphabet, a " +
                                "number and a special character", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Email Id is not valid!",Toast.LENGTH_LONG).show();
                }


            }
        });

    }

    public static boolean isValidPassword(String s) {
        Pattern PASSWORD_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9\\!\\@\\#\\$]{8,24}");

        return !TextUtils.isEmpty(s) && PASSWORD_PATTERN.matcher(s).matches();
    }


    public static boolean isValidEmail(String s) {
        Pattern EMAIL_PATTERN
                = Pattern.compile(
                "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+");

        return !TextUtils.isEmpty(s) && EMAIL_PATTERN.matcher(s).matches();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return  true;
    }
}

