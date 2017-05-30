package com.sp_angle.app.minecraft_emd.dataBase;

import com.sp_angle.app.minecraft_emd.R;

import static com.sp_angle.app.minecraft_emd.dataBase.DataBase.editActivityContext;

/**
 * Created by Sisoman on 2017/05/28.
 */

public class Const {
    public static final String TEST ="テスト";

    public static final String SKIP = "スキップ";
    public static final String PRINT = "画面にメッセージを表示する";
    public static final String CLIENT_MESSAGE = "チャットに文字を表示する";

    //public static final String

    public static final String LIST_MODE = "listMode";
    public static final String VALUE_MODE = "valueMode";
    public static final String VALUE_LIST_MODE = "valueListMode";

    public static final String KOUBUN_STRING = "koubun";
    public static final String FUNCTION_STRING = "function";


    public static final String DISPLAY_TEXT_VALUE = "text";
    public static final String SPEED_VALUE = "int";

    public static final String HINT_DISPLAY_TEXT = "表示する文章を入力してください";
    public static final String HINT_SPEED =editActivityContext.getString(R.string.hint_speed);

    public static final String COMPLETION = editActivityContext.getString(R.string.completion);
    public static final String ADD_FUNCTION = "機能の追加";

}
