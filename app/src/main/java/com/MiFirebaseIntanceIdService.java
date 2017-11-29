package com;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by User on 27/11/2017.
 */

public class MiFirebaseIntanceIdService extends FirebaseInstanceIdService{

    public static final String TAG = "NOTICIAS";

    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.

        Log.d(TAG, "Refreshed token: " +FirebaseInstanceId.getInstance().getToken());

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.

    }


}
