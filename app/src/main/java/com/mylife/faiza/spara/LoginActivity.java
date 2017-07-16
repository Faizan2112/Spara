package com.mylife.faiza.spara;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mylife.faiza.spara.WebServices.BackgroundWorker;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

/**
 * Created by faiza on 24/08/2016.
 */
public class LoginActivity extends Activity {
    String userNameLogin, userPassLogin;
    Button signUp;
    Button login;
    EditText mUserlogin;
    EditText mUserPassword;

    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_login);
        signUp = (Button) findViewById(R.id.login_signUp);
        login = (Button) findViewById(R.id.login_submit);
        mUserlogin = (EditText) findViewById(R.id.login_username);
        mUserPassword = (EditText) findViewById(R.id.login_userpass);
        // signUp.setOnClickListener(this);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentSign = new Intent(LoginActivity.this, RegisterationActivity.class);
                startActivity(intentSign);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String userNameLogin, userPassLogin;
                String type = "msg";
                userNameLogin = mUserlogin.getText().toString().trim();
                userPassLogin = mUserPassword.getText().toString().trim();

                boolean isValidRegistration = validateUser();
                if (isValidRegistration) {
                    BackgroundTask backgroudtask = new BackgroundTask();
                    backgroudtask.execute(type, userNameLogin, userPassLogin);
                   Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intentMain);

                }
/*

                   Intent intentMain = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intentMain);
*/

            }
        });

    }

    private boolean validateUser() {
        // final String USERNAME_PATTERN = "^[a-z0-9_-]{3,15}$";
        /*&& !Pattern.compile(USERNAME_PATTERN).matcher(username).matches()*/
        boolean isValid = true;

        if ("".equals(userNameLogin)) {
            mUserlogin.setError("Please enter your Full Name");
            mUserlogin.setFocusable(true);
            isValid = false;
        }

       /* if ("".equals(mUserlogin)) {
            mUserlogin.setError("Enter the name");
            isValid = false;*/

      /*  if ("".equals(userPassLogin)) {
            mUserPassword.setError("Please Enter your password ");
            mUserPassword.setFocusable(true);
            isValid = false;*/
        //}
        /*if ("".equals(userEmaile)) {
            userEmail.setError("Please Enter your Email Address");
            userEmail.setFocusable(true);
            isValid = false;
        }

      *//*  if ("".equals(userQualificatone))
        {
            userQualificaton.setError("Please fill your qualification");
            userQualificaton.setFocusable(true);
            isValid = false;
        }*//*
        if ("".equals(userLookingfore))
        {
            userLookingfor.setError("Please fill what you are looking for");
            userLookingfor.setFocusable(true);
            isValid = false;
        }*/
        return isValid;
    }


    public class BackgroundTask extends AsyncTask<String, String, String> {
        private ProgressDialog progressDialog;

        protected void onPreExecute() {
            if (isConnection()) {
                progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please wait ....");
                progressDialog.setCancelable(false);
                progressDialog.show();
            } else {
                showConnectionErrorDialog();
            }
        }


        protected String doInBackground(String... params) {

            userNameLogin = params[0];
            userPassLogin = params[1];
        /*userEmaile= params[2];
        userQualificatone = params[3];
        userLookingfore = params[4];
*/
            try {
                URL url = new URL("http://www.kworld.16mb.com/PhpApp/userinfo.php");
                //URL url = new URL("add_info_url");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);

                //httpURLConnection.setDoInput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(OS, "UTF-8"));
                String data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(userNameLogin, "UTF-8")
                        + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(userPassLogin, "UTF-8");

                  /*
                    + "&" +
                   URLEncoder.encode("userEmail", "UTF-8") + "=" + URLEncoder.encode(userEmaile, "UTF-8")
                    + "&" +
                       *//* URLEncoder.encode("userQualification", "UTF-8") + "=" + URLEncoder.encode(userMobilee, "UTF-8")
                        + "&" +*//*
                    URLEncoder.encode("userLookingfor", "UTF-8") + "=" + URLEncoder.encode(userLookingfore, "UTF-8")
                    + "&" +
                    URLEncoder.encode("userQualification", "UTF-8") + "=" + URLEncoder.encode(userItem, "UTF-8")
                 */
                ;
                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream IS = httpURLConnection.getInputStream();

                IS.close();
                //httpURLConnection.connect();
                httpURLConnection.disconnect();
                return "Thanks for submitting your detail";
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String result) {

            progressDialog.dismiss();

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
            mUserlogin.setText("");
            mUserPassword.setText("");
     /*   userLookingfor.setText("");
        userEmail.setText("");
        //   userQualificaton.setText("");
*/
        }


        protected boolean isConnection() {
            ConnectivityManager manage = (ConnectivityManager) LoginActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo info = manage.getActiveNetworkInfo();
            if (info != null && info.isConnectedOrConnecting()) {

                return true;
            } else {
                return false;
            }
        }

        public void showConnectionErrorDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("No Internet !!");
            builder.setMessage("Not connected to the network right now. Please turn it on and try again later");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    LoginActivity.this.finish();
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }
}

/*
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.login_signUp:
                Intent intentSignUp = new Intent(LoginActivity.this,RegisterationActivity.class);
                startActivity(intentSignUp);
            break;


                */
/*Intent intentLogin = new Intent(LoginActivity.this,MainActivity.class);
               startActivity(intentLogin);
                break;
*//*

        }
    }
// validating login and forwarding to main activity
    private void hitLoginAPI() {
        Intent intenMain = new Intent(LoginActivity.this , MainActivity.class);
        startActivity(intenMain);

    }

    private boolean validateteLoginView() {
        String username ,pass ;
        username =mUserlogin.getText().toString().trim();
        pass = mUserPassword.getText().toString().trim();

        if(username=="" )
        {
            mUserlogin.setError("please enter your user name");
            return false ;
        }
        if(pass=="" )
        {
            mUserlogin.setError("please enter your user name");
            return false ;
        }

        return true;

    }
}*/
