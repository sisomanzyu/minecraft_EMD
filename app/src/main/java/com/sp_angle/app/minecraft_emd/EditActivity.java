package com.sp_angle.app.minecraft_emd;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.sp_angle.app.minecraft_emd.dataBase.DataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;
import com.sp_angle.app.minecraft_emd.makeclass.Output;
import com.sp_angle.app.minecraft_emd.popclass.ArgumentSelectionClass;
import com.sp_angle.app.minecraft_emd.popclass.Hook;
import com.sp_angle.app.minecraft_emd.popclass.Muzyoken;
import com.sp_angle.app.minecraft_emd.popclass.TextEditer;
import com.sp_angle.app.minecraft_emd.popclass.managementOfInstance;
import com.sp_angle.app.minecraft_emd.popclass.transitionPopupWindow;

import java.io.File;
public class EditActivity extends Activity
{

	Intent bl;
  	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.edit, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId()){
			case R.id.save:
				output.output();

			case R.id.menu_play:
				
				Intent patch =new Intent("net.zhuoweizhang.mcpelauncher.action.IMPORT_SCRIPT");
				patch.setDataAndType(Uri.fromFile(new File(Environment.getExternalStorageDirectory() + "/EMD/mods/makedByEMD.js")), "text/plain");
				startActivity(patch);
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	LinearLayout popupframe;
	LinearLayout argumentSelectionLayoutChild;
	
	managementOfInstance managementOfInstance=new managementOfInstance();
	
	Maker maker=managementOfInstance.maker;
	Output output=managementOfInstance.output;
	transitionPopupWindow transitionPopupWindow=managementOfInstance.transitionPopupWindow;
	Hook hook=managementOfInstance.hook;
	Muzyoken muzyoken=managementOfInstance.muzyoken;
	TextEditer textEditer=managementOfInstance.textEditer;
	ArgumentSelectionClass argmentSelectionClass=managementOfInstance.argmentSelectionClass;
	//DataBase DataBase=new DataBase();
	
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
		DataBase.appContext = getApplicationContext();
		DataBase.editActivityContext =this;
		
		DataBase.popupWindowLinearLayout[0]=(LinearLayout)getLayoutInflater().inflate(R.layout.hook, null);
		DataBase.popupWindowLinearLayout[1]=(LinearLayout)getLayoutInflater().inflate(R.layout.muzyoken, null);
		DataBase.popupWindowLinearLayout[101]=(LinearLayout)getLayoutInflater().inflate(R.layout.print, null);
		DataBase.popupWindowLinearLayout[201]=(LinearLayout)getLayoutInflater().inflate(R.layout.popupwindowframe,null);

		DataBase.argmentSelectionPopupWindowLinearLayoutChild=new LinearLayout(this);
		DataBase.argmentSelectionPopupWindowLinearLayoutChild.setOrientation(LinearLayout.VERTICAL);
		DataBase.popupWindowLinearLayout[201].addView(DataBase.argmentSelectionPopupWindowLinearLayoutChild);
		DataBase.popupWindowScrollView[201]=new ScrollView(this);
		DataBase.popupWindowScrollView[201].addView(DataBase.popupWindowLinearLayout[201]);
	
		DataBase.printEditText1=(EditText) DataBase.popupWindowLinearLayout[101].findViewById(R.id.printEditText1);
		DataBase.printTextView1=(TextView) DataBase.popupWindowLinearLayout[101].findViewById(R.id.printTextView1);
		
		for(int hc=0;hc<hooked.length;hc++)hooked[hc]=false;	
		for(int mkc = 0; mkc< DataBase.script.length; mkc++) DataBase.script[mkc]="";
		
		DataBase.popupWindowCount=-1;
    }



	//hook
	String Hook;
	boolean hooked[]=new boolean[100];
	int mk=0;
	String maked;
	int tyu=0;
	
	public void useItem(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function useItem(x,y,z,itemId,blockId,side,itemDamage,blockDamage){\n";
		DataBase.kindOfList="koubun";
		hook.hook(DataBase.hookOfKind,0, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void attackHook(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function attackHook(attacker,victim){\n";
		hook.hook(DataBase.hookOfKind,1, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}

	public void modTick(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function modTick(){\n";
		hook.hook(DataBase.hookOfKind,2, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
		
	}
	
	public void procCmd(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function procCmd(cmd){\n";
		hook.hook(DataBase.hookOfKind,3, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void newLevel(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function newLevel(){\n";
		hook.hook(DataBase.hookOfKind,4, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void leaveGame(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function leaveGame(){\n";
		hook.hook(DataBase.hookOfKind,5, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void entityAdded(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function entityAddedHook(entity){\n";
		hook.hook(DataBase.hookOfKind,6, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void entityRemoved(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function entityRemovedHook(entity){\n";
		hook.hook(DataBase.hookOfKind,7, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void deathHook(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function deathHook(murderer, victim){\n";
		hook.hook(DataBase.hookOfKind,8, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void levelEvent(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function levelEventHook(entity,eventType,x,y,z,data){\n";
		hook.hook(DataBase.hookOfKind,9, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	public void blockEvent(View view){
		DataBase.popupWindowCount=0;
		DataBase.hookOfKind="function blockEventHook(x,y,z,eventType,data){\n";
		hook.hook(DataBase.hookOfKind,10, DataBase.spaceInt,hooked, DataBase.popupWindowLinearLayout[0],view,this);
		DataBase.spaceInt=1;
	}
	
	//bunpo
	
	public void muzyoken(View view){
	    transitionPopupWindow.transitionPopupWindow();
		DataBase.popupWindowCount=1;
		muzyoken.showFunctionPopupWindow(view);
	}
		
	//kansu
	
	public void textFunctions(View view){
		transitionPopupWindow.transitionPopupWindow();
		DataBase.popupWindowCount=201;
		DataBase.argmentKind=new String[1];
		DataBase.argmentKind[0]="text";
		DataBase.view=view;
		argmentSelectionClass.argumentSelectionPopWindow();
	}
	
	String textFunctionsOfKind;
	
	
	public void print(View view){
		DataBase.functionName="print(";
		textFunctions(view);
    }
	
	public void clientMessage(View view){
		DataBase.functionName="clientMessage(";
		textFunctions(view);
	}
	
	/*public void (View view){
		transitionPopupWindow.transitionPopupWindow();
		DataBase.popupWindowCount=201;
		DataBase.functionName="";
		DataBase.argmentKind=new String[];
		DataBase.view=view;
		argmentSelectionClass.argumentSelectionPopWindow();
		
	}*/
	
	public void mod_printok(View view){
		textEditer.saveEditTextString();
	}
	
	public void closePopupWindow(View view){
		transitionPopupWindow.transitionPopupWindow();
		DataBase.spaceInt=0;
	}
}
