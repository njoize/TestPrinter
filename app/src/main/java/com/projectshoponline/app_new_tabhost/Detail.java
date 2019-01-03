package com.projectshoponline.app_new_tabhost;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.zj.wfsdk.PrintPic;
import com.zj.wfsdk.WifiCommunication;

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

public class Detail extends AppCompatActivity {


    TextView tvView1;

    Button b_menu;

    ArrayList<HashMap<String, String>> MyArrList;

    ListView listView_1;

    ImageView imgview1;

    Button btnSearch_1;

    EditText strKeySearch_1;

    Button b_add;
    Button b_back;

    TextView tv_TotalPrice;

    /////////
    Button btnConn = null;
    Button btnPrint = null;
    Button btn_test = null;
    Button btnClose = null;
    Button btn_opencasher = null;
    // EditText edtContext = null;

    TextView edtContext = null;

    WifiCommunication wfComm = null;
    EditText txt_ip = null;
    int  connFlag = 0;
    revMsgThread revThred = null;
    //checkPrintThread cheThread = null;
    private static final int WFPRINTER_REVMSG = 0x06;
    ////////

    String New_String_TotalPrice;
    String New_String_Name;

    TextView t_name;

    // TODO (1) Declare a TextView variable called mToysListTextView
    TextView mToysListTextView;

    String New_pname;

    TextView tv_new;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

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


        tv_TotalPrice = (TextView) findViewById(R.id.tv_TotalPrice);
        //////////////////////

//        text_id.setText("" + s_id);
//        text_name.setText("" + s_name);
//        text_price.setText("" + s_price);

        //text_price.setText("" + s_price);

        // edtContext = (EditText) this.findViewById(R.id.txt_content);
        t_name= (TextView) this.findViewById(R.id.t_name);


