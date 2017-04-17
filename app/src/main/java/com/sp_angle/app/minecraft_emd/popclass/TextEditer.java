
package com.sp_angle.app.minecraft_emd.popclass;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.sp_angle.app.minecraft_emd.R;
import com.sp_angle.app.minecraft_emd.dataBase.dataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;

public class TextEditer{
	managementOfInstance managementOfInstance=new managementOfInstance();
	
	
	PopupWindow printWin;
	public void textEditerPop(View view,LinearLayout mod_printpop){
		printWin= new PopupWindow(view); 
	    printWin.setWindowLayoutMode(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		printWin.setContentView(dataBase.popupWindowLinearLayout[101]);
		printWin.setBackgroundDrawable(null);
		printWin.showAtLocation(view,Gravity.CENTER,0,0);
		printWin.setFocusable(true);
		//dataBase.printTextView1.setText(dataBase.argmentKind[dataBase.argmentNumber]);
	}

	public void printc(){
		printWin.dismiss();
	}
	
	public void printu(View view,LinearLayout mod_printpop,Context con){
		Toast myToast = Toast.makeText(con,mod_printpop.getResources().getString(R.string.mod_print_tyui),Toast.LENGTH_SHORT);
		myToast.setGravity(Gravity.BOTTOM, 0, 0);
		myToast.show();
	}

	Maker maker=new Maker();
	dataBase dataBase=new dataBase();
	
	public String printm(){
		
		
		int mk=managementOfInstance.maker.getmk();
		
		String printtext1=dataBase.functionName;
		String printtext2=null;
		StringBuffer stringBuffer1=new StringBuffer();
		if(dataBase.argmentKind.length!=1){
			for(int a=0;a!=dataBase.argmentKind.length;a++){
				stringBuffer1.append("\"");
				stringBuffer1.append(dataBase.argmentValue[a]);
				stringBuffer1.append("\"");
				if(a!=dataBase.argmentKind.length-1)stringBuffer1.append(",");
			}
			printtext2=stringBuffer1.toString();
		}else{
			printtext2=dataBase.argmentValue[0];
		}
		
		String printtext3=");\n";
		StringBuilder stringBuffer2=new StringBuilder();
		stringBuffer2.append(printtext1);
		stringBuffer2.append(printtext2);
		stringBuffer2.append(printtext3);
		dataBase.script[mk]=maker.space(stringBuffer2.toString(),dataBase.spaceInt);
		return dataBase.script[mk];
	}
	public void saveEditTextString(){
		dataBase.argmentValue[dataBase.argmentNumber]=dataBase.printEditText1.getText().toString();
		dataBase.argmentKind[dataBase.argmentNumber]="ok";
		dataBase.printEditText1.getText().clear();
		backPopupWindow();
	}
	   
	public void backPopupWindow(){
		printWin.dismiss();
		boolean allOk=true;
		for(int a=0;a!=dataBase.argmentKind.length;a++){
			if(dataBase.argmentKind[a]!="ok"){
				allOk=false;
				break;
			}
		}
		if(allOk==false){
			managementOfInstance.argmentSelectionClass.argumentSelectionPopWindow();
			dataBase.popupWindowCount=201;
			}else{
				printm();
				String maked=maker.make();
				Toast myToast = Toast.makeText(dataBase.context,maked,Toast.LENGTH_SHORT);
				myToast.setGravity(Gravity.BOTTOM, 0, 0);
				myToast.show();
			}
		allOk=true;
	}
}
