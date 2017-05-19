package com.example.stevenliao.httpconnect;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private String connect;
    private EditText Name;
    private EditText IP;
    private Button conBtn;
    private ConnectThread mConnectThread;
    public TextView readView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name = (EditText) findViewById(R.id.editText2);
        IP = (EditText) findViewById(R.id.editText3);
        conBtn = (Button) findViewById(R.id.button);
        readView = (TextView) findViewById(R.id.textView4);

        conBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NameCon = Name.getText().toString();
                String IPcon = IP.getText().toString();
                connect = IPcon + "?Name=" + NameCon;
                mConnectThread = new ConnectThread(connect);
                mConnectThread.start();

            }
        });



    }

    //Connecting Thread
    private class ConnectThread extends Thread {
            URLConnection conn;
        public ConnectThread(String connectIP) {
            try {

                  URL url = new URL(connectIP);
                  conn = url.openConnection();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {

                conn.setDoInput(true);
                Reader in = new InputStreamReader(conn.getInputStream(), "UTF-8");
                int data = in.read();
                String get = "";
                while (data != -1) {
                    get = get + data;
                }
                Message msg;
                msg = mhandler.obtainMessage(1,get);
                mhandler.sendMessage(msg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private final Handler mhandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    String show = (String)msg.obj;
                    readView.setText(show);
                    break;
            }
        }
    };
}
