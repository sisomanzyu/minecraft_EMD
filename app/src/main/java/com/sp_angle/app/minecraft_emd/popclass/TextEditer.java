
package com.sp_angle.app.minecraft_emd.popclass;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.Toast;

import com.sp_angle.app.minecraft_emd.R;
import com.sp_angle.app.minecraft_emd.dataBase.DataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;

public class TextEditer{
	managementOfInstance managementOfInstance=new managementOfInstance();
	
	
	PopupWindow printWin;
	public void textEditerPop(View view,LinearLayout mod_printpop){
		printWin= new PopupWindow(view); 
	    printWin.setWindowLayoutMode(
			LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		printWin.setContentView(DataBase.popupWindowLinearLayout[101]);
		printWin.setBackgroundDrawable(null);
		printWin.showAtLocation(view,Gravity.CENTER,0,0);
		printWin.setFocusable(true);
		//DataBase.printTextView1.setText(DataBase.argmentKind[DataBase.argmentNumber]);
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
	
	public String printm(){
		
		
		int mk=managementOfInstance.maker.getmk();
		
		String printtext1= DataBase.functionName;
		String printtext2=null;
		StringBuffer stringBuffer1=new StringBuffer();
		if(DataBase.argmentKind.length!=1){
			for(int a = 0; a!= DataBase.argmentKind.length; a++){
				stringBuffer1.append("\"");
				stringBuffer1.append(DataBase.argmentValue[a]);
				stringBuffer1.append("\"");
				if(a!= DataBase.argmentKind.length-1)stringBuffer1.append(",");
			}
			printtext2=stringBuffer1.toString();
		}else{
			printtext2= DataBase.argmentValue[0];
		}
		
		String printtext3=");\n";
		StringBuilder stringBuffer2=new StringBuilder();
		stringBuffer2.append(printtext1);
		stringBuffer2.append(printtext2);
		stringBuffer2.append(printtext3);
		DataBase.script[mk]=maker.space(stringBuffer2.toString(), DataBase.spaceInt);
		return DataBase.script[mk];
	}
	public void saveEditTextString(){
		DataBase.argmentValue[DataBase.argmentNumber]= DataBase.printEditText1.getText().toString();
		DataBase.argmentKind[DataBase.argmentNumber]="ok";
		DataBase.printEditText1.getText().clear();
		backPopupWindow();
	}
	   
	public void backPopupWindow(){
		printWin.dismiss();
		boolean allOk=true;
		for(int a = 0; a!= DataBase.argmentKind.length; a++){
			if(DataBase.argmentKind[a]!="ok"){
				allOk=false;
				break;
			}
		}
		if(allOk==false){
			managementOfInstance.argmentSelectionClass.argumentSelectionPopWindow();
			DataBase.popupWindowCount=201;
			}else{
				printm();
				String maked=maker.make();
				Toast myToast = Toast.makeText(DataBase.editActivityContext,maked,Toast.LENGTH_SHORT);
				myToast.setGravity(Gravity.BOTTOM, 0, 0);
				myToast.show();
			}
		allOk=true;
	}
}
