package com.example.ecommerce.ui.account;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.ecommerce.Loginhistory;
import com.example.ecommerce.login.APIInterface;
import com.example.ecommerce.app.App;
import com.example.ecommerce.cart.CartAPIInterface;
import com.example.ecommerce.order.CheckOut;
import com.example.ecommerce.login.LoginCheck;
import com.example.ecommerce.NavigationHome;
import com.example.ecommerce.order.OrderHistory;
import com.example.ecommerce.R;
import com.example.ecommerce.login.RegistrationActivity;
import com.example.ecommerce.login.RegistrationDTO;
import com.example.ecommerce.TokenClass;
import com.example.ecommerce.entity.CartRoomEntity;
import com.example.ecommerce.viewmodel.CartViewModel;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executor;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AccountFragment extends Fragment  implements View.OnClickListener,Callback<ResponseBody> {

CartViewModel cartViewModel;
    SignInButton sign;
    int RC_SIGN_IN=0;
    GoogleSignInClient mGoogleSignInClient;
    Call<TokenClass> call;
    Call<ResponseBody> callRegister;

    private LoginButton loginButton;
    private CallbackManager callbackManager;
    private boolean saveLogin;
    private String username,pass;
    Object token;
    private AccountViewModel accountViewModel;
    Retrofit retrofit= App.getRetrofit();
    CartAPIInterface cartAPIInterface=retrofit.create(CartAPIInterface.class);
    APIInterface apiInterface=retrofit.create(APIInterface.class);
    Call<ResponseBody> callfromCart;

//    App app=new App();
//    SharedPreferences loginPreferences=app.getsharedPreferences();

  public    SharedPreferences loginPreferences;
  public SharedPreferences.Editor loginPrefsEditor;

    View root;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        loginPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        loginPrefsEditor = loginPreferences.edit();
        cartViewModel= ViewModelProviders.of(this).get(CartViewModel.class);
        final GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(getContext(), gso);

        if(loginPreferences.getBoolean("isLoggedIn",false))
        {
            addToCart(loginPreferences.getString("username",""));
            // if(value of intent from )
            accountViewModel = ViewModelProviders.of(this).get(AccountViewModel.class);
            root = inflater.inflate(R.layout.activity_profile, container, false);
            ImageView imageView=(ImageView) root.findViewById(R.id.profilepic);
            TextView username=(TextView) root.findViewById(R.id.personname);
            username.setText(loginPreferences.getString("username","username"));
            Button order_history=(Button) root.findViewById(R.id.orderhistory);
            //Button change_cred=(Button) root.findViewById(R.id.changepassword);
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
           // change_cred.setOnClickListener(this);
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


            loginButton = root.findViewById(R.id.login_button);
            loginButton.setReadPermissions(Arrays.asList("email"));

            callbackManager = CallbackManager.Factory.create();

            LoginManager.getInstance().registerCallback(callbackManager,
                    new FacebookCallback<LoginResult>() {
                        @Override
                        public void onSuccess(LoginResult loginResult) {
                            // App code
                            Intent intent = new Intent(getContext(), NavigationHome.class);
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
                        call.enqueue(new Callback<TokenClass>() {
                            @Override
                            public void onResponse(Call<TokenClass> call, Response<TokenClass> response) {
                                Gson gson=new Gson();
                                if(response.code()==200)
                                {
                                   // System.out.println("rtilewbhd   "+response.body().getAccessToken());
                                    //System.out.println(response.body().getTokenType());
                                    loginPrefsEditor.putBoolean("isLoggedIn", true);
                                    loginPrefsEditor.putString("username",username);
                                    loginPrefsEditor.putString("accessToken",response.body().getAccessToken());
                                    loginPrefsEditor.putString("tokenType",response.body().getTokenType());
                                    loginPrefsEditor.commit();
                                    System.out.println(loginPreferences.getString("accessToken",""));
                                    //else {
                                       Intent i = new Intent(getContext(), NavigationHome.class);
                                       startActivity(i);
                                       //getActivity().finish();
                                   //}

//                                    if(getArguments().getString("place")!=null) {
//                                        String msg = getArguments().getString("place");
//                                        if (msg.equals("msg from checkout")) {
//                                            Intent i1 = new Intent(getContext(), CheckOut.class);
//                                            startActivity(i1);
//                                        }
//                                    }
                                    Toast.makeText(getContext(), "Login Success", Toast.LENGTH_SHORT).show();

                                }
                                else
                                {
                                    Toast.makeText(getContext(),"Login Failed",Toast.LENGTH_LONG).show();
                                }

//
                            }

                            @Override
                            public void onFailure(Call<TokenClass> call, Throwable t) {
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

        }

        return root;
    }

    @Override
    public void onStop() {
        super.onStop();
       // getActivity().finish();
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
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
            if(acct!=null) {
                String email = acct.getEmail();
                String name=acct.getDisplayName();
                String IdToken=acct.getIdToken();
                String password=acct.getId();
                checkIfRegistered(name,email,password);
                Intent intent=new Intent(getContext(),NavigationHome.class);
                startActivity(intent);
                getActivity().finish();
            }
            // Signed in successfully, show authenticated UI.

        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }


    public void checkIfRegistered(String name, final String email, final String password)
    {

        RegistrationDTO object=new RegistrationDTO(name,email,password);

        callRegister=apiInterface.postRegistrationDetails(object);
        callRegister.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call1, Response<ResponseBody> response) {
                System.out.println(response.code());
                //already registered
                if(response.code()==400 || response.code()==201)
                {
                    LoginCheck loginObject=new LoginCheck(email,password);
                    call=apiInterface.login(loginObject);
                    call.enqueue(new Callback<TokenClass>() {
                        @Override
                        public void onResponse(Call<TokenClass> call, Response<TokenClass> response) {
                            loginPrefsEditor.putBoolean("isLoggedIn", true);
                            loginPrefsEditor.putString("username",email);
                            loginPrefsEditor.putString("accessToken",response.body().getAccessToken());
                            loginPrefsEditor.putString("tokenType",response.body().getTokenType());
                            loginPrefsEditor.commit();
                        }

                        @Override
                        public void onFailure(Call<TokenClass> call, Throwable t) {

                        }
                    });
                }
                //not registered

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Toast.makeText(getContext(),"Network Problem",Toast.LENGTH_SHORT).show();

            }
        });


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

//            case R.id.changepassword:
//
//                break;

            case R.id.changedelivery:
                Intent i1 = new Intent(getContext(), AddressUpdate.class);
                startActivity(i1);
                break;

            case R.id.loginhistory:
                Intent i2=new Intent(getContext(), Loginhistory.class);
                startActivity(i2);
                break;

            case R.id.logout:
                mGoogleSignInClient.signOut();
                loginPrefsEditor.putBoolean("isLoggedIn",false);
                loginPrefsEditor.putString("username","");
                loginPrefsEditor.commit();
                cartViewModel.delete();
                Intent i3=new Intent(getContext(), NavigationHome.class);
                startActivity(i3);
                getActivity().finish();
                break;


        }
    }

    public void addToCart(final String username)
    {
        cartViewModel.getAllItems().observe(this, new Observer<List<CartRoomEntity>>() {
            @Override
            public void onChanged(@Nullable final List<CartRoomEntity> cartRoomEntities) {
               int count =cartRoomEntities.size();
               newFunction(cartRoomEntities,username);
            }
        });
    }

    public void newFunction(List<CartRoomEntity> cartRoomEntities,String username)
    {
        int count=cartRoomEntities.size();
        for(int i=0;i<count;i++)
        {
            CartRoomEntity object=cartRoomEntities.get(i);
            object.setUserId(username);
            callfromCart=cartAPIInterface.addProduct("Bearer "+loginPreferences.getString("accessToken",""),object);
            System.out.println(object.toString());
            callfromCart.enqueue( this);
        }
    }

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
        System.out.println("response ....."+response.body());
    }

    @Override
    public void onFailure(Call<ResponseBody> call, Throwable t) {

    }
}