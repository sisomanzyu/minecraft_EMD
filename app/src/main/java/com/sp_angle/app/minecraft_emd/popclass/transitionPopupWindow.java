package com.sp_angle.app.minecraft_emd.popclass;

import com.sp_angle.app.minecraft_emd.dataBase.*;

public class transitionPopupWindow
{
	public void transitionPopupWindow(){
		switch(DataBase.popupWindowCount){
			case 0:
				managementOfInstance.hook.hookc();
				break;
			case 1:
				managementOfInstance.muzyoken.muzyokenc();
				break;
			case 101:
				managementOfInstance.textEditer.printc();
				break;
			case 201:
				managementOfInstance.argmentSelectionClass.argumentSelectionPopWindowClose();
				break;
		}
	}
}
