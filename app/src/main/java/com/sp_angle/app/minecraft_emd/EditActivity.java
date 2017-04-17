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

import com.sp_angle.app.minecraft_emd.dataBase.dataBase;
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
	dataBase dataBase=new dataBase();
	
	public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit);
		dataBase.context=this;
		
		dataBase.popupWindowLinearLayout[0]=(LinearLayout)getLayoutInflater().inflate(R.layout.hook, null);
		dataBase.popupWindowLinearLayout[1]=(LinearLayout)getLayoutInflater().inflate(R.layout.muzyoken, null);
		dataBase.popupWindowLinearLayout[101]=(LinearLayout)getLayoutInflater().inflate(R.layout.print, null);
		dataBase.popupWindowLinearLayout[201]=(LinearLayout)getLayoutInflater().inflate(R.layout.popupwindowframe,null);

		dataBase.argmentSelectionPopupWindowLinearLayoutChild=new LinearLayout(this);
		dataBase.argmentSelectionPopupWindowLinearLayoutChild.setOrientation(LinearLayout.VERTICAL);
		dataBase.popupWindowLinearLayout[201].addView(dataBase.argmentSelectionPopupWindowLinearLayoutChild);
		dataBase.popupWindowScrollView[201]=new ScrollView(this);
		dataBase.popupWindowScrollView[201].addView(dataBase.popupWindowLinearLayout[201]);
	
		dataBase.printEditText1=(EditText)dataBase.popupWindowLinearLayout[101].findViewById(R.id.printEditText1);
		dataBase.printTextView1=(TextView)dataBase.popupWindowLinearLayout[101].findViewById(R.id.printTextView1);
		
		for(int hc=0;hc<hooked.length;hc++)hooked[hc]=false;	
		for(int mkc=0; mkc<dataBase.script.length; mkc++)dataBase.script[mkc]="";
		
		dataBase.popupWindowCount=-1;
    }
	
	//hook
	String Hook;
	boolean hooked[]=new boolean[100];
	int mk=0;
	String maked;
	int tyu=0;
	
	public void useItem(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function useItem(x,y,z,itemId,blockId,side,itemDamage,blockDamage){\n";
		hook.hook(dataBase.hookOfKind,0,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void attackHook(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function attackHook(attacker,victim){\n";
		hook.hook(dataBase.hookOfKind,1,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}

	public void modTick(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function modTick(){\n";
		hook.hook(dataBase.hookOfKind,2,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
		
	}
	
	public void procCmd(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function procCmd(cmd){\n";
		hook.hook(dataBase.hookOfKind,3,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void newLevel(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function newLevel(){\n";
		hook.hook(dataBase.hookOfKind,4,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void leaveGame(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function leaveGame(){\n";
		hook.hook(dataBase.hookOfKind,5,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void entityAdded(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function entityAddedHook(entity){\n";
		hook.hook(dataBase.hookOfKind,6,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void entityRemoved(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function entityRemovedHook(entity){\n";
		hook.hook(dataBase.hookOfKind,7,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void deathHook(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function deathHook(murderer, victim){\n";
		hook.hook(dataBase.hookOfKind,8,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void levelEvent(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function levelEventHook(entity,eventType,x,y,z,data){\n";
		hook.hook(dataBase.hookOfKind,9,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	public void blockEvent(View view){
		dataBase.popupWindowCount=0;
		dataBase.hookOfKind="function blockEventHook(x,y,z,eventType,data){\n";
		hook.hook(dataBase.hookOfKind,10,dataBase.spaceInt,hooked,dataBase.popupWindowLinearLayout[0],view,this);
		dataBase.spaceInt=1;
	}
	
	//bunpo
	
	public void muzyoken(View view){
	    transitionPopupWindow.transitionPopupWindow();
		dataBase.popupWindowCount=1;	
		muzyoken.showFunctionPopupWindow(view);
	}
		
	//kansu
	
	public void textFunctions(View view){
		transitionPopupWindow.transitionPopupWindow();
		dataBase.popupWindowCount=201;
		dataBase.argmentKind=new String[1];
		dataBase.argmentKind[0]="text";
		dataBase.view=view;
		argmentSelectionClass.argumentSelectionPopWindow();
	}
	
	String textFunctionsOfKind;
	
	
	public void print(View view){
		dataBase.functionName="print(";
		textFunctions(view);
    }
	
	public void clientMessage(View view){
		dataBase.functionName="clientMessage(";
		textFunctions(view);
	}
	
	/*public void (View view){
		transitionPopupWindow.transitionPopupWindow();
		dataBase.popupWindowCount=201;
		dataBase.functionName="";
		dataBase.argmentKind=new String[];
		dataBase.view=view;
		argmentSelectionClass.argumentSelectionPopWindow();
		
	}*/
	
	public void mod_printok(View view){
		textEditer.saveEditTextString();
	}
	
	public void closePopupWindow(View view){
		transitionPopupWindow.transitionPopupWindow();
		dataBase.spaceInt=0;
	}
}
