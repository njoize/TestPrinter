package com.projectshoponline.app_new_tabhost;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Page_Login extends AppCompatActivity {

    TextView lblFogotPassword;

    SharedPreferences sharedPref;

    public static final String mypreference = "Info";
    public static final String Name_code_color = "data_code_color";

    private static TextView tv_data_code_color;
    LinearLayout background_1;


    public static String Data_code_color = "NewString_data_code_color";


    EditText txtUser;
    EditText txtPass;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        // Permission StrictMode ==> Try to Use http://, https://
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }


        // Create File wml Info.xml package Value "" at key s_name
        SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);

        // Read Value from Info.xml at key s_name defaule ""
        String data_name = sharedPref.getString("s_name", "");


        // Iniaial View
        CheckBox chk = (CheckBox) findViewById(R.id.chk1);
        chk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                // Check which checkbox was clicked
                if (checked){
                    // Do your coding

                   // Toast.makeText(Page_Login.this, "OK1", Toast.LENGTH_SHORT).show();

                    if (Save_SharedPreferences())
                    {
                        // When action Complete
                    }


                }
                else{
                    // Do your coding

                    if (Clear_SharedPreferences())
                    {
                        // When action Complete
                    }


                   // Toast.makeText(Page_Login.this, "OK2", Toast.LENGTH_SHORT).show();
                }
            }
        });
//        sharedPref = getSharedPreferences(mypreference, Context.MODE_PRIVATE);
//
//        background_1 = (LinearLayout) findViewById(R.id.background_1);
//        //background_1.setVisibility(View.GONE);
//        tv_data_code_color = (TextView) findViewById(R.id.tv_data_code_color);
//
//        if (sharedPref.contains(Name_code_color)) {
//            tv_data_code_color.setText(sharedPref.getString(Name_code_color, ""));
//
//            Data_code_color = (sharedPref.getString(Name_code_color, ""));
//
//            //background_1.setBackgroundColor(Color.parseColor(""+(Name_code_color)));
//            // background_1.setBackgroundColor(Color.parseColor(""+Name_code_color));
//            //background_1.setBackgroundColor(Color.parseColor(""+NewString_data_code_color));
//          //  background_1.setBackgroundColor(Color.parseColor("" + Data_code_color));
//            Toast.makeText(getApplicationContext(), "มีค่า SharedPreferences", Toast.LENGTH_SHORT).show();
//
//        } else {
//
//
//            Toast.makeText(getApplicationContext(), "no SharedPreferences", Toast.LENGTH_SHORT).show();
//
//        }

        //*** Session Login
        final UserHelper usrHelper = new UserHelper(this);

       // lblFogotPassword = (TextView) findViewById(R.id.lblFogotPassword);
      //  lblFogotPassword.setOnClickListener(new View.OnClickListener() {
       //     @Override
      //      public void onClick(View v) {
      //          // DialogFogotPassword();
      //      }
      //  });

//        //*** Button Next
//        final Button btnNext = (Button) findViewById(R.id.btnNext);
//        btnNext.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                // Goto Activity 2
//                Intent newActivity = new Intent(Page_Login.this, Page_Register.class);
//                startActivity(newActivity);
//            }
//        });


        //*** txtUsername & txtPassword
        txtUser = (EditText) findViewById(R.id.txtUsername);
        txtUser.setText(""+data_name);
        txtPass = (EditText) findViewById(R.id.txtPassword);

        //*** Alert Dialog
        final AlertDialog.Builder ad = new AlertDialog.Builder(this);

        //*** Login Button
        final Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                //String url = "http://projectshoponline.com/older/app_login.php";
                String url = "http://www.brainwakecafe.com/app/app_login.php";


                List<NameValuePair> params = new ArrayList<NameValuePair>();
                params.add(new BasicNameValuePair("strUser", txtUser.getText().toString()));
                params.add(new BasicNameValuePair("strPass", txtPass.getText().toString()));

                /** Get result from Server (Return the JSON Code)
                 * StatusID = ? [0=Failed,1=Complete]
                 * MemberID = ? [Eg : 1]
                 * Error	= ?	[On case error return custom error message]
                 *
                 * Eg Login Failed = {"StatusID":"0","MemberID":"0","Error":"Incorrect Username and Password"}
                 * Eg Login Complete = {"StatusID":"1","MemberID":"2","Error":""}
                 */

                String resultServer = MyHttp.getHttpPost(url, params);

                Log.d("24novV1","resultServer ==> " + resultServer);
