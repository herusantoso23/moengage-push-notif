package com.herusantoso.latihan.moengagepushnotification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.moengage.push.PushManager;

/**
 * Created by santoso on 5/28/18.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = MyFirebaseInstanceIdService.class.getSimpleName();
    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        //Prefs.write(Prefs.API_HEADER_PUSHTOKEN, refreshedToken);
        PushManager.getInstance().refreshToken(getApplicationContext(), refreshedToken);
    }
}
