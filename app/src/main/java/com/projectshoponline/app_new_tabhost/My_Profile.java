package com.projectshoponline.app_new_tabhost;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class My_Profile extends AppCompatActivity {

    String strMemberID = "";

    String New_String_id;
    String New_String_name;

    private MyHttpPoster poster;
    ////////////////////////////////////////


    EditText tName;
    EditText tTel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_profile);

        ////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ////////////////



        //รับค่าส่งมาจากจากหน้า page_showdata2 เพื่อมาแสดง
//        tvView1 = (TextView) findViewById(R.id.tvView1);
//
//
//        final Intent intent = getIntent();
//        String sProductID = intent.getStringExtra("ProductID");
//
//
//        tvView1.setText("" + sProductID);

        //*** Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        //*** Get Session Login
        final UserHelper usrHelper = new UserHelper(this);

        //*** Get Login Status
        if(!usrHelper.getLoginStatus())
        {
            Intent newActivity = new Intent(My_Profile.this, MainActivity.class);
            startActivity(newActivity);
        }

        //*** Get Member ID from Session
        strMemberID = usrHelper.getMemberID();


        ////////////////

//        final Button b_back = (Button) findViewById(R.id.b_back);
//        b_back.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                // Goto Acitity2
//                Intent newActivity = new Intent(My_Profile.this,Page_Menu.class);
//                startActivity(newActivity);
//            }
//        });



        final Button btnLogout = (Button) findViewById(R.id.Logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Clear Session
                usrHelper.deleteSession();

                // Goto Acitity2
                Intent newActivity = new Intent(My_Profile.this,Page_Login.class);
                startActivity(newActivity);
            }
        });



        /////////
        Button b_confirm = (Button) findViewById(R.id.b_confirm);
        b_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                // Clear Session
                usrHelper.deleteSession();

                // poster = new MyHttpPoster("http://brainwakecafe.com/app/app_confirm.php");
                // String url = "http://brainwakecafe.com/app/app_get_all_orders_by_group.php?group_id="+ (""+s_group_id);
                //  poster = new MyHttpPoster("http://brainwakecafe.com/app/app_confirm.php?group_id="+ (""+New_String_group_id));


                poster = new MyHttpPoster("http://www.brainwakecafe.com/app/app_member_logout.php");
                //poster = new MyHttpPoster("http://www.brainwakecafe.com/app/app_member_login.php?id="+strMemberID);
                ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();


                //data.add(new BasicNameValuePair("dataname1", strMemberID));
                //data.add(new BasicNameValuePair("dataname2", strMemberName));
                //data.add(new BasicNameValuePair("dataname3", strMemberPass));
//                        data.add(new BasicNameValuePair("dataname3", New_String_product_name));
//                        data.add(new BasicNameValuePair("dataname4", New_String_product_price));
//                        data.add(new BasicNameValuePair("dataname5", New_String_product_amount));
//                        data.add(new BasicNameValuePair("dataname6", textView_Product_price_total.getText().toString()));
//
                //data.add(new BasicNameValuePair("dataname1", tMemberID.getText().toString()));
                //data.add(new BasicNameValuePair("dataname2", tUsername.getText().toString()));
                //data.add(new BasicNameValuePair("dataname3", tUserpass.getText().toString()));
                // data.add(new BasicNameValuePair("dataname3", md5(tUserpass.getText().toString())));


//                        data.add(new BasicNameValuePair("dataname7", New_String_product_img));


                poster.doPost(data, new Handler() {
                    public void handleMessage(android.os.Message msg) {
                        switch (msg.what) {
                            case MyHttpPoster.HTTP_POST_OK:

                                String resultValue = (String) msg.obj;
                                //result.setText(resultValue);
                                finish();
                                // Goto Activity2
                                Intent newActivity = new Intent(My_Profile.this,Page_Login.class);
                                startActivity(newActivity);

                                Toast.makeText(My_Profile.this, "OK", Toast.LENGTH_SHORT).show();


                        }

                    }



                });
            }
        });





        tName = (EditText)findViewById(R.id.tName);
        tTel = (EditText)findViewById(R.id.tTel);


