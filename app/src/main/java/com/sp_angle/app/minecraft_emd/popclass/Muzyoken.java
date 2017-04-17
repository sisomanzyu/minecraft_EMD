package com.sp_angle.app.minecraft_emd.popclass;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.sp_angle.app.minecraft_emd.dataBase.dataBase;

public class Muzyoken
{
	dataBase dataBase=new dataBase();
	PopupWindow muzyokenWin;
	
	public void showFunctionPopupWindow(View view){
		muzyokenWin= new PopupWindow(view);
		muzyokenWin.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
		muzyokenWin.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
		muzyokenWin.setContentView(dataBase.popupWindowLinearLayout[1]);
		muzyokenWin.setBackgroundDrawable(null);
		muzyokenWin.showAtLocation(view,Gravity.CENTER,0,0);
		muzyokenWin.setFocusable(true);
		muzyokenWin.update();
	}
	
	public void muzyokenc(){
		    muzyokenWin.dismiss();
	}
}
