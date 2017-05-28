package com.sp_angle.app.minecraft_emd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.sp_angle.app.minecraft_emd.dataBase.Const;
import com.sp_angle.app.minecraft_emd.dataBase.DataBase;
import com.sp_angle.app.minecraft_emd.makeclass.Maker;
import com.sp_angle.app.minecraft_emd.popclass.TextEditer;

import java.util.ArrayList;
import java.util.List;

import static com.sp_angle.app.minecraft_emd.dataBase.Const.*;


/**
 * Created by watanabe-takumi on 2017/05/28.
 */

public class PopupActivity extends AppCompatActivity {

    LinearLayout linearLayout;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        linearLayout =(LinearLayout)findViewById(R.id.linearLayoyt_popup);

        switch (DataBase.popupMode) {
            case LIST_MODE_STRING:
                setList();
                break;
            case VALUE_MODE_STRING:
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
                        transitionValuePopup(HINT_DISPLAY_TEXT,InputType.TYPE_CLASS_TEXT,"print(");
                        break;
                }
            }
        });
        linearLayout.addView(listView);
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
                String printText1= DataBase.functionName;
                String printText2=null;
                StringBuffer stringBuffer1=new StringBuffer();
                if(DataBase.valueInputType==InputType.TYPE_CLASS_TEXT)stringBuffer1.append("\"");
                stringBuffer1.append(editText.getText().toString());
                if(DataBase.valueInputType==InputType.TYPE_CLASS_TEXT)stringBuffer1.append("\"");

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

    public void transitionValuePopup(String hintOfEditText ,int kindOfValue,String functionName){
        DataBase.valueInputType = kindOfValue;
        DataBase.hintOfEditText = hintOfEditText;
        DataBase.functionName = functionName;
        valueMode();
        Intent intent = new Intent(DataBase.appContext,PopupActivity.class);
        DataBase.editActivityContext.startActivity(intent);
        finish();
    }

    public void finishPopupActivity(View view){
        finish();
    }

    public static final void valueMode(){
        DataBase.popupMode = VALUE_MODE_STRING;
    }
    public static final void listMode(){
        DataBase.popupMode = LIST_MODE_STRING;
    }
}
