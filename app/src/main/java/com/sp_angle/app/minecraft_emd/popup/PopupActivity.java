package com.sp_angle.app.minecraft_emd.popup;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    ListData listData;
    LinearLayout linearLayout;


    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);
        setFinishOnTouchOutside(false);

        listData = new ListData();

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
                        transitonValueListPopup("print(",DISPLAY_TEXT_VALUE);
                        break;
                    case TEST:
                        transitonValueListPopup("test(",DISPLAY_TEXT_VALUE,SPEED_VALUE);
                }
            }
        });
        linearLayout.addView(listView);
    }

    public void setValueList(){
        ListView listView = new ListView(this);
        List<String> argumentList = DataBase.argumentList;
        List<String> argumentListHint = DataBase.argumentListHint;
        List<Map<String,String>> valueList = new ArrayList<Map<String,String>>();

        for(int i = 0;i<argumentList.size();i++){
            Map<String,String> valueListMap = new HashMap<String, String>();
            valueListMap.put("Main",argumentList.get(i));
            valueListMap.put("Sub",argumentListHint.get(i));

            valueList.add(valueListMap);
        }

       SimpleAdapter adapter = new SimpleAdapter(this,valueList,android.R.layout.simple_list_item_2,new String[]{"Main","Sub"},new int[]{android.R.id.text1,android.R.id.text2});

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
                        transitonValueListPopup("print(",DISPLAY_TEXT_VALUE);
                        break;
                    case TEST:
                        transitonValueListPopup("test(",DISPLAY_TEXT_VALUE,SPEED_VALUE);
                }
            }
        });
        linearLayout.addView(listView);
    }

    public void transitonValueListPopup(String functionName,String... kindOfValue){
        DataBase.functionName = functionName;
        for(String  kov: kindOfValue) {
            DataBase.argumentList.add(kov);
            switch (kov) {
                case DISPLAY_TEXT_VALUE:
                    DataBase.argumentListHint.add(HINT_DISPLAY_TEXT);
                    break;
                case SPEED_VALUE:
                    DataBase.argumentListHint.add(HINT_SPEED);
            }
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
                String printText1 = null;
                String printText2 = null;
                StringBuffer stringBuffer1 = new StringBuffer();;
                for(int a = 0;DataBase.argumentList.size()>a;a++) {
                    printText1 = DataBase.functionName;
                    printText2 = null;
                    if (DataBase.argumentList.get(a) == DISPLAY_TEXT_VALUE)
                        stringBuffer1.append("\"");
                    stringBuffer1.append(DataBase.editTextList.get(a).toString());
                    if (DataBase.argumentList.get(a) == DISPLAY_TEXT_VALUE)
                        stringBuffer1.append("\"");
                    if(a!=DataBase.argumentList.size()-1)
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

    public void transitionValuePopup(String hintOfEditText ,String functionName){
        DataBase.hintOfEditText = hintOfEditText;
        DataBase.functionName = functionName;
        valueMode();
        Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
        DataBase.editActivityContext.startActivity(intent);
        finish();
    }

    public void finishPopupActivity(View view){
        listMode();
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


