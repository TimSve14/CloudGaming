package game.project.com.gameclouds;

/**
 * Created by TimSvensson on 2016-11-04.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private static final String MY_PREFERENCES = "my_preferences";

    public static boolean isFirst(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        final boolean first = reader.getBoolean("is_first", true);
        if(first){
            final SharedPreferences.Editor editor = reader.edit();
            editor.putBoolean("is_first", false);
            editor.commit();
        }
        return first;
    }

    public static void saveIp(Context context, String _ip){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = reader.edit();
        editor.putString("ipadress", _ip);
        editor.commit();
    }

    public static String getIp(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        return reader.getString("ipadress", "");
    }


}