        edtContext = (TextView) this.findViewById(R.id.txt_content);
        //edtContext.setText("order:" + s_id +  "\nname:"+ s_order_product_name +"\nprice:" +s_order_product_price_total);
        //edtContext.setText(" order:" + s_id + "\n"+ "\n name:"+ s_name +  "\n"+  "\n price:"+ s_price+"฿");
       // edtContext.setText("\n price:"+ New_String_TotalPrice+"฿");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                btnConn.performClick();
            }
        }, 2000);


        btnConn = (Button) this.findViewById(R.id.btn_conn);
        btnConn.setOnClickListener(new ClickEvent());
        btnPrint = (Button) this.findViewById(R.id.btnSend);
        btnPrint.setOnClickListener(new ClickEvent());
        btn_test = (Button) this.findViewById(R.id.btn_test);
        btn_test.setOnClickListener(new ClickEvent());
        btnClose = (Button) this.findViewById(R.id.btnClose);
        btnClose.setOnClickListener(new ClickEvent());
        //  edtContext = (EditText) this.findViewById(R.id.txt_content);


        txt_ip = (EditText)this.findViewById(R.id.txt_ip);
        wfComm = new WifiCommunication(mHandler);
        btn_opencasher = (Button)this.findViewById(R.id.btn_opencasher);
        btn_opencasher.setOnClickListener(new ClickEvent());

        btnConn.setEnabled(true);
        btnPrint.setEnabled(false);
        btn_test.setEnabled(false);
        btnClose.setEnabled(false);
        btn_opencasher.setEnabled(false);
        ////////////////////////


        mToysListTextView = (TextView) findViewById(R.id.tv_names);

        tv_new = (TextView) findViewById(R.id.tv_new);


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
    } // onCreate


    //แสดงข้อมูล
    public void ShowData() {

        listView_1 = (ListView) findViewById(R.id.listView_1);

        strKeySearch_1 = (EditText) findViewById(R.id.txtKeySearch_1);

        // Disbled Keyboard auto focus
        InputMethodManager imm = (InputMethodManager) getSystemService(
                Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(strKeySearch_1.getWindowToken(), 0);


       // tv_id = (TextView) findViewById(R.id.tv_id);
        // tv_name = (TextView) findViewById(R.id.tv_name);
        // tv_detail = (TextView) findViewById(R.id.tv_detail);


        final Intent intent = getIntent();
        String s_id = intent.getStringExtra("id");
        String s_TotalPrice = intent.getStringExtra("TotalPrice");
        String s_tname = intent.getStringExtra("tname");
        String s_tzname = intent.getStringExtra("tzname");
        String s_cnum = intent.getStringExtra("cnum");
        //String s_detail = intent.getStringExtra("product_detail");
        //String s_img = intent.getStringExtra("product_img");
        // String s_status = intent.getStringExtra("status");


        //+++++PRINT+++++
        edtContext.append("#"+ s_id+"      Table No. "+s_tname+"  "+s_tzname+"      Customers. "+s_cnum+"\n\n");

        // ?????????????????????????????????//
        // NEW STRING ###################################//

        //New_String_id = c.getString("MemberID");
        //New_String_name = c.getString("Username");



        New_String_TotalPrice =  intent.getStringExtra("TotalPrice");

        // NEW STRING ###################################//
        // ?????????????????????????????????//
        //edtContext.setText("\n price:"+ New_String_TotalPrice+"฿");
      // edtContext.setText("order:" + s_id +  "\nname:"+ New_String_Name +"\nprice:" +New_String_TotalPrice);
       // edtContext.setText("#" + s_id +  "\n"+ New_String_Name +"" +New_String_TotalPrice);
       // edtContext.setText("#" + s_id +  "\n"+ New_String_Name +"" +New_String_TotalPrice);
        tv_TotalPrice.setText("" + s_TotalPrice + " บาท");

        String url = "http://brainwakecafe.com/app/app_show_pr_orders_and_pr_orders_list_id_0_detail.php?id="+s_id;
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
//                map.put("TotalList", c.getString("TotalList"));
//                map.put("TotalPrice", c.getString("TotalPrice"));
                map.put("pname", c.getString("pname"));
//                map.put("date", c.getString("date"));
//                map.put("type", c.getString("type"));
                map.put("num", c.getString("num"));
                map.put("price", c.getString("price"));
                map.put("pic", c.getString("pic"));
//                map.put("Elder_Eat_Medicine_ID", c.getString("Elder_Eat_Medicine_ID"));
//                map.put("Elder_Eat_Med_Type_ID", c.getString("Elder_Eat_Med_Type_ID"));
//                map.put("Elder_Eat_Med_ID", c.getString("Elder_Eat_Med_ID"));


                //edtContext.setText(c.getString("pname")+"   "+c.getString("price"));
               //edtContext.setText(c.getString("pname")+"\n\n\n");


                New_pname = (c.getString("pname")+"  "+c.getString("num")+" x "+c.getString("price")+"\n");


                //tv_new.setText(c.getString("pname")+"\n\n\n");
                //tv_new.append(New_pname+"\n\n\n");
               // tv_new.append(New_pname+"");

                //+++++PRINT+++++
                edtContext.append(New_pname+"");
                //+++++END PRINT+++++

                // TODO (3) Use findViewById to get a reference to the TextView from the layout


                // TODO (4) Use the static ToyBox.getToyNames method and store the names in a String array
                //Array of Strings of Toy Names Created
                //String[] toyNames = ToyBox.getToyNames();
               // String toyNames = (c.getString("pname")+"   "+c.getString("price"));

                // TODO (5) Loop through each toy and append the name to the TextView (add \n for spacing)
                //For Each loop for Toy Names and appending spaces after each toy name

                String[] pname = new String[0];
                for(String toyName : pname){
                    mToysListTextView.append(toyName+"\n\n\n");


                }

                MyArrList.add(map);

            }



            //+++++PRINT+++++
            edtContext.append("\n"+"รวมทั้งสิ้น "+ s_TotalPrice + " บาท"+"");
            //+++++END PRINT+++++



            listView_1.setAdapter(new ImageAdapter(this));

            // OnClick Item
            listView_1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> myAdapter, View myView,
                                        int position, long mylng) {

//                    String s_id = MyArrList.get(position).get("id")
//                            .toString();
//
//
//                    Intent newActivity = new Intent(Show_0.this, Detail.class);
//                    newActivity.putExtra("id", s_id);
//                    startActivity(newActivity);

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
                convertView = inflater.inflate(R.layout.detail_column, null);
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
            txt1.setText(MyArrList.get(position).get("pname") + "");
            t_name.setText(MyArrList.get(position).get("pname") + "");


            //edtContext.setText(MyArrList.get(position).get("pname") + ""+MyArrList.get(position).get("price") + " บาท");



            // R.id.
            TextView txt2 = (TextView) convertView.findViewById(R.id.txt2);
            txt2.setPadding(5, 0, 0, 0);
            txt2.setText(MyArrList.get(position).get("price") + " บาท");
            //      R.id.






//
//            //R.id.
//            TextView txt3 = (TextView) convertView.findViewById(R.id.txt3);
//            txt3.setPadding(5, 0, 0, 0);
//            txt3.setText("สินค้า " + MyArrList.get(position).get("TotalList") + " รายการ");
////              R.id.
//
//            //R.id.
//            TextView txt4 = (TextView) convertView.findViewById(R.id.txt4);
//            txt4.setPadding(5, 0, 0, 0);
//            txt4.setText(MyArrList.get(position).get("date"));
////              R.id.
//
//            //R.id.
//            TextView txt5 = (TextView) convertView.findViewById(R.id.txt5);
//            txt5.setPadding(5, 0, 0, 0);
//            txt5.setText(MyArrList.get(position).get("type"));
////              R.id.
////
            //R.id.
            TextView txt6 = (TextView) convertView.findViewById(R.id.txt6);
            txt6.setPadding(5, 0, 0, 0);
            txt6.setText("จำนวน " + MyArrList.get(position).get("num") + " ชิ้น");
//              R.id.

            TextView txt_pic = (TextView) convertView.findViewById(R.id.txt_pic);
            txt_pic.setPadding(5, 0, 0, 0);
            txt_pic.setText("http://brainwakecafe.com/"+MyArrList.get(position).get("pic"));





            //txt_pic.setText(MyArrList.get(position).get("pic"));
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



            imgview1 = (ImageView) convertView.findViewById(R.id.image1);


            Picasso.with(Detail.this)
                    .load("http://brainwakecafe.com/"+MyArrList.get(position).get("pic"))
                    // .placeholder(R.drawable.placeholder)   // optional
                    //.error(R.drawable.error)      // optional
                    //.resize(400,400)                        // optional
                   .into(imgview1);




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

    //////////////////////////////////

///////////////////////////


    @Override
    protected void onDestroy() {
        super.onDestroy();
        wfComm.close();
    }

class ClickEvent implements View.OnClickListener {

    String tag = "24novV1";

    public void onClick(View v) {

        Log.d(tag, "You Click ==> " + v.toString());

        if (v == btnConn) {
            if( connFlag == 0 ){   //������������˰�ť������������߳�
                connFlag = 1;
                Log.d("wifi����","���\"����\"");
                String strAddressIp = txt_ip.getText().toString();
                wfComm.initSocket(strAddressIp,9100);
            }
        } else if (v == btnPrint) {

//            Start Print

            String msg = edtContext.getText().toString();
            Log.d(tag, "msg ==> " + msg);
           // String msg = listView_1.toString();

            Toast.makeText(Detail.this, "ok", Toast.LENGTH_SHORT).show();


            //feedPaperCutPartial();

            if( msg.length() > 0 ){

                byte[] openCashDrawer   = new byte[]{0x1B, 0x70, 0x00, 0x40, 0x50}; // Open Cash Drawer
                wfComm.sndByte(openCashDrawer);

                byte[] tcmd = new byte[3];
                tcmd[0] = 0x10;
                tcmd[1] = 0x04;
                tcmd[2] = 0x04;     //����Ƿ���ָֽ��
                wfComm.sndByte(tcmd);

                byte[] bold     =  new  byte [ 3 ]; // Set the font (double height and width bold)
                bold[0] = 0x1B;
                bold[1] = 0x21;
                bold[2] |= 0x04; // 08 04 bold
                bold[2] |= 0x08; // 10 08 height
                bold[2] |= 0x20; // 20 10
                wfComm.sndByte(bold);
                byte[] centered = new byte[]{0x1B, 0x61, 1}; // centered
                wfComm.sndByte(centered);
                wfComm.sendMsg("Brainwake"+"\n"+"Matichon Academy"+"\n"+"02 005 0026"+"\n\n", "tis-620");

                byte[] dfont    = new byte[]{0x1B, 0x21, 0x00}; // default font
                wfComm.sndByte(dfont);
                byte[] left     = new byte[]{0x1B, 0x61, 0}; // left
                wfComm.sndByte(left);
                wfComm.sendMsg(msg,"tis-620");
                byte[] tail = new byte[3];
                tail[0] = 0x0A;
                tail[1] = 0x0D;
                wfComm.sndByte(tail);

                byte[] cutterPaper      = new byte[]{0x1D, 0x56, 0x42, 90}; // Cutter Paper command
                wfComm.sndByte(cutterPaper);
            }
        } else if (v == btnClose) {
            wfComm.close();
        }else if( v == btn_opencasher ){
            byte[] tcmd = new byte[5];
            tcmd[0] = 0x1B;
            tcmd[1] = 0x70;
            tcmd[2] = 0x00;
            tcmd[3] = 0x40;
            tcmd[4] = 0x50;
            wfComm.sndByte(tcmd);
        }else if (v == btn_test) {
            byte[] tcmd = new byte[4];
            tcmd[0] = 0x10;
            tcmd[1] = 0x04;
            tcmd[2] = 0x04;
            wfComm.sndByte(tcmd);   //����Ƿ���ָֽ��

            tcmd[0] = 0x1B;
            tcmd[1] = 0x42;
            tcmd[2] = 0x04;
            tcmd[3] = 0x01;
            wfComm.sndByte(tcmd);   //����������

            String msg = "";
            String lang = getString(R.string.strLang);
            printImage();           //��ӡͼƬ
            byte[] cmd = new byte[3];
            cmd[0] = 0x1b;
            cmd[1] = 0x21;
            if((lang.compareTo("en")) == 0){
                cmd[2] |= 0x10;
                wfComm.sndByte(cmd);          //set double height and double width mode
                wfComm.sendMsg("AAAAAAAAAกกก! \n\n", "GBK");
                cmd[2] &= 0xEF;
                wfComm.sndByte(cmd);          //cancel double height and double width mode
                try {
                    Thread.sleep(50);                   //ÿ��һ����ʱ5����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                msg = "  You have sucessfully created communications between your device and our WIFI printer.\n\n"
                        +"  our company is a high-tech enterprise which specializes" +
                        " in R&D,manufacturing,marketing of thermal printers and barcode scanners.\n\n";
                wfComm.sendMsg(msg, "GBK");
            }else if((lang.compareTo("ch")) == 0){
                cmd[2] |= 0x10;
                wfComm.sndByte(cmd);             //set double height and double width mode
                wfComm.sendMsg("��ϲ��! \n\n", "GBK");  //send data to the printer By gbk encoding
                cmd[2] &= 0xEF;
                wfComm.sndByte(cmd);            //cancel double height and double width mode
                try {
                    Thread.sleep(50);                   //ÿ��һ����ʱ5����
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                msg = "  ���Ѿ��ɹ��������������ǵ�WIFI��ӡ����\n\n"
                        + "  ���ǹ�˾��һ��רҵ�����з�����������������Ʊ�ݴ�ӡ��������ɨ���豸��һ��ĸ߿Ƽ���ҵ.\n\n";
                wfComm.sendMsg(msg, "GBK");
            }

            tcmd[0]=0x1D;
            tcmd[1]=0x56;
            tcmd[2]=0x42;
            tcmd[3]=90;
            wfComm.sndByte(tcmd);  //�е�ָ��
        }
    }
}

    private final Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case WifiCommunication.WFPRINTER_CONNECTED:
                    connFlag = 0;
                    // Toast.makeText(getApplicationContext(), "Connect the WIFI-printer successful",
                    //         Toast.LENGTH_SHORT).show();
                    btnPrint.setEnabled(true);
                    btn_test.setEnabled(true);
                    btnClose.setEnabled(true);
                    btn_opencasher.setEnabled(true);
                    btnConn.setEnabled(false);

                    revThred = new revMsgThread();
                    revThred.start();
                    break;
                case WifiCommunication.WFPRINTER_DISCONNECTED:
                    //Toast.makeText(getApplicationContext(), "Disconnect the WIFI-printer successful",
                    //        Toast.LENGTH_SHORT).show();
                    btnConn.setEnabled(true);
                    btnPrint.setEnabled(false);
                    btn_test.setEnabled(false);
                    btnClose.setEnabled(false);
                    btn_opencasher.setEnabled(false);
                    revThred.interrupt();
                    break;
                case WifiCommunication.SEND_FAILED:
                    connFlag = 0;
                    Toast.makeText(getApplicationContext(), "Send Data Failed,please reconnect",
                            Toast.LENGTH_SHORT).show();
                    btnConn.setEnabled(true);
                    btnPrint.setEnabled(false);
                    btn_test.setEnabled(false);
                    btnClose.setEnabled(false);
                    btn_opencasher.setEnabled(false);
                    revThred.interrupt();
                    break;
                case WifiCommunication.WFPRINTER_CONNECTEDERR:
                    connFlag = 0;
                    Toast.makeText(getApplicationContext(), "Connect the WIFI-printer error",
                            Toast.LENGTH_SHORT).show();
                    break;
                case WFPRINTER_REVMSG:
                    byte revData = (byte) Integer.parseInt(msg.obj.toString());
                    if(((revData >> 6) & 0x01) == 0x01)
                        Toast.makeText(getApplicationContext(), "The printer has no paper", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }
        }
    };

class checkPrintThread extends Thread {
    @Override
    public void run() {
        byte[] tcmd = new byte[3];
        tcmd[0] = 0x10;
        tcmd[1] = 0x04;
        tcmd[2] = 0x04;
        try {
            while(true){
                wfComm.sndByte(tcmd);
                Thread.sleep(15);
                Log.d("wifi����","����һ�ε�������");
            }
        }catch (InterruptedException e){
            e.printStackTrace();
            Log.d("wifi����","�˳��߳�");
        }
    }
}

//��ӡ���̣߳������ϴ�ӡ��ʱ�������رմ�ӡ��ʱ�˳�
class revMsgThread extends Thread {
    @Override
    public void run() {
        try {
            Message msg = new Message();
            int revData;
            while(true)
            {
                revData = wfComm.revByte();               //�����������ֽڽ������ݣ�����ĳɷ����������ַ�����ο��ֲ�
                if(revData != -1){

                    msg = mHandler.obtainMessage(WFPRINTER_REVMSG);
                    msg.obj = revData;
                    mHandler.sendMessage(msg);
                }
                Thread.sleep(20);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Log.d("wifi����","�˳��߳�");
        }
    }
}


    //��ӡͼ��
    private void printImage() {
        byte[] sendData = null;
        PrintPic pg = new PrintPic();
        int i = 0,s = 0,j = 0,index = 0,lines = 0;
        pg.initCanvas(384);
        pg.initPaint();
        pg.drawImage(0, 0, "/mnt/sdcard/icon.bmp");
        sendData = pg.printDraw();
        byte[] temp = new byte[(pg.getWidth() / 8)*5];
        byte[] dHeader = new byte[8];
        if(pg.getLength()!=0){
            dHeader[0] = 0x1D;
            dHeader[1] = 0x76;
            dHeader[2] = 0x30;
            dHeader[3] = 0x00;
            dHeader[4] = (byte)(pg.getWidth()/8);
            dHeader[5] = 0x00;
            dHeader[6] = (byte)(pg.getLength()%256);
            dHeader[7] = (byte)(pg.getLength()/256);
            wfComm.sndByte(dHeader);
            for( i = 0 ; i < (pg.getLength()/5)+1 ; i++ ){         //ÿ��5�з���һ��ͼƬ����
                s = 0;
                if( i < pg.getLength()/5 ){
                    lines = 5;
                }else{
                    lines = pg.getLength()%5;
                }
                for( j = 0 ; j < lines*(pg.getWidth() / 8) ; j++ ){
                    temp[s++] = sendData[index++];
                }
                wfComm.sndByte(temp);
                try {
                    Thread.sleep(60);                              //ÿ��һ����ʱ60����
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                for(j = 0 ; j <(pg.getWidth()/8)*5 ; j++ ){         //����������
                    temp[j] = 0;
                }
            }
        }
    }
}





