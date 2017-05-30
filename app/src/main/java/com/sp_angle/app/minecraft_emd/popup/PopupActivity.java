package com.sp_angle.app.minecraft_emd.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sp_angle.app.minecraft_emd.R;
import com.sp_angle.app.minecraft_emd.dataBase.Const;
import com.sp_angle.app.minecraft_emd.dataBase.DataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sp_angle.app.minecraft_emd.dataBase.Const.*;


/**
 * Created by watanabe-takumi on 2017/05/28.
 */

public class PopupActivity extends AppCompatActivity {

    DataBase.ListData listData;
    LinearLayout linearLayout;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        setFinishOnTouchOutside(false);

        listData = DataBase.ListData.classInstance;

        linearLayout =(LinearLayout)findViewById(R.id.linearLayoyt_popup);

        switch (listData.popupMode) {
            case LIST_MODE:
                setList();
                break;
            case VALUE_LIST_MODE:
                setValueList();
                break;
            case VALUE_MODE:
                setValue();
                break;
        }
        }
    public void setList(){
        ListView listView = new ListView(this);
        List<String> kindOfArrayList = new ArrayList<String>();
        if(DataBase.kindOfList!=null)switch (DataBase.kindOfList){
            case Const.KOUBUN_STRING:
                kindOfArrayList = DataBase.koubunList;
                break;
            case Const.FUNCTION_STRING:
                kindOfArrayList = DataBase.functionList;
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,kindOfArrayList);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;

                String item = (String) listView.getItemAtPosition(position);
                switch (item){
                    case Const.SKIP:
                        DataBase.kindOfList = Const.FUNCTION_STRING;
                        listMode();
                        transitionListPopup(Const.FUNCTION_STRING);
                        break;

                    case Const.PRINT:
                        transitionValueListPopup("print(",HINT_DISPLAY_TEXT);
                        break;
                    case TEST:
                        transitionValueListPopup("test(",HINT_DISPLAY_TEXT,HINT_SPEED);
                        break;
                }
            }
        });
        linearLayout.addView(listView);
    }

    public void setValueList(){
        final ListView listView = new ListView(this);
        final List<String> argumentList = listData.argumentList;
        List<String> argumentListHint = listData.argumentListHint;
        List<Map<String,String>> valueList = new ArrayList<Map<String,String>>();

        for(int i = 0;i<argumentListHint.size();i++){
            Map<String,String> valueListMap = new HashMap<String, String>();
            valueListMap.put("Main",argumentListHint.get(i));
            String value;
            if(argumentList.get(i)==null){
                value = "未入力";
            }else {
                value = argumentList.get(i);
            }
            valueListMap.put("Sub",value);

            valueList.add(valueListMap);
        }

        SimpleAdapter adapter = new SimpleAdapter(this,valueList,R.layout.popup_list_item,new String[]{"Main","Sub"},new int[]{R.id.popup_list_item_text1,R.id.popup_list_item_text2});

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                ListView listView = (ListView) parent;

                HashMap<String,String> item = (HashMap<String,String>) listView.getItemAtPosition(position);
                String hint = item.get("Main");
                if(hint==HINT_DISPLAY_TEXT)
                    transitionValuePopup(hint, InputType.TYPE_CLASS_TEXT,position);
                else if(hint==HINT_SPEED)
                    transitionValuePopup(hint,InputType.TYPE_CLASS_NUMBER,position);
            }
        });
        linearLayout.addView(listView);
        Button button = new Button(this);
        button.setText(ADD_FUNCTION);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String printText1 = null;
                String printText2 = null;
                StringBuffer stringBuffer1 = new StringBuffer();;
                for(int a = 0;listData.argumentListHint.size()>a;a++) {
                    printText1 = DataBase.functionName;
                    if (textCheck(listData.argumentListHint.get(a)) == true)
                        stringBuffer1.append("\"");
                    stringBuffer1.append(listData.argumentList.get(a));
                    if (textCheck(listData.argumentListHint.get(a)) == true)
                        stringBuffer1.append("\"");
                    if(a!=listData.argumentListHint.size()-1)
                        stringBuffer1.append(",");
                }
                printText2=stringBuffer1.toString();
                String printText3=");\n";
                StringBuilder stringBuffer2=new StringBuilder();
                stringBuffer2.append(printText1);
                stringBuffer2.append(printText2);
                stringBuffer2.append(printText3);
                DataBase.script[Maker.getmk()]=Maker.space(stringBuffer2.toString(), DataBase.spaceInt);
            }
        });
        linearLayout.addView(button);
    }

    public final boolean textCheck(String hint){
        if(hint==HINT_DISPLAY_TEXT)
            return true;
        return false;
    }

    public void transitionValueListPopup(String functionName, String... kindOfValue){
        if(functionName!=null)DataBase.functionName = functionName;
        for(String  kov: kindOfValue) {
            listData.argumentListHint.add(kov);
        }
        listData.popupMode = VALUE_LIST_MODE;
        Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
        DataBase.editActivityContext.startActivity(intent);
        finish();
    }

    public void setValue(){
        final EditText editText = new EditText(this);
        editText.setHint(DataBase.hintOfEditText);
        editText.setInputType(DataBase.valueInputType);
        Button button = new Button(this);
        button.setText(COMPLETION);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                listData.argumentList.set(listData.argumentPosition,editText.getText().toString());
                transitionValueListPopup(null);
            }
        });
        linearLayout.addView(editText);
        linearLayout.addView(button);
        listMode();
    }

    public void transitionListPopup(String kindOfList){
        DataBase.kindOfList = kindOfList;

        Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
        DataBase.editActivityContext.startActivity(intent);
        finish();
    }

    public void transitionValuePopup(String hintOfEditText,int valueInputType,int position){
        DataBase.hintOfEditText = hintOfEditText;
        DataBase.valueInputType = valueInputType;
        listData.argumentPosition = position;
        valueMode();
        Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
        DataBase.editActivityContext.startActivity(intent);
        finish();
    }

    public void resetListData(){
        DataBase.ListData.classInstance = new DataBase.ListData();
    }

    public void finishPopupActivity(View view){
        resetListData();
        finish();
    }

    public  final void valueMode(){
        listData.popupMode = VALUE_MODE;
    }
    public  final void listMode(){
        listData.popupMode = LIST_MODE;
    }
    public  final void valueListMode(){

    }
}


