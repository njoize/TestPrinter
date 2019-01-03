package com.projectshoponline.app_new_tabhost;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import java.io.IOException;

public class Show_members_textview_1 extends AppCompatActivity {

    HttpResponse httpResponse;
    Button button;
    TextView text_id;
    TextView text_name;
    JSONObject jsonObject = null ;
    String StringHolder = "" ;
    ProgressBar progressBar;
    // Adding HTTP Server URL to string variable.
    String HttpURL = "http://brainwakecafe.com/app/app_show_members_textview_1.php";


    Button b_save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_members_textview_1);

        //////////////////
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ////////////////

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                b_save.performClick();
            }
        }, 2000);

        // Assigning ID's to button, textView and progressbar.
        button = (Button)findViewById(R.id.button1);
        text_id = (TextView)findViewById(R.id.text_id);
        text_name = (TextView)findViewById(R.id.text_name);
        //progressBar = (ProgressBar)findViewById(R.id.progressBar1);

        new GetDataFromServerIntoTextView(Show_members_textview_1.this).execute();



        b_save= (Button) findViewById(R.id.b_save);
        b_save.setOnClickListener (new View.OnClickListener() {

            public void onClick(View  V) {

                if (Save_SharedPreferences())
                {
                    // When action Complete
                }

                //Toast.makeText(getApplicationContext(), "Save_SharedPreferences", Toast.LENGTH_SHORT).show();

            // Intent intent =  new Intent(V.getContext(), Page_get_member.class);
               Intent intent =  new Intent(V.getContext(), TabHostMENU.class);
               startActivityForResult(intent, 0);
            }

        });//Button




        // Adding click lister to button.
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Showing progress bar on button click.
               // progressBar.setVisibility(View.VISIBLE);

                //Calling GetDataFromServerIntoTextView method to Set JSon MySQL data into TextView.
                new GetDataFromServerIntoTextView(Show_members_textview_1.this).execute();

            }
        });
    }

    // Declaring GetDataFromServerIntoTextView method with AsyncTask.
    public class GetDataFromServerIntoTextView extends AsyncTask<Void, Void, Void>
    {
        // Declaring CONTEXT.
        public Context context;


        public GetDataFromServerIntoTextView(Context context)
        {
            this.context = context;
        }

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {

            HttpClient httpClient = new DefaultHttpClient();

            // Adding HttpURL to my HttpPost oject.
            HttpPost httpPost = new HttpPost(HttpURL);

            try {
                httpResponse = httpClient.execute(httpPost);

                StringHolder = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            try{
                // Passing string holder variable to JSONArray.
                JSONArray jsonArray = new JSONArray(StringHolder);
                jsonObject = jsonArray.getJSONObject(0);


            } catch ( JSONException e) {
                e.printStackTrace();
            }

            catch (Exception e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }
        protected void onPostExecute(Void result)
        {
            try {

                // Adding JSOn string to textview after done loading.
                text_id.setText(jsonObject.getString("id"));
                text_name.setText(jsonObject.getString("name"));

            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //Hiding progress bar after done loading TextView.
           // progressBar.setVisibility(View.GONE);

        }
    }

    ///////////////////////////////////////////
    public boolean Save_SharedPreferences() {

        SharedPreferences sharedPref = getSharedPreferences("Info", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        //editor.putString("data_font", tv_new_font.getText().toString());
        //editor.putString("data_code_color", Name_code_color);

        editor.putString("s_id", text_id.getText().toString());
        editor.putString("s_name", text_name.getText().toString());
        editor.apply();

        Toast.makeText(this, "Save!", Toast.LENGTH_LONG).show();


        return true;
    }

}