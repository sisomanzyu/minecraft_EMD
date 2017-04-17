package com.sp_angle.app.minecraft_emd.popclass;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.sp_angle.app.minecraft_emd.dataBase.dataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;
import com.sp_angle.app.minecraft_emd.makeclass.Output;


public class Hook
{
	dataBase dataBase=new dataBase();
	Output output=new Output();
	public void hook(String hookOfKind,int hookNumber,int space,boolean hooked[],LinearLayout popwindow,View view,Context editContext){
			hookpop(view,popwindow);
		 if(!hooked[hookNumber]){
			 dataBase.script[maker.getmkh()]=hookOfKind;
			 hookm(maker.getmkh(),space,editContext);
		 }
		output.output();
		hooked[hookNumber]=true;
	}
	PopupWindow HookWin;
	
	public void hookpop(View view,LinearLayout hookpop){
	    HookWin= new PopupWindow(view);
		HookWin.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
		HookWin.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		HookWin.setContentView(hookpop);
		HookWin.setBackgroundDrawable(null);
		HookWin.showAtLocation(view,Gravity.CENTER,0,0);
		HookWin.setFocusable(true);
		HookWin.update();
		}
	
	Maker maker=new Maker();
	
	public void hookc(){
		HookWin.dismiss();
	}
	
	public String hookm(int mk,int space,Context con){
		dataBase.script[mk+1]="}\n\n";
		String maked=maker.make();
		Toast myToast = Toast.makeText(con,maked,
									   Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.BOTTOM, 0, 0);
		myToast.show();
		
		return dataBase.script[mk];
	}
}
