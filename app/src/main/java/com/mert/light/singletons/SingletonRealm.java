package com.mert.light.singletons;

import android.app.Activity;
import android.util.Log;

import com.mert.light.data.db.realms.Realm;
import com.mert.light.data.model.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

public class SingletonRealm extends Realm{
    private static SingletonRealm ourInstance;

    private SingletonRealm(Activity activity) {
        super(activity);
    }

    public static SingletonRealm getInstance(Activity activity) {
        if (ourInstance == null) {
            ourInstance = new SingletonRealm(activity);
            Log.d(SingletonRealm.class.getClass().getSimpleName(), "SingletonRealm ayağa kaldırıldı.");
        }

        return ourInstance;
    }
}