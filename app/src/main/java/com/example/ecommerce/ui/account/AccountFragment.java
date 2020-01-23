package com.example.ecommerce.ui.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ecommerce.APIInterface;
import com.example.ecommerce.AddressUpdate;
import com.example.ecommerce.App;
import com.example.ecommerce.LoginActivity;
import com.example.ecommerce.LoginCheck;
import com.example.ecommerce.NavigationHome;
import com.example.ecommerce.OrderHistory;
import com.example.ecommerce.R;
import com.example.ecommerce.RegistrationActivity;
import com.example.ecommerce.SecondActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountFragment extends Fragment  implements View.OnClickListener {


    SignInButton sign;
    int RC_SIGN_IN=0;
    GoogleSignInClient mGoogleSignInClient;
    APIInterface apiInterface;
    Call<ResponseBody> call;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean saveLogin;
    private String username,pass;
    List<Object> token;
    private AccountViewModel accountViewModel;

//    App app=new App();
//    SharedPreferences loginPreferences=app.getsharedPreferences();

     SharedPreferences loginPreferences;
  SharedPreferences.Editor loginPrefsEditor;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginPreferences = getContext().getSharedPreferences("loginPrefs",Context.MODE_PRIVATE );
        loginPrefsEditor = loginPreferences.edit();


        if(loginPreferences.getBoolean("isLoggedIn",false))
        {
            accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
            root = inflater.inflate(R.layout.activity_profile, container, false);
            ImageView imageView=(ImageView) root.findViewById(R.id.profilepic);
            TextView username=(TextView) root.findViewById(R.id.personname);
            Button order_history=(Button) root.findViewById(R.id.orderhistory);
            Button change_cred=(Button) root.findViewById(R.id.changepassword);
            Button update_address=(Button) root.findViewById(R.id.changedelivery);
            Button login_history=(Button) root.findViewById(R.id.loginhistory);
            Button logout=root.findViewById(R.id.logout);

            //load user image
            Glide.with(getContext()).applyDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_launcher_background)).load("https://cdn3.iconfinder.com/data/icons/vector-icons-6/96/256-512.png").into(imageView);

            accountViewModel.getText().observe(this, new Observer<String>() {

                @Override
                public void onChanged(@Nullable String s) {
                    //textView.setText(s);
                }
            });
            order_history.setOnClickListener(this);
            change_cred.setOnClickListener(this);
            update_address.setOnClickListener(this);
            login_history.setOnClickListener(this);
            logout.setOnClickListener(this);

        }

        else {
            accountViewModel =
                    ViewModelProviders.of(this).get(AccountViewModel.class);

             root = inflater.inflate(R.layout.activity_login, container, false);

            sign = root.findViewById(R.id.googleLogin);
            sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.googleLogin:
                            signIn();
                            break;
                    }
                }
            });

            GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestEmail()
                    .build();

            mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);
            loginButton = root.findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("email"));

            callbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            Intent intent = new Intent(getContext(), SecondActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onCancel() {
                            // App code
                        }

                        @Override
                        public void onError(FacebookException exception) {
                            // App code
                        }
                    });

            TextView newUser = root.findViewById(R.id.newUser);
            newUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getContext(), RegistrationActivity.class);
                    startActivity(intent);
                }
            });
            final EditText user = root.findViewById(R.id.userName);
            final EditText password = root.findViewById(R.id.password);
            final CheckBox rememberMe = root.findViewById(R.id.rememberMe);


            //remember me functionality

            saveLogin = loginPreferences.getBoolean("saveLogin", false);
            if (saveLogin == true) {
                user.setText(loginPreferences.getString("username", ""));
                password.setText(loginPreferences.getString("password", ""));
                rememberMe.setChecked(true);
            }

            Button button = root.findViewById(R.id.signin);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //  InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    // imm.hideSoftInputFromWindow(user.getWindowToken(), 0);

                    username = user.getText().toString();
                    pass = password.getText().toString();

                    if (validateLogin(username, pass)) {
                    /*if (rememberMe.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", username);
                        loginPrefsEditor.putString("password", pass);
                        loginPrefsEditor.apply();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.apply();
                    }*/

                        apiInterface = App.getRetrofit().create(APIInterface.class);
                        LoginCheck loginCheck = new LoginCheck(username, pass);
                        call = apiInterface.login(loginCheck);
                        call.enqueue(new Callback<ResponseBody>() {
                            @Override
                            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                              //  token = (List<Object>) response.body();
                               //
                                // System.out.println("rtilewbhd   "+token);
                                loginPrefsEditor.putBoolean("isLoggedIn", true);
                                loginPrefsEditor.commit();
                                System.out.println(loginPreferences.getBoolean("isLoggedIn",false));
                                Intent i=new Intent(getContext(),NavigationHome.class);
                                startActivity(i);
//                            loginPreferences.getString(token.get(0).toString(),""); // shared preferences to store access tokens should be here
//                            loginPrefsEditor.putString();
                                Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onFailure(Call<ResponseBody> call, Throwable t) {
                                Toast.makeText(getContext(), "Login failure", Toast.LENGTH_SHORT).show();
                            }
                        });
                        // Intent intent=new Intent(getContext(),SecondActivity.class);
                        //startActivity(intent);
                    }
                }
            });
            TextView forgotPass = root.findViewById(R.id.forgotPass);
            forgotPass.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loginPrefsEditor.putString("username", username = user.getText().toString());
                    String email = loginPreferences.getString("username", username);
                    sendEmail(email);

                }
            });

           // return  root;
        }

        return root;
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data); // added for fb
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent=new Intent(getContext(),SecondActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }


    AccessToken accessToken = AccessToken.getCurrentAccessToken();
    boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

    private boolean validateLogin(String username, String password){
        if(username == null || username.trim().length() == 0){
            Toast.makeText(getContext(), "Username is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(password == null || password.trim().length() == 0){
            Toast.makeText(getContext(), "Password is required", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    protected void sendEmail(String email) {
        Log.i("Send email", "");
        String[] TO ={email};
//        String[] CC = {""};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mail to:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
//        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Forgot password");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "You requested for a password change");

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
           // finish();
            Log.i("Finished sending email", "");
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getContext(), "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.orderhistory:

                Intent i = new Intent(getContext(), OrderHistory.class);

                startActivity(i);

                break;

            case R.id.changepassword:

                break;

            case R.id.changedelivery:
                Intent i1 = new Intent(getContext(), AddressUpdate.class);
                startActivity(i1);
                break;

            case R.id.loginhistory:

                break;

            case R.id.logout:
                loginPrefsEditor.putBoolean("isLoggedIn",false);
                loginPrefsEditor.commit();
                Intent i3=new Intent(getContext(), NavigationHome.class);
                startActivity(i3);
                break;


        }
    }
}