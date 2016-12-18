package game.project.com.gameclouds;

/**
 * Created by TimSvensson on 2016-11-04.
 */
import android.content.Context;
import android.content.SharedPreferences;

public class MyPreferences {
    private static final String MY_PREFERENCES = "my_preferences";

    /**
     * unused method as for now
     * @param context
     * @return Boolean value
     */
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

    /**
     * Saves the ip to preferences.
     * @param _context
     * @param _ip
     */
    public static void saveIp(Context _context, String _ip){
        final SharedPreferences reader = _context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = reader.edit();
        editor.putString("ipadress", _ip);
        editor.commit();
    }

    /**
     * get the ip from preferences
     * @param context
     * @return String value
     */
    public static String getIp(Context context) {
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        return reader.getString("ipadress", "");
    }

    /**
     * Save if raw data is going to be used
     * @param context
     * @param rawData
     */
    public static void sendRawData(Context context, Boolean rawData){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = reader.edit();
        editor.putBoolean("rawData", rawData);
        editor.apply();
    }

    /**
     * return if raw data is going to be used
     * @param context
     * @return Boolean value
     */
    public static boolean isRawData(Context context){
        final SharedPreferences reader = context.getSharedPreferences(MY_PREFERENCES, Context.MODE_PRIVATE);
        return reader.getBoolean("rawData", false);
    }


}
