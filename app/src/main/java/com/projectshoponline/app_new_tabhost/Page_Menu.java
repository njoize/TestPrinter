package com.projectshoponline.app_new_tabhost;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

//import static com.appnewabc.app_oldermore.Page_Login.Data_code_color;
//import static com.appnewabc.app_oldermore.Page_Login.Data_code_font;


public class Page_Menu extends AppCompatActivity {

    SharedPreferences sharedPref;

    public static final String mypreference = "Info";
    public static final String Name_code_color = "data_code_color";
    public static final String Name_code_font= "data_code_font";

    private static TextView tv_data_code_color;
    private static TextView tv_data_code_font;

    LinearLayout background_1;

    String strMemberID = "";
    String strMemberName = "";
    String strMemberPass = "";


    Button b_Emergency;
     Button btnEmerCall;

    String New_String_phone = "9999999999" ;


    TextView tv_data_font;
    TextView tv_show_font;
    private String Data_code_color;
    private String Data_code_font;

    TextView tMemberID;
    TextView tUsername;
    TextView tUserpass;

    TextView tMemberID_2;
    TextView tUsername_2;
    TextView tUserpass_2;


    Button b_next_1;
    Button b_confirm;

    private MyHttpPoster poster;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_menu);
        ////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ////////////////
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
            Intent newActivity = new Intent(Page_Menu.this, MainActivity.class);
            startActivity(newActivity);
        }

        //*** Get Member ID from Session
        strMemberID = usrHelper.getMemberID();
        strMemberName = usrHelper.getMemberName();
        strMemberPass = usrHelper.getMemberPass();
        //*** Show User Info
        showUserLoginInfo();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b_confirm.performClick();
            }
        }, 1000);



        //Button
        b_next_1 = (Button) findViewById(R.id.b_next_1);
        b_next_1.setOnClickListener (new View.OnClickListener() {

            public void onClick(View V) {
                Intent intent =  new Intent(V.getContext(), TabHostMENU.class);
                startActivityForResult(intent, 0);
            }

        });//Button




     //   sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
////////////////////////////////////////////////////////////////////////////////////////////////////////

        tMemberID = (TextView)findViewById(R.id.txtMemberID);
        tUsername = (TextView)findViewById(R.id.txtUsername);
        tUserpass = (TextView)findViewById(R.id.txtUserpass);


        tMemberID_2 = (TextView)findViewById(R.id.txtMemberID_2);
        tMemberID_2.setText(""+strMemberID);
        tUsername_2 = (TextView)findViewById(R.id.txtUsername_2);
        tUsername_2.setText(""+strMemberName);
        tUserpass_2 = (TextView)findViewById(R.id.txtUserpass_2);
        tUserpass_2.setText(""+strMemberPass);



        /////////
        b_confirm = (Button) findViewById(R.id.b_confirm);
        b_confirm.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {




                        // poster = new MyHttpPoster("http://brainwakecafe.com/app/app_confirm.php");
                        // String url = "http://brainwakecafe.com/app/app_get_all_orders_by_group.php?group_id="+ (""+s_group_id);
                      //  poster = new MyHttpPoster("http://brainwakecafe.com/app/app_confirm.php?group_id="+ (""+New_String_group_id));


                poster = new MyHttpPoster("http://www.brainwakecafe.com/app/app_member_login.php");
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
                data.add(new BasicNameValuePair("dataname1", tMemberID.getText().toString()));
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
                                    Intent newActivity = new Intent(Page_Menu.this,TabHostMENU.class);
                                     startActivity(newActivity);

                                }

                            }



            });
                        }
        });

        //////////////////////////////////////////////////////////////////


//        background_1 = (LinearLayout) findViewById(R.id.background_1);
//        tv_data_code_color = (TextView) findViewById(R.id.tv_data_code_color);
//        tv_data_code_font = (TextView) findViewById(R.id.tv_data_code_font);

