package com.sp_angle.app.minecraft_emd;

import android.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.RelativeLayout.*;

public class HomeActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);
    }
	
	public void goedit(View view){
		Intent goediti=new Intent(getApplicationContext(),EditActivity.class);
		startActivity(goediti);
	}
	PopupWindow popupWin;
	boolean pw;
	public void openopenfill(View view){
		
    if(!pw){
		LinearLayout popLayout
			= (LinearLayout)getLayoutInflater().inflate(
			R.layout.fillopen, null);
		popupWin = new PopupWindow(this); 
		popupWin.setWindowLayoutMode(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		popupWin.setContentView(popLayout);
		popupWin.setBackgroundDrawable(null);
		popupWin.showAtLocation(view,Gravity.CENTER,0,0);
		pw=true;
	  }
	}
	public void closeopenfill(View view){
		popupWin.dismiss();
		pw=false;
	}
	
	public void kousin(View view){
		Intent gokousin=new Intent(getApplicationContext(),KousinActivity.class);
	    startActivity(gokousin);
	}
}
