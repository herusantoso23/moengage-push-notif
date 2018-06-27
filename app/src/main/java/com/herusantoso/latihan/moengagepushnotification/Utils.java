package com.herusantoso.latihan.moengagepushnotification;

import android.app.Activity;

import com.moe.pushlibrary.MoEHelper;
import com.moe.pushlibrary.PayloadBuilder;

/**
 * Created by santoso on 6/27/18.
 */

public class Utils {

    public static void sendMoeScreenTracking(Activity activity, String screenName) {
        PayloadBuilder builder = new PayloadBuilder();
        builder.putAttrString("screen", screenName);
        MoEHelper.getInstance(activity).trackEvent("openScreen", builder.build());
    }

}
