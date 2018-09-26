package com.mert.light.data.db.sharedpreferences;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.mert.light.data.model.User;
import com.mert.light.tools.encryption.RSA;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

public class SharedPreferences {

    private android.content.SharedPreferences preferences;
    private android.content.SharedPreferences.Editor editor;

    //Activity
    private Activity activity;

    //RSA
    private RSA rsa;

    public SharedPreferences(Activity activity){
        this.activity = activity;
        try {
            this.rsa = new RSA();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        this.preferences = this.activity.getSharedPreferences("myPreferences", Context.MODE_PRIVATE);
        this.editor = this.preferences.edit();
    }

    public void setSession(User user) {
        editor.putLong("id", user.getId()).commit();
        Log.d(this.getClass().getSimpleName(), "setSession, -id :" + user.getId());
    }

    public long getId() {
        return preferences.getLong("id", -1);
    }

    public void setId(long id) {
        editor.putLong("id", id).commit();

        Log.d(this.getClass().getSimpleName(), "setId, -id :" + id);
    }

    public boolean isLogin() {
        long isLogin = preferences.getLong("id", -1);

        if (isLogin == -1) {
            return false;
        }

        return true;
    }

    public void clear() {
        editor.clear().commit();
        Log.d(this.getClass().getSimpleName(), "SharedPreferences :clear");
    }
}
