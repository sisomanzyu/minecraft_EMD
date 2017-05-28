package com.sp_angle.app.minecraft_emd.popclass;

import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.sp_angle.app.minecraft_emd.R;
import com.sp_angle.app.minecraft_emd.dataBase.DataBase;

public class ArgumentSelectionClass implements OnClickListener
{
	managementOfInstance managementOfInstance=new managementOfInstance();

	PopupWindow argmentSelectionPopupWindow;
	
	public void argumentSelectionPopWindow(){
		String buttonText=null;
		for(int fc1 = 0; fc1!= DataBase.argmentKind.length; fc1++){
			DataBase.argumentSelectionPopwindowButton[fc1]=new Button(DataBase.editActivityContext);
			switch(DataBase.argmentKind[fc1]){
				case "text":
					buttonText="表示するテキストを指定してください";
					DataBase.printEditText1.setInputType(InputType.TYPE_CLASS_TEXT);
					break;
				case "explode_location_y":
					buttonText="爆発させる座標 Yを入力してください";
					DataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "explode_location_z":
					buttonText="爆発させる座標 Zを入力してください";
					DataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "explode_location_x":
					buttonText="爆発させる座標 Xを入力してください";
					DataBase.printEditText1.setInputType(InputType.TYPE_CLASS_NUMBER);
					break;
				case "ok":
				buttonText="完了";
				break;
			}
			DataBase.argumentSelectionPopwindowButton[fc1].setText(buttonText);
			DataBase.argumentSelectionPopwindowButton[fc1].setTag(R.string.TAG_2,fc1);
			DataBase.argumentSelectionPopwindowButton[fc1].setTag(R.string.TAG_1, DataBase.argmentKind[fc1]);
			DataBase.argumentSelectionPopwindowButton[fc1].setOnClickListener(this);
			DataBase.argmentSelectionPopupWindowLinearLayoutChild.addView(DataBase.argumentSelectionPopwindowButton[fc1]);
		}
		argmentSelectionPopupWindow= new PopupWindow(DataBase.view);
		argmentSelectionPopupWindow.setHeight(WindowManager.LayoutParams.MATCH_PARENT);
		argmentSelectionPopupWindow.setWidth(WindowManager.LayoutParams.MATCH_PARENT);;
		argmentSelectionPopupWindow.setContentView(DataBase.popupWindowScrollView[201]);
		argmentSelectionPopupWindow.setBackgroundDrawable(null);
		argmentSelectionPopupWindow.showAtLocation(DataBase.view,Gravity.CENTER,0,0);
		argmentSelectionPopupWindow.setFocusable(true);
		argmentSelectionPopupWindow.update();
		}
	
	public void argumentSelectionPopWindowClose(){
		argmentSelectionPopupWindow.dismiss();
		DataBase.argmentSelectionPopupWindowLinearLayoutChild.removeAllViews();
	}
	
	@Override
	public void onClick(View view)
	{
		/*if(view.getTag(R.string.TAG_1)=="text"){
			managementOfInstance.transitionPopupWindow.transitionPopupWindow();
		    managementOfInstance.textEditer.textEditerPop(view,DataBase.popupWindowLinearLayout[101]);
			DataBase.popupWindowCount=101;
		}
		DataBase.argmentNumber=view.getTag(R.string.TAG_2);*/
	}
}
