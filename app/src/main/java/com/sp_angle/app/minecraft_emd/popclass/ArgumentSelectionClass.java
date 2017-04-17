package com.sp_angle.app.minecraft_emd.popclass;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.sp_angle.app.minecraft_emd.R;
import com.sp_angle.app.minecraft_emd.dataBase.dataBase;

public class ArgumentSelectionClass implements OnClickListener
{
	managementOfInstance managementOfInstance=new managementOfInstance();
	dataBase dataBase=new dataBase();

	PopupWindow argmentSelectionPopupWindow;
	
	public void argumentSelectionPopWindow(){
		String buttonText=null;
		for(int fc1=0;fc1!=dataBase.argmentKind.length;fc1++){
			dataBase.argumentSelectionPopwindowButton[fc1]=new Button(dataBase.context);
			switch(dataBase.argmentKind[fc1]){
				case "text":
					buttonText="表示するテキストを指定してください";
					dataBase.printEditText1.setInputType(InputType.TYPE_CLASS_TEXT);
					break;
				case "explode_location_y":
					buttonText="爆発させる座標 Yを入力してください";
					dataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "explode_location_z":
					buttonText="爆発させる座標 Zを入力してください";
					dataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "explode_location_x":
					buttonText="爆発させる座標 Xを入力してください";
					dataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "ok":
				buttonText="完了";
				break;
			}
			dataBase.argumentSelectionPopwindowButton[fc1].setText(buttonText);
			dataBase.argumentSelectionPopwindowButton[fc1].setTag(R.string.TAG_2,fc1);
			dataBase.argumentSelectionPopwindowButton[fc1].setTag(R.string.TAG_1,dataBase.argmentKind[fc1]);
			dataBase.argumentSelectionPopwindowButton[fc1].setOnClickListener(this);
			dataBase.argmentSelectionPopupWindowLinearLayoutChild.addView(dataBase.argumentSelectionPopwindowButton[fc1]);
		}
		argmentSelectionPopupWindow= new PopupWindow(dataBase.view);
		argmentSelectionPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
		argmentSelectionPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);;
		argmentSelectionPopupWindow.setContentView(dataBase.popupWindowScrollView[201]);
		argmentSelectionPopupWindow.setBackgroundDrawable(null);
		argmentSelectionPopupWindow.showAtLocation(dataBase.view,Gravity.CENTER,0,0);
		argmentSelectionPopupWindow.setFocusable(true);
		argmentSelectionPopupWindow.update();
		}
	
	public void argumentSelectionPopWindowClose(){
		argmentSelectionPopupWindow.dismiss();
		dataBase.argmentSelectionPopupWindowLinearLayoutChild.removeAllViews();
	}
	
	@Override
	public void onClick(View view)
	{
		/*if(view.getTag(R.string.TAG_1)=="text"){
			managementOfInstance.transitionPopupWindow.transitionPopupWindow();
		    managementOfInstance.textEditer.textEditerPop(view,dataBase.popupWindowLinearLayout[101]);
			dataBase.popupWindowCount=101;
		}
		dataBase.argmentNumber=view.getTag(R.string.TAG_2);*/
	}
}