//                Bad  Authen ==> resultServer ==> {"StatusID":"0","MemberID":"0","Username":"0","Error":"Incorrect Username and Password"}
//                Good Authen ==> resultServer ==> {"StatusID":"1","MemberID":"1","Username":null,"Error":"Complete"}

                /*** Default Value ***/
                String strStatusID = "0";
                String strMemberID = "0";
                String strMemberName = "0";
                String strMemberPass = "0";
                String strError = "Unknow Status!";

                JSONObject c;
                try {
                    c = new JSONObject(resultServer);
                    strStatusID = c.getString("StatusID");
                    strMemberID = c.getString("MemberID");
                    strMemberName = c.getString("MemberName");
                    strMemberPass = c.getString("MemberPass");
                    strError = c.getString("Error");

                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                // Prepare Login
                if (strStatusID.equals("0")) {
                    // Dialog
                    ad.setTitle("Error! ");
                    ad.setIcon(android.R.drawable.btn_star_big_on);
                    ad.setPositiveButton("Close", null);
                    ad.setMessage(strError);
                    ad.show();
                    txtUser.setText("");
                    txtPass.setText("");
                } else {
                    Toast.makeText(Page_Login.this, "Login OK", Toast.LENGTH_SHORT).show();

                    // Create Session
                    usrHelper.createSession(strMemberID, strMemberName, strMemberPass);

//                    if (Save_SharedPreferences())
//                    {
//                        // When action Complete
//                    }

                    // Goto Activity2
                    Intent newActivity = new Intent(Page_Login.this, Page_Menu.class);
                    startActivity(newActivity);

                }

            }
        });

    }   // Main Method

//    private void DialogFogotPassword() {
//        View dialogBoxView = View.inflate(this, R.layout.dialog_get_password, null);
//        final Button btnGetPassword = (Button) dialogBoxView.findViewById(R.id.btnGetPassword);
//        final EditText txtUsername = (EditText) dialogBoxView.findViewById(R.id.txtUsername);
//
//        btnGetPassword.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String strPassword = "";
//                String status = "0";
//                String error = "";
//                String url = getString(R.string.url) + "getPassword.php";
//                // Paste Parameters
//                List<NameValuePair> params = new ArrayList<NameValuePair>();
//                params.add(new BasicNameValuePair("username", txtUsername.getText()
//                        .toString().trim()));
//                try {
//                    JSONArray data = new JSONArray(http.getJSONUrl(url, params));
//                    if (data.length() > 0) {
//                        JSONObject c = data.getJSONObject(0);
//                        status = c.getString("status");
//                        error = c.getString("error");
//                        if ("1".equals(status)) {
//                            strPassword = c.getString("password");
//                        }
//                    }
//                    if ("1".equals(status)) {
//                        MessageDialog("รหัสผ่าน : "+ strPassword);
//                    } else {
//                        MessageDialog(error);
//                    }
//                } catch (JSONException e) {
//                    // TODO Auto-generated catch block
//                    e.printStackTrace();
//                    MessageDialog(e.getMessage());
//                }
//
//            }
//        });
//
//        AlertDialog.Builder builderInOut = new AlertDialog.Builder(this);
//        builderInOut.setTitle("ลืมรหัสผ่าน");
//        builderInOut.setMessage("")
//                .setView(dialogBoxView)
//                .setCancelable(false)
//                /*         .setPositiveButton("ตกลง", new DialogInterface.OnClickListener() {
//                             public void onClick(DialogInterface dialog, int id) {
//
//                             }
//                         })*/
//                .setNegativeButton("ปิด",
//                        new DialogInterface.OnClickListener() {
//                            public void onClick(DialogInterface dialog, int id) {
//                                dialog.cancel();
//                            }
//                        }).show();
//    }


    //    @Override
//    public void onBackPressed(){
//        super.onBackPressed();
////        Intent i=new Intent(Intent.ACTION_MAIN);
////        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////        finish();
//        Intent intent = new Intent(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_HOME);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//        startActivity(intent);
//        //Goto Activity2
//        //Intent newActivity = new Intent(Page_Login.this,Page_Menu.class);
//        //startActivity(newActivity);
//    }
//    /////////////// onBackPressed
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

    ///////////////////////////////////////////
    public boolean Save_SharedPreferences() {

        SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("data_font", tv_new_font.getText().toString());
        //editor.putString("data_code_color", Name_code_color);

        editor.putString("s_name", txtUser.getText().toString());
        editor.apply();

        Toast.makeText(this, "Save!", Toast.LENGTH_LONG).show();


        return true;
    }
    public boolean Clear_SharedPreferences() {

        SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);

        sharedPref.edit().remove("s_name").commit();
       // Toast.makeText(this, "clear!", Toast.LENGTH_LONG).show();


        return true;
    }
}