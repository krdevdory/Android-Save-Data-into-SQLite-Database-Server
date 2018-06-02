package com.devdory.dataintosql;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
    Created by krdevdory on 2018.
    http://devdory.com/
 */

public class MainActivity extends AppCompatActivity {


    String server_url = "http://@@@/submit.php"; //URL of submit.php
    //You can download submit.php on https://github.com/krdevdory/Android-Save-Data-into-SQLite-Database-Server/blob/master/submit.php

    String name, email, message = null;

    EditText etName, etEmail, etMessage;
    Button submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.name);
        etEmail = (EditText) findViewById(R.id.email);
        etMessage = (EditText) findViewById(R.id.message);

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                email = etEmail.getText().toString();
                message = etMessage.getText().toString();

                if(name.matches("") || email.matches("") || message.matches("")){
                    Toast.makeText(getApplicationContext(), "Fill in the blanks.", Toast.LENGTH_SHORT).show();
                } else {
                    new Insert().execute();
                }
                //If there is any blank Edittext, you can't insert data into SQLite Database.

            }
        });

    }

    public class Insert extends AsyncTask<String, Void, Boolean> {

        ProgressDialog dialogP;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogP = new ProgressDialog(MainActivity.this);
            dialogP.setTitle("Wait... ");
            dialogP.setMessage("Inserting...");
            dialogP.setCancelable(false);
            dialogP.show();
        }

        @Override
        protected Boolean doInBackground(String... url) {

            try {

                List<NameValuePair> data = new ArrayList<>();
                data.add(new BasicNameValuePair("name", name));
                data.add(new BasicNameValuePair("email", email));
                data.add(new BasicNameValuePair("message", message));
                //You need to add some codes in build.gradle(Module: app). Check build.gradle(Module: app) line 17.

                //Add data to List.

                HttpClient httpClient = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(server_url);
                httpPost.setEntity(new UrlEncodedFormEntity(data, "UTF-8"));

                HttpResponse response = httpClient.execute(httpPost);

                HttpEntity entity = response.getEntity();

                return true;


            } catch (IOException e) {
                e.printStackTrace();

            }

            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialogP.cancel();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Result");
            builder.setMessage("Data inserted into SQLite Database.");
            builder.setCancelable(true);

            builder.setPositiveButton(
                    "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();

                            Intent intent = getIntent();
                            finish();
                            startActivity(intent);
                            //Refresh activity to prevent spam.
                        }
                    });
            AlertDialog dialogA = builder.create();
            dialogA.show();
            //Inform if data inserted into SQLite Database successfully.
        }
    }

}
