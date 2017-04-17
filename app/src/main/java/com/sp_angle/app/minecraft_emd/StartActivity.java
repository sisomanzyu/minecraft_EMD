package com.sp_angle.app.minecraft_emd;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.*;
import android.content.Intent;

public class StartActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
    }
	public void gohome(View view){
		Intent gohomei=new Intent(getApplicationContext(),HomeActivity.class);
		startActivity(gohomei);
		}
}