//        if (sharedPref.contains(Name_code_color)) {
//            tv_data_code_color.setText(sharedPref.getString(Name_code_color, ""));
//
//            Data_code_color = (sharedPref.getString(Name_code_color, ""));
//
//            //background_1.setBackgroundColor(Color.parseColor(""+(Name_code_color)));
//            // background_1.setBackgroundColor(Color.parseColor(""+Name_code_color));
//            //background_1.setBackgroundColor(Color.parseColor(""+NewString_data_code_color));
//            background_1.setBackgroundColor(Color.parseColor(""+Data_code_color));
//
//            Toast.makeText(getApplicationContext(), "มีค่า SharedPreferences", Toast.LENGTH_SHORT).show();
//
//            if (sharedPref.contains(Name_code_color)||sharedPref.contains(Name_code_font)) {
//
//               ///////////////////////////////////////////////////////////////////////////////////
//                tv_data_code_color.setText(sharedPref.getString(Name_code_color, ""));
//
//                Data_code_color = (sharedPref.getString(Name_code_color, ""));
//
//                //background_1.setBackgroundColor(Color.parseColor(""+(Name_code_color)));
//                // background_1.setBackgroundColor(Color.parseColor(""+Name_code_color));
//                //background_1.setBackgroundColor(Color.parseColor(""+NewString_data_code_color));
//                background_1.setBackgroundColor(Color.parseColor(""+Data_code_color));
//
//                Toast.makeText(getApplicationContext(), "มีค่า Name_code_color", Toast.LENGTH_SHORT).show();
//
//                /////////////////////////////////////////////////////////////
//
//                tv_data_code_font.setText(sharedPref.getString(Name_code_font, ""));
//
//                Data_code_font = (sharedPref.getString(Name_code_font, ""));
//
//
//                Typeface typeFace = Typeface.createFromAsset(getAssets(), "fonts/"+Data_code_font);
//
//                tv_show_font = (TextView) findViewById(R.id.tv_show_font);
//                tv_show_font.setTypeface(typeFace);
//
//                tUsername = (TextView)findViewById(R.id.txtUsername);
//                tUsername.setTypeface(typeFace);
//
//
//                Toast.makeText(getApplicationContext(), "มีค่า Name_code_font", Toast.LENGTH_SHORT).show();
//                //////////////////////////////////////////////////////////////////////////////
//        }else {
//
//
//            Toast.makeText(getApplicationContext(), "no SharedPreferences", Toast.LENGTH_SHORT).show();
//
//        }
//
//
//
//        Button b_call = (Button) findViewById(R.id.b_call);
//        b_call.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View V) {
//
//
////                AlertDialog.Builder dialog = new AlertDialog.Builder(Page_Menu.this);
////                dialog.setTitle("Alert");
////                dialog.setIcon(R.mipmap.ic_launcher);
////                dialog.setCancelable(true);
////                dialog.setMessage("Do you want to Tel?");
////                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////
////
////                        //String number = "9999999999";
////                        //Intent intent = new Intent(Intent.ACTION_CALL);
////                        //intent.setData(Uri.parse("tel:" + number));
////
////                        // String number = "0917474832";
////                        Intent intent = new Intent(Intent.ACTION_CALL);
////                        intent.setData(Uri.parse("tel:" + New_String_phone));
////
////                        if (ActivityCompat.checkSelfPermission(Page_Menu.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
////                            // TODO: Consider calling
////                            //    ActivityCompat#requestPermissions
////                            // here to request the missing permissions, and then overriding
////                            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
////                            //                                          int[] grantResults)
////                            // to handle the case where the user grants the permission. See the documentation
////                            // for ActivityCompat#requestPermissions for more details.
////                            return;
////                        }
////                        startActivity(intent);
////                    }
////                });
////
////                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
////                    public void onClick(DialogInterface dialog, int which) {
////                        dialog.cancel();
////                    }
////                });
////
////                dialog.show();
//
//               //////////////////////////////////////////////////////////////////////////
//
//
//                Intent intent = new Intent(Page_Menu.this, DialogActivity_3.class);
////                //Pass the image title and url to DetailsActivity
////                intent. putExtra("id", item.getId()).
////                        putExtra("name", item.getTitle()).
////                        putExtra("image", item.getImage());
//
////                intent.putExtra("emergency_phone_id", s_id);
////                intent.putExtra("emergency_phone_name", s_name);
////                intent.putExtra("emergency_phone_phone", s_phone);
////                intent.putExtra("emergency_phone_img", s_img);
//                startActivity(intent);
//
//
//            }
// }); /////////////// end
//

