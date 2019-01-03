package com.projectshoponline.app_new_tabhost;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

//import com.squareup.picasso.Picasso;

public class Show_0_Detail extends AppCompatActivity {

    TextView tv_id;
    TextView tv_name;
    TextView tv_detail;
    ImageView image1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_0_detail);




        tv_id = (TextView) findViewById(R.id.tv_id);
       // tv_name = (TextView) findViewById(R.id.tv_name);
       // tv_detail = (TextView) findViewById(R.id.tv_detail);


        final Intent intent = getIntent();
        String s_id = intent.getStringExtra("id");
       // String s_name = intent.getStringExtra("product_name");
        //String s_detail = intent.getStringExtra("product_detail");
        //String s_img = intent.getStringExtra("product_img");
       // String s_status = intent.getStringExtra("status");


        tv_id.setText("" + s_id);
       // tv_name.setText("" + s_name);
        // tv_detail.setText("" + s_detail);
       // tv_detail.setText("" + s_status);

        // image1 = (ImageView) convertView.findViewById(R.id.image1);

        //  image1 = (ImageView) findViewById(R.id.image1);

//        Picasso.with(Show_1_Detail.this)
//                //.load(""+MyArrList.get(position).get("product_img"))
//                .load(""+ s_img)
//                // .placeholder(R.drawable.placeholder)   // optional
//                //.error(R.drawable.error)      // optional
//                //.resize(400,400)                        // optional
//                .into(image1);



    }
}