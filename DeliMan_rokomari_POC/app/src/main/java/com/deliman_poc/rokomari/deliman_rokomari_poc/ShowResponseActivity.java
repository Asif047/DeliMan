package com.deliman_poc.rokomari.deliman_rokomari_poc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ShowResponseActivity extends AppCompatActivity {


    private TextView tvResponse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_response);

        tvResponse=findViewById(R.id.textview_message);

        Intent intent=getIntent();
        String response=intent.getStringExtra("response");

        Toast.makeText(this,response,Toast.LENGTH_LONG).show();

        tvResponse.setText(response);
    }
}
