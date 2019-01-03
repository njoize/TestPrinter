package com.projectshoponline.app_new_tabhost;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


/**
 * @author Adil Soomro
 *
 */
@SuppressWarnings("deprecation")
public class TabHostMENU extends TabActivity {
	/** Called when the activity is first created. */

	String strMemberID = "";

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabhost_menu);
//		//*** Permission StrictMode
//		if (android.os.Build.VERSION.SDK_INT > 9) {
//			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//			StrictMode.setThreadPolicy(policy);
//		}
//
//		//*** Get Session Login
//		final UserHelper usrHelper = new UserHelper(this);
//
//		//*** Get Login Status
//		if(!usrHelper.getLoginStatus())
//		{
//			Intent newActivity = new Intent(TabHostMENU.this, Page_Home.class);
//			startActivity(newActivity);
//		}
//
//		//*** Get Member ID from Session
//		strMemberID = usrHelper.getMemberID();


		setTabs() ;

	}
	private void setTabs()
	{

 	 
 		//addTab("Home",    R.drawable.tab_home, Tab_Home.class);
		//addTab("Home",    R.drawable.tab_home, Page_Web_1.class);
		addTab("บิลขาย",    R.drawable.tab_home, Show_0.class);
 		//addTab("Page_1",  R.drawable.tab_page_1,  Page_1.class);
		addTab("  โต๊ะ",  R.drawable.tab_page_1,  Page_1.class);
 		addTab("อาหาร",  R.drawable.tab_page_2,  Page_2.class);
 		addTab(" เตือน",  R.drawable.tab_page_3,  Page_3.class);
		addTab("  อื่นๆ",  R.drawable.tab_page_4,  My_Profile.class);
	}


	private void addTab(String labelId, int drawableId, Class<?> c)
	{
		TabHost tabHost = getTabHost();
		Intent intent = new Intent(this, c);
		TabHost.TabSpec spec = tabHost.newTabSpec("tab" + labelId);
		
		View tabIndicator = LayoutInflater.from(this).inflate(R.layout.tab_indicator, getTabWidget(), false);
		TextView title = (TextView) tabIndicator.findViewById(R.id.title);
		title.setText(labelId);
		ImageView icon = (ImageView) tabIndicator.findViewById(R.id.icon);
		icon.setImageResource(drawableId);
		
		spec.setIndicator(tabIndicator);
		spec.setContent(intent);
		tabHost.addTab(spec);
	}
	/////////////// onBackPressed



//	@Override
//	public void onBackPressed() {
//		super.onBackPressed();
//		Log.d("MainActivity","onBackPressed");
//		Toast.makeText(getApplicationContext(),"onBackPressed to Page Home", Toast.LENGTH_SHORT).show();
//
//		Intent i = new Intent(TabHostMENU.this, MainActivity.class);
//		startActivity(i);
//		finish();




}