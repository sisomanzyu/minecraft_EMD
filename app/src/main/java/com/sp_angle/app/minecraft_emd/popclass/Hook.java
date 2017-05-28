package com.sp_angle.app.minecraft_emd.popclass;

import android.content.Context;
import android.content.Intent;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sp_angle.app.minecraft_emd.EditActivity;
import com.sp_angle.app.minecraft_emd.PopupActivity;
import com.sp_angle.app.minecraft_emd.dataBase.DataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;
import com.sp_angle.app.minecraft_emd.makeclass.Output;


public class Hook
{
	Output output=new Output();
	public void hook(String hookOfKind,int hookNumber,int space,boolean hooked[],LinearLayout popwindow,View view,Context editContext){
		hookpop();
		 if(!hooked[hookNumber]){
			 DataBase.script[maker.getmkh()]=hookOfKind;
			 hookm(maker.getmkh(),space,editContext);
		 }
		output.output();
		hooked[hookNumber]=true;
	}
	PopupWindow HookWin;
	
	public void hookpop(){
		Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
		DataBase.editActivityContext.startActivity(intent);
	}
	
	Maker maker=new Maker();
	
	public void hookc(){
		HookWin.dismiss();
	}
	
	public String hookm(int mk,int space,Context con){
		DataBase.script[mk+1]="}\n\n";
		String maked=maker.make();
		Toast myToast = Toast.makeText(con,maked,
									   Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.BOTTOM, 0, 0);
		myToast.show();
		
		return DataBase.script[mk];
	}
}