//
//
//
//        b_Emergency = (Button) findViewById(R.id.b_Emergency);
//
//
//        b_Emergency.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getBaseContext(), Page_Emergency.class);
//               // i.putExtra("MyArrList", MyArrList);
//                startActivity(i);
//            }
//        });
//
//        final Button b_Profile = (Button) findViewById(R.id.b_Profile);
//        b_Profile.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                // Intent newActivity = new Intent(Page_Menu.this,Show_Category.class);
//                Intent newActivity = new Intent(Page_Menu.this,My_Profile.class);
//                //Intent newActivity = new Intent(Page_Menu.this,Connect_1.class);
//                startActivity(newActivity);
//            }
//        });
//
////        final Button b_Page_Post = (Button) findViewById(R.id.b_Page_Post);
////        b_Page_Post.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                // Goto Activity 2
////                // Intent newActivity = new Intent(Page_Menu.this,Show_Category.class);
////                Intent newActivity = new Intent(Page_Menu.this,Page_Show_Post.class);
////                //Intent newActivity = new Intent(Page_Menu.this,Connect_1.class);
////                startActivity(newActivity);
////            }
////        });
//
//        final Button b_Page_Show_Post= (Button) findViewById(R.id.b_Page_Show_Post);
//        b_Page_Show_Post.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                // Intent newActivity = new Intent(Page_Menu.this,Show_Category.class);
//                Intent newActivity = new Intent(Page_Menu.this,Page_Show_Post.class);
//                //Intent newActivity = new Intent(Page_Menu.this,Connect_1.class);
//                startActivity(newActivity);
//            }
//        });
//
//
//        final Button b_Page_Show_Subject= (Button) findViewById(R.id.b_Page_Show_Subject);
//        b_Page_Show_Subject.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                // Intent newActivity = new Intent(Page_Menu.this,Show_Category.class);
//                Intent newActivity = new Intent(Page_Menu.this,Page_Show_Subject.class);
//                //Intent newActivity = new Intent(Page_Menu.this,Connect_1.class);
//                startActivity(newActivity);
//            }
//        });
//
//
//        final Button b_Page_Setting = (Button) findViewById(R.id.b_Page_Setting);
//        b_Page_Setting.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                // Intent newActivity = new Intent(Page_Menu.this,Show_Category.class);
//                Intent newActivity = new Intent(Page_Menu.this,Page_Setting.class);
//                //Intent newActivity = new Intent(Page_Menu.this,Connect_1.class);
//                startActivity(newActivity);
//            }
//        });
//
////        //*** Button Logout
////        final Button btnLogout = (Button) findViewById(R.id.btnLogout);
////        btnLogout.setOnClickListener(new View.OnClickListener() {
////            public void onClick(View v) {
////                // Clear Session
////                usrHelper.deleteSession();
////
////                // Goto Acitity2
////                Intent newActivity = new Intent(Page_Menu.this,Page_Login.class);
////                startActivity(newActivity);
////            }
////        });



        //*** Button Logout
        Button btnLogout = (Button) findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                AlertDialog.Builder dialog = new AlertDialog.Builder(Page_Menu.this);
                dialog.setTitle("แจ้งเตือน");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(true);
                dialog.setMessage("คุณต้องการออกจากระบบใช่หรือไม่?");
                dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent intent = new Intent(Intent.ACTION_MAIN);
                       // intent.addCategory(Intent.CATEGORY_HOME);
                        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                       // startActivity(intent);

                        ///// Clear Session
                usrHelper.deleteSession();

                // Goto Acitity2
                Intent newActivity = new Intent(Page_Menu.this,Page_Login.class);
                startActivity(newActivity);


                    }
                });

                dialog.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }




        }); /////////////// exitApp

    }

    public String md5(String s) {
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuffer hexString = new StringBuffer();
            for (int i=0; i<messageDigest.length; i++)
                hexString.append(Integer.toHexString(0xFF & messageDigest[i]));
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

//        /////////////// exitApp
//        Button btnAppExit = (Button) findViewById(R.id.exitApp);
//        btnAppExit.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//
//                AlertDialog.Builder dialog = new AlertDialog.Builder(Page_Menu.this);
//                dialog.setTitle("Exit");
//                dialog.setIcon(R.mipmap.ic_launcher);
//                dialog.setCancelable(true);
//                dialog.setMessage("Do you want to exit?");
//                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(Intent.ACTION_MAIN);
//                        intent.addCategory(Intent.CATEGORY_HOME);
//                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        startActivity(intent);
//                    }
//                });
//
//                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.cancel();
//                    }
//                });
//
//                dialog.show();
//            }
//
//
//
//
//        }); /////////////// exitApp
//
//    }
//    /////////////// onBackPressed








    public void showUserLoginInfo()
    {
        // txtMemberID,txtMemberID,txtUsername,txtPassword,txtName,txtEmail,txtTel
        final TextView tMemberID = (TextView)findViewById(R.id.txtMemberID);
        final TextView tUsername = (TextView)findViewById(R.id.txtUsername);
        final TextView tUserpass = (TextView)findViewById(R.id.txtUserpass);
      // final TextView tStatus= (TextView)findViewById(R.id.txtStatus);
//        final TextView tAddress= (TextView)findViewById(R.id.txtAddress);
//        final TextView tEmail = (TextView)findViewById(R.id.txtEmail);
//        final TextView tTel = (TextView)findViewById(R.id.txtTel);




       // String url = "http://projectshoponline.com/older/app_get_id.php";
        String url = "http://www.brainwakecafe.com/app/app_login_get_id.php";


        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("sMemberID", strMemberID));
        params.add(new BasicNameValuePair("sUsername", strMemberName));
        params.add(new BasicNameValuePair("sUserpass", strMemberPass));

        /** Get result from Server (Return the JSON Code)
         *
         * {"MemberID":"2","Username":"aa","Password":"aa","Name":"aa","Tel":"1234","Email":"aaa"}
         */

        String resultServer  = MyHttp.getHttpPost(url,params);

        String strMemberID = "";
        String strMemberName = "";
        String strMemberPass = "";
       // String strStatus = "";
//        String strAdress = "";
//        String strEmail = "";
//        String strTel = "";

        JSONObject c;
        try {
            c = new JSONObject(resultServer);
            strMemberID = c.getString("MemberID");
            strMemberName = c.getString("MemberName");
            strMemberPass = c.getString("MemberPass");
            //strStatus = c.getString("Status");
//            strAdress = c.getString("Address");
//            strEmail = c.getString("Email");
//            strTel = c.getString("Tel");

            if(!strMemberID.equals(""))
            {
                tMemberID.setText(strMemberID);
                tUsername.setText(strMemberName);
                tUserpass.setText(strMemberPass);
                 //tStatus.setText(strStatus);
//                tAddress.setText(strAdress);
//                tEmail.setText(strEmail);
//                tTel.setText(strTel);
            }
            else
            {
                tMemberID.setText("-");
                tUsername.setText("-");
                tUserpass.setText("-");
//                tAddress.setText("-");
//                tEmail.setText("-");
//                tTel.setText("-");
            }

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


            public void onBackPressed() {
                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                dialog.setTitle("แจ้งเตือน");
                dialog.setIcon(R.mipmap.ic_launcher);
                dialog.setCancelable(true);
                dialog.setMessage("คุณต้องการที่จะออกจากแอพใช่หรือไม่?");
                dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Intent.ACTION_MAIN);
                        intent.addCategory(Intent.CATEGORY_HOME);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                dialog.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }  /////////////// onBackPressed


        }


