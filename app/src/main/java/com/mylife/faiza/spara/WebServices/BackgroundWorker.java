package com.mylife.faiza.spara.WebServices;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.mylife.faiza.spara.RegisterationActivity;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by faizan on 05/09/2016.
 */
public class BackgroundWorker extends AsyncTask<String, Void, String>

        // we use context to pass variable from one place to another
// for that we have to construct constructer for it

{
   /* String username, pass ,mobile , email;
    EditText mUsername,mUserPass,mUserEmail,mUserMobile ;*/
    ProgressDialog progressDialog ;
    AlertDialog alertDialog;
    Context context;

    public BackgroundWorker(Context ctx) {
        context = ctx;
    }


    /*  protected Void doInBackground(Void params) {
          return null;
      }
    */
    protected void onPreExecute() {
       /* alertDialog = new AlertDialog.Builder(context).create();*/

        if (isConnection()) {
            progressDialog = new ProgressDialog(this.context);
            progressDialog.setMessage("Please wait ....");
            progressDialog.setCancelable(false);
            progressDialog.show();
        } else {
            showConnectionErrorDialog();
        }
    }


    @Override
    protected String doInBackground(String... params) {
        String type = params[0];
        String login_url = "http://www.kworld.16mb.com/PhpApp/userinfo.php";
        String register_url = "http://www.kworld.16mb.com/PhpApp/reg.php";

        if (type.equals("msg")) {
            try {
                String username = params[1];
                String pass = params[2];


                URL uri = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) uri.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8")
                        + "&" + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8");


                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // now to read fro server
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                // we have to read esult line by line

                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(type.equals("SignUp"))
            try {
                String username = params[1];
                String pass = params[2];
                String mobile = params[3];
                String email = params[4];

                URL uri = new URL(register_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) uri.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoInput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&"
                        + URLEncoder.encode("pass", "UTF-8") + "=" + URLEncoder.encode(pass, "UTF-8")
                        + "&" + URLEncoder.encode("mobile", "UTF-8") + "=" + URLEncoder.encode(mobile, "UTF-8")
                        + "&" + URLEncoder.encode("email", "UTF-8") + "=" + URLEncoder.encode(email, "UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();

                // now to read fro server
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                // we have to read esult line by line

                String result = "";
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result += line;
                }
                bufferedReader.close();
                httpURLConnection.disconnect();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        return null;

    }

    protected void onPostExecute(String result) {
        /*alertDialog.setMessage(result);
        alertDialog.setTitle("Login detail  ");*/
        progressDialog.dismiss();

      //  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();

     //  context.mUserlogin.setText("");
     //   mUserPassword.setText("");

    }

    protected void onProgressUpdate(Void values) {
        super.onProgressUpdate(values);
    }
    protected boolean isConnection() {
   //     ConnectivityManager manage = (ConnectivityManager) RegisterationActivity.this.getSystemService(Context.CONNECTIVITY_SERVICE);
        ConnectivityManager manage = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo info = manage.getActiveNetworkInfo();
        if (info != null && info.isConnectedOrConnecting()) {

            return true;
        } else {
            return false;
        }
    }

    public void showConnectionErrorDialog() {
        //android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RegisterationActivity.this);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);


        builder.setTitle("No Internet !!");
        builder.setMessage("Not connected to the network right now. Please turn it on and try again later");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
              //  RegisterationActivity.this.finish();
            }
        });

        android.app.AlertDialog dialog = builder.create();
        dialog.show();
    }

}
  /*  @Override
    protected void doInBackground(String... params) {
        return null;
    }
    protected void onPreExecute(Void... params) {
        super.onPreExecute();
    }

    protected void onPostExecute(Void... params) {
        super.onPostExecute(params);
    }

    protected Void onProgress(Void... params) {
        return null;
    }
}
*/