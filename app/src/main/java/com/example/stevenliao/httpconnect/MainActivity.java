package com.example.stevenliao.httpconnect;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.widget.EditText;

import java.io.IOError;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    String connect = "";
    EditText Name;
    EditText IP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.editText2);
        IP = (EditText) findViewById(R.id.editText3);
        String NameCon = Name.getText().toString();
        String IPcon = IP.getText().toString();
        connect = IPcon + "?Name=" + NameCon;
        try {
            URL url = new URL(connect);


        } catch (MalformedURLException e) {
          e.printStackTrace();
        } catch (IOException e) {
          e.printStackTrace();
        }
    }
}
