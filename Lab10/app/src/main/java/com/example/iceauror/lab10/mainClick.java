package com.example.iceauror.lab10;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by Jeffery Schons on 11/1/2017.
 */

public class mainClick extends AppCompatActivity {
    private Button nextButton;
    private Button homeButton;
    private EditText TextTOEdit;
    private String text;

    private Socket connexion = null;
    private PrintWriter writer = null;
    private BufferedInputStream reader = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainclick);


        nextButton = (Button) findViewById(R.id.button1);
        homeButton = (Button) findViewById(R.id.button2);

        nextButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = nextButton.getText().toString();
                //Intent startSecondActivity = new Intent(MainActivity.this, secondActivity.class);
                // startSecondActivity.putExtra("Text_info", text);

                //startActivityForResult(startSecondActivity, 0);

                String n = "Node";
               // write(n);


                Intent intent = new Intent(mainClick.this, MapsActivity.class);
                intent.putExtra("Text_info", text);
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                text = nextButton.getText().toString();
                //Intent startSecondActivity = new Intent(MainActivity.this, secondActivity.class);
                // startSecondActivity.putExtra("Text_info", text);

                //startActivityForResult(startSecondActivity, 0);

                Intent intent = new Intent(mainClick.this, MapsActivity.class);
                intent.putExtra("Text_info", text);
                startActivity(intent);
            }
        });

    }


/*
    public void write(String n) {

        try {

            Socket socket = new Socket("localhost",5000);
            //printSocketInfo(socket);

            // 2. WRITE A MESSAGE TO THE SOCKET TO SEND TO THE SERVER
            PrintWriter out = new PrintWriter(socket.getOutputStream());
            //out.print("Client socket Local Address: " + socket.getLocalAddress() + ":" + socket.getLocalPort());
            //out.println("  Client socket Remote Address: " + socket.getRemoteSocketAddress());
            //out.print("r2d2Nodesend");
            out.print("c3poNodesend");
            out.flush();

        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
*/
}
