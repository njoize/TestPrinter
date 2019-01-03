package com.projectshoponline.app_new_tabhost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Page_get_member extends AppCompatActivity {

    TextView text_id;
    TextView text_name;

    Button b_clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_get_member);

        //////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ////////////////

        SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);
        String data_1 = sharedPref.getString("s_id", "");
        String data_2 = sharedPref.getString("s_name", "");




        text_id = (TextView) findViewById(R.id.text_id);
        text_name = (TextView) findViewById(R.id.text_name);

        // infoText.setText(name + " " + pass);
        text_id.setText("" + data_1);
        text_name.setText("" + data_2);


        b_clear = (Button) findViewById(R.id.b_clear);
        b_clear.setOnClickListener (new View.OnClickListener() {

            public void onClick(View  V) {

                if (clear_SharedPreferences())
                {
                    // When action Complete
                }

                //Toast.makeText(getApplicationContext(), "Save_SharedPreferences", Toast.LENGTH_SHORT).show();

                Intent intent =  new Intent (V.getContext(), TabHostMENU.class);
               startActivityForResult(intent, 0);
            }

        });//Button
    }
        public boolean clear_SharedPreferences() {

            SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);
            sharedPref.edit().remove("s_id").commit();
            sharedPref.edit().remove("s_name").commit();
            Toast.makeText(this, "clear!", Toast.LENGTH_LONG).show();


            return true;
        }

}
