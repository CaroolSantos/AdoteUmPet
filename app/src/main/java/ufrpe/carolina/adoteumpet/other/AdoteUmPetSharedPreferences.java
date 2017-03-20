package ufrpe.carolina.adoteumpet.other;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Ana Carolina on 20/03/17.
 */

public class AdoteUmPetSharedPreferences {
    static final String PREF_USER_ID= "userid";
    static final String PREF_PHOTOURL= "photourl";
    static final String PREF_USERNAME= "username";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserId(Context ctx, String userId)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userId);
        editor.commit();
    }

    public static String getUserId(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static void setPhotoUrl(Context ctx, String photoUrl)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_PHOTOURL, photoUrl);
        editor.commit();
    }

    public static String getPhotoUrl(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_PHOTOURL, "");
    }

    public static String getUsername(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USERNAME, "");
    }

    public static void setUsername(Context ctx, String username)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USERNAME, username);
        editor.commit();
    }


}