///////////////////////////////////////////
        Button b_action1 = (Button) findViewById(R.id.b_action1);
        b_action1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(My_Profile.this);
                dialog.setTitle("confirm");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(true);
                dialog.setMessage("Do you want to confirm ?");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        Toast.makeText(getBaseContext(), "OK! ", Toast.LENGTH_SHORT).show();


                        //getting the tag from the edittext
                        // final String place_type_id = et_place_name.getText().toString().trim();
                        // final String place_name = et_place_name.getText().toString().trim();
                        // final String detail = et_detail.getText().toString().trim();

                        //final Intent intent = getIntent();
                       // final String s_id = intent.getStringExtra("text_1");


                        // poster = new MyHttpPoster("http://projectshoponline.com/yore/app_update_1.php?id="+s_id);
                        //poster = new MyHttpPoster("http://projectshoponline.com/yore/app_edit_1.php?id=" + s_id);
                       // poster = new MyHttpPoster("http://projectshoponline.com/tae/app_edit_1.php?id=" + strMemberID);
                        poster = new MyHttpPoster("http://projectshoponline.com/chat/app_edit_profile.php?id=" + strMemberID);

                        ArrayList<NameValuePair> data = new ArrayList<NameValuePair>();


                        //data.add(new BasicNameValuePair("dataname1", New_String_id));
                        data.add(new BasicNameValuePair("dataname1", tName.getText().toString().trim()));
                        data.add(new BasicNameValuePair("dataname2", tTel.getText().toString().trim()));
                       // data.add(new BasicNameValuePair("dataname3", tTel.getText().toString().trim()));
                        //data.add(new BasicNameValuePair("dataname4", text_project_detail.getText().toString().trim()));
                       // data.add(new BasicNameValuePair("dataname5", text_project_time_start.getText().toString().trim()));
                       // data.add(new BasicNameValuePair("dataname6", text_project_time_end.getText().toString().trim()));

                        // data.add(new BasicNameValuePair("dataname_user_id", strMemberID));


                        poster.doPost(data, new Handler() {
                            public void handleMessage(android.os.Message msg) {
                                switch (msg.what) {
                                    case MyHttpPoster.HTTP_POST_OK:

                                        String resultValue = (String) msg.obj;
                                        //result.setText(resultValue);
//                                        finish();


                                }
                            }


                        });


                    }

                });


                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                        Toast.makeText(getBaseContext(), "cancel", Toast.LENGTH_SHORT).show();

                    }
                });

                dialog.show();
            }


        });


        //////////////////////////////////////////////////////////////////





        //*** Show User Info
        show_User_Profile();
    }
    public void show_User_Profile()
    {

//        final TextView tMemberID = (TextView)findViewById(R.id.txtMemberID);
//        final TextView tUsername = (TextView)findViewById(R.id.txtUsername);
//        final TextView tPassword = (TextView)findViewById(R.id.txtPasssword);
//        final TextView tAddress = (TextView)findViewById(R.id.txtAddress);
//        final TextView tEmail = (TextView)findViewById(R.id.txtEmail);
//        final TextView tTel = (TextView)findViewById(R.id.txtTel);
//        final TextView tPicture = (TextView)findViewById(R.id.txtPicture);


        final EditText tMemberID = (EditText)findViewById(R.id.tMemberID);
        final EditText tEmail = (EditText)findViewById(R.id.tEmail);
        tEmail.setEnabled(false);
        final EditText tPassword = (EditText)findViewById(R.id.tPassword);
        tPassword.setEnabled(false);
        final EditText tName = (EditText)findViewById(R.id.tName);
        final EditText tTel = (EditText)findViewById(R.id.tTel);
        //final EditText tPicture = (EditText)findViewById(R.id.txtPicture);
        tMemberID.setText(strMemberID);


        //String url = "http://projectappnew.com/web2/app/get_MemberID.php";
        String url = "http://www.brainwakecafe.com/app/app_my_profile.php?id="+ strMemberID;

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sMemberID", strMemberID));


        String resultServer  = MyHttp.getHttpPost(url,params);

        String strMemberID = "";
       // String strEmail = "";
       // String strPassword = "";
        String strName = "";
        //String strTel = "";
       // String strPicture = "";
        JSONObject c;
        try {
            c = new JSONObject(resultServer);
//            strMemberID = c.getString("user_id");
//            strUsername = c.getString("username");
//            strPassword = c.getString("password");
//            strAddress = c.getString("address");
//            strEmail = c.getString("email");
//            strTel = c.getString("tel");
//            strPicture = c.getString("user_img");
            strMemberID = c.getString("id");
           // strEmail = c.getString("email");
            //strPassword = c.getString("password");
            strName = c.getString("name");
           // strTel = c.getString("tel");



            // ?????????????????????????????????//
            // NEW STRING ###################################//


            //  New_String_id = c.getString("MemberID");
            // New_String_name = c.getString("Username");



            // NEW STRING ###################################//
            // ?????????????????????????????????//


            //ImageView Picture = (ImageView)findViewById(R.id.picture);
 //           ImageView imgview1 = (ImageView)findViewById(R.id.picture);
            //  rounded  วงกลม
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.id.image1);
//            RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(getResources(),bitmap);
//            roundedBitmapDrawable.setCircular(true);
//            imgview1.setImageDrawable(roundedBitmapDrawable);

            //mChart2.setImageBitmap(new DownloadImagesTask().execute(graph_URL_2));
            // new DownloadImageTask(Picture).execute(strPicture);
//            Picasso.with(My_Profile.this)
//                    //.load(""+MyArrList.get(position).get("employee_img"))
//                    .load(""+strPicture)
//                    // .placeholder(R.drawable.placeholder)   // optional
//                    //.error(R.drawable.error)      // optional
//                    //.resize(400,400)                        // optional
//                    .into(imgview1);

            if(!strMemberID.equals(""))
            {
                tMemberID.setText(strMemberID);
               // tEmail.setText(strEmail);
               // tPassword.setText(strPassword);
                tName.setText(strName);
               // tTel.setText(strTel);
              //  tPicture.setText(strPicture);
            }
            else
            {
                tMemberID.setText("-");
               // tEmail.setText("-");
               // tPassword.setText("-");
                tName.setText("-");
               // tTel.setText("-");
               // tPicture.setText("-");
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }

}


