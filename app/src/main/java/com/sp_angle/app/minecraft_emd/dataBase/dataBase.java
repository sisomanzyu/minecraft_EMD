package com.sp_angle.app.minecraft_emd.dataBase;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class dataBase
{
	public static View view;
	public static Context context;
	
	public static LinearLayout popupWindowLinearLayout[]=new LinearLayout[500];
	public static ScrollView popupWindowScrollView[]=new ScrollView[500];
	
	public static LinearLayout argmentSelectionPopupWindowLinearLayoutChild;
	
	
	public static Button argumentSelectionPopwindowButton[]=new Button[255];
	public static EditText printEditText1;
	public static TextView printTextView1;
	
	public static String script[]=new String[100000];
	public static int spaceInt=0;
	public static int popupWindowCount=0;
	public static String hookOfKind;
	
	public static String argmentKind[];
	public static String argmentValue[]=new String[255];
	public static int argmentNumber=0;
	
	public static String functionName;
}

