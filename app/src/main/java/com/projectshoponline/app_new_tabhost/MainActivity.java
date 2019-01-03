package com.projectshoponline.app_new_tabhost;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Button
        Button b_next_1 = (Button) findViewById(R.id.b_next_1);
        b_next_1.setOnClickListener (new View.OnClickListener() {

            public void onClick(View V) {
                Intent intent =  new Intent(V.getContext(), Show_members_textview_1.class);
                startActivityForResult(intent, 0);
            }

        });//Button



    }
}
