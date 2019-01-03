package com.projectshoponline.app_new_tabhost;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by mdoublee on 1/12/2018 AD.
 */

public class Show_0 extends AppCompatActivity {


    TextView tvView1;

    Button b_menu;

    ArrayList<HashMap<String, String>> MyArrList;

    ListView listView_1;

    ImageView imgview1;

    Button btnSearch_1;

    EditText strKeySearch_1;

    Button b_add;
    Button b_back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_0);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

//        b_add = (Button) findViewById(R.id.b_add);
//        b_add.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View V) {
//                Intent intent = new Intent(V.getContext(), Page_Add_Post.class);
//                startActivityForResult(intent, 0);
//            }
//
//        });
//
//        b_back = (Button) findViewById(R.id.b_back);
//        b_back.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View V) {
//                Intent intent = new Intent(V.getContext(), Page_Menu.class);
//                startActivityForResult(intent, 0);
//            }
//
//        });



//        Button b_MainActivity = (Button) findViewById(R.id.b_MainActivity);
//        b_MainActivity.setOnClickListener(new View.OnClickListener() {
//
//            public void onClick(View V) {
//                Intent intent = new Intent(V.getContext(), MainActivity.class);
//                startActivityForResult(intent, 0);
//            }
//
//        });


        // tvView1 = (TextView) findViewById(R.id.tvView1);

