package com.mylife.faiza.spara;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mylife.faiza.spara.WebServices.BackgroundWorker;

/**
 * Created by faiza on 22/08/2016.
 */
public class RegisterationActivity extends Activity implements View.OnClickListener {
    String username, pass ,mobile , email;
    EditText mUsername,mUserPass,mUserEmail,mUserMobile ;
   Button mSignUp ;
    public void onCreate(Bundle saveInstanceState)
    {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_registration);
        mUsername=(EditText)findViewById(R.id.registration_username);
        mUserPass=(EditText)findViewById(R.id.registration_userpass);
        mUserEmail =(EditText)findViewById(R.id.registration_useremail);
        mUserMobile = (EditText)findViewById(R.id.registration_usermobile);



        mSignUp = (Button)findViewById(R.id.registration_signup);
      //  mSignUp.setOnClickListener(this);
    }

    public void SignUp (View view)
    {
        username = mUsername.getText().toString().trim();
        pass = mUserPass.getText().toString().trim();
        mobile = mUserMobile.getText().toString().trim();
        email = mUserEmail.getText().toString().trim();
        String type = "SignUp";
        boolean validateRegistration = isValidInfo();
        if(validateRegistration) {
            BackgroundWorker backgroundWorker = new BackgroundWorker(this);
            //   require context
            //   now we have to execure this
            backgroundWorker.execute(type, username, pass, mobile, email);
        }
        Intent intentLoginPage = new Intent(RegisterationActivity.this,LoginActivity.class);
        startActivity(intentLoginPage);

    }

    @Override
    public void onClick(View v) {
        username = mUsername.getText().toString().trim();
        pass = mUserPass.getText().toString().trim();
        mobile = mUserMobile.getText().toString().trim();
        email = mUserMobile.getText().toString().trim();
        boolean isValidRegistration = isValidInfo();
    }

    private boolean isValidInfo() {
        /// make a variable to tell their property must be boolean type

   boolean isValid = true ;
        if ("".equals(username))
        {
            mUsername.setError("Enter use name");
            mUsername.setFocusable(true);
            isValid = false ;
        }
        if ("".equals(pass))
        {
            mUserPass.setError("Enter use password");
            mUserPass.setFocusable(true);
            isValid = false ;
        }
        if ("".equals(mobile))
        {
            mUserMobile.setError("Enter use mobile");
            mUserMobile.setFocusable(true);
            isValid = false ;
        }
        if ("".equals(email))
        {
            mUserEmail.setError("Enter use Email");
            mUserEmail.setFocusable(true);
            isValid = false ;
        }

        return isValid;
    }
    protected boolean isConnection() {
        ConnectivityManager manage = (ConnectivityManager) RegisterationActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manage.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {

            return true;
        } else {
            return false;
        }
    }

    public void showConnectionErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterationActivity.this);
        builder.setTitle("No Internet !!");
        builder.setMessage("Not connected to the network right now. Please turn it on and try again later");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                RegisterationActivity.this.finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
}