// final Intent intent = getIntent();
//        String sMedicine_ID = intent.getStringExtra("text_1");
//
//
//
//        tvView1.setText("" + sMedicine_ID);




        // Permission StrictMode
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        ShowData();


        // btnSearch
        btnSearch_1 = (Button) findViewById(R.id.btnSearch_1);
        //btnSearch.setBackgroundColor(Color.TRANSPARENT);
        // Perform action on click
        btnSearch_1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ShowData();
            }
        });
    }
    //แสดงข้อมูล
    public void ShowData() {

        listView_1 = (ListView) findViewById(R.id.listView_1);

        strKeySearch_1 = (EditText) findViewById(R.id.txtKeySearch_1);

        // Disbled Keyboard auto focus
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(strKeySearch_1.getWindowToken(), 0);



        String url = "http://brainwakecafe.com/app/app_show_pr_orders_and_pr_orders_list_id_0.php";
        // Paste Parameters
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("txtKeyword", strKeySearch_1.getText().toString()));
        try {
            JSONArray data = new JSONArray(getJSONUrl(url, params));

            MyArrList = new ArrayList<HashMap<String, String>>();
            HashMap<String, String> map;

            for (int i = 0; i < data.length(); i++) {
                JSONObject c = data.getJSONObject(i);

                map = new HashMap<String, String>();
                map.put("id", c.getString("id"));
                map.put("TotalList", c.getString("TotalList"));
                map.put("TotalPrice", c.getString("TotalPrice"));
                map.put("name", c.getString("name"));
                map.put("date", c.getString("date"));
                map.put("type", c.getString("type"));
                map.put("tname", c.getString("tname"));
                map.put("tzname", c.getString("tzname"));
                map.put("cnum", c.getString("cnum"));
//                map.put("price", c.getString("price"));
//                map.put("post_img", c.getString("pic"));
//                map.put("Elder_Eat_Medicine_ID", c.getString("Elder_Eat_Medicine_ID"));
//                map.put("Elder_Eat_Med_Type_ID", c.getString("Elder_Eat_Med_Type_ID"));
//                map.put("Elder_Eat_Med_ID", c.getString("Elder_Eat_Med_ID"));

                MyArrList.add(map);

            }
            listView_1.setAdapter(new ImageAdapter(this));

            // OnClick Item
            listView_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView,
                                        int position, long mylng) {

                    String s_id = MyArrList.get(position).get("id")
                            .toString();

                    String s_type = MyArrList.get(position).get("type")
                            .toString();

                    String s_tname = MyArrList.get(position).get("tname")
                            .toString();

                    String s_tzname = MyArrList.get(position).get("tzname")
                            .toString();

                    String s_cnum = MyArrList.get(position).get("cnum")
                            .toString();

                    String s_TotalPrice = MyArrList.get(position).get("TotalPrice")
                            .toString();
                    Intent newActivity = new Intent(Show_0.this, Detail.class);
                    newActivity.putExtra("id", s_id);
                    newActivity.putExtra("type", s_type);
                    newActivity.putExtra("tname", s_tname);
                    newActivity.putExtra("tzname", s_tzname);
                    newActivity.putExtra("cnum", s_cnum);
                    newActivity.putExtra("TotalPrice", s_TotalPrice);
                    startActivity(newActivity);

                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public class ImageAdapter extends BaseAdapter {
        private Context context;

        public ImageAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return MyArrList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {

            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.show_0_column, null);
            }
            // R.id.
            TextView txt_id = (TextView) convertView.findViewById(R.id.txt_id);
            txt_id.setPadding(10, 0, 0, 0);
            txt_id.setText(MyArrList.get(position).get("id") + ".");

            // R.id.
            //TextView txtCatID = (TextView) convertView.findViewById(R.id.CatID);
            // txtCatID.setPadding(5, 0, 0, 0);
            //txtCatID.setText(MyArrList.get(position).get("CatID") + ".");

//            // R.id.
//            TextView txt1 = (TextView) convertView.findViewById(R.id.txt1);
//            txt1.setPadding(5, 0, 0, 0);
//            txt1.setText(MyArrList.get(position).get("product_code") + ".");
//
//            // R.id.
//            TextView txt2 = (TextView) convertView.findViewById(R.id.txt2);
//            txt2.setPadding(5, 0, 0, 0);
//            txt2.setText(MyArrList.get(position).get("product_herd") + ".");

            // R.id.
            TextView txt1 = (TextView) convertView.findViewById(R.id.txt1);
            txt1.setPadding(5, 0, 0, 0);
            txt1.setText("โดย " + MyArrList.get(position).get("name") + "");

            // R.id.
            TextView txt2 = (TextView) convertView.findViewById(R.id.txt2);
            txt2.setPadding(5, 0, 0, 0);
            txt2.setText(MyArrList.get(position).get("TotalPrice") + " บาท");
            //      R.id.

            //R.id.
            TextView txt3 = (TextView) convertView.findViewById(R.id.txt3);
            txt3.setPadding(5, 0, 0, 0);
            txt3.setText("สินค้า " + MyArrList.get(position).get("TotalList") + " รายการ");
//              R.id.

            //R.id.
            TextView txt4 = (TextView) convertView.findViewById(R.id.txt4);
            txt4.setPadding(5, 0, 0, 0);
            txt4.setText(MyArrList.get(position).get("date"));
//              R.id.

            //R.id.
            TextView txt5 = (TextView) convertView.findViewById(R.id.txt5);
            txt5.setPadding(5, 0, 0, 0);
            txt5.setText(MyArrList.get(position).get("type"));
//              R.id.
//
            //R.id.
            TextView txt6 = (TextView) convertView.findViewById(R.id.txt6);
            txt6.setPadding(5, 0, 0, 0);
            txt6.setText("ลูกค้า " + MyArrList.get(position).get("cnum") + " คน");
////              R.id.

            //TextView txt4 = (TextView) convertView.findViewById(R.id.txt4);
            //txt4.setPadding(5, 0, 0, 0);
            //txt4.setText(MyArrList.get(position).get("Medicine_Img"));
//            // R.id.
//            TextView txtimage2 = (TextView) convertView.findViewById(R.id.product_img_name2);
//            txtimage2.setPadding(5, 0, 0, 0);
//            txtimage2.setText(MyArrList.get(position).get("product_img_name2"));
//            // R.id.
//            TextView txtimage3 = (TextView) convertView.findViewById(R.id.product_img_name3);
//            txtimage3.setPadding(5, 0, 0, 0);
//            txtimage3.setText(MyArrList.get(position).get("product_img_name3"));


            // ImageView imgview1 = (ImageView) convertView.findViewById(R.id.image1);

            // new DownloadImageTask(imgview1).execute(MyArrList.get(position).get("travel_img"));

//            ImageView imgview2 = (ImageView) convertView.findViewById(R.id.image2);
//
//            new DownloadImageTask(imgview2).execute(MyArrList.get(position).get("product_img_name2"));
//
//            ImageView imgview3 = (ImageView) convertView.findViewById(R.id.image3);
//
//            new DownloadImageTask(imgview3).execute(MyArrList.get(position).get("product_img_name3"));



//            imgview1 = (ImageView) convertView.findViewById(R.id.image1);


//            Picasso.with(Show_0.this)
//                    .load("http://brainwakecafe.com/"+MyArrList.get(position).get("pic"))
//                    // .placeholder(R.drawable.placeholder)   // optional
//                    //.error(R.drawable.error)      // optional
//                    //.resize(400,400)                        // optional
//                   .into(imgview1);




            return convertView;

        }

    }


    public String getJSONUrl(String url, List<NameValuePair> params) {
        StringBuilder str = new StringBuilder();
        HttpClient client = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));


            HttpResponse response = client.execute(httpPost);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == 200) { // Download OK
                HttpEntity entity = response.getEntity();
                InputStream content = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                String line;
                while ((line = reader.readLine()) != null) {
                    str.append(line);
                }
            } else {
                Log.e("Log", "Failed to download result..");
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return str.toString();
    }

}

