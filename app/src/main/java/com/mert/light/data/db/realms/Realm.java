package com.mert.light.data.db.realms;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.mert.light.data.model.User;
import com.mert.light.tools.Random;
import com.mert.light.tools.encryption.RSA;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.NoSuchPaddingException;

import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class Realm {

    //Activity
    private Activity activity;

    //RSA
    private RSA rsa;

    //Realm
    private io.realm.Realm realm;

    public Realm(Activity activity) {
        try {
            rsa = new RSA();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        this.realm = io.realm.Realm.getDefaultInstance();
    }

    public boolean saveData(RealmObject realmObject) {
        this.realm.beginTransaction();
        this.realm.insert(realmObject);
        this.realm.commitTransaction();
        return true;
    }

    public boolean updateData(RealmObject realmObject) {
        /* ------- */
        return true;
    }

    public RealmResults query(RealmQuery query) {
        return query.findAll();
    }

    public RealmResults queryAsync(RealmQuery query) {
        return query.findAllAsync();
    }

    public boolean isHave(RealmQuery query) {
        if (query.findAll().size() < 1) {
            Log.d(this.getClass().getSimpleName(), "isHave : false");
            return false;
        }

        Log.d(this.getClass().getSimpleName(), "isHave : true");
        return true;
    }

    public io.realm.Realm getRealm() {
        return this.realm;
    }

    //User
    public boolean isHaveByUserName(String name) {
        RealmResults<User> users = getRealm().where(User.class).contains("username", name).findAll();

        if (users.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isHaveByUserPassword(String name, String password) {
        RealmResults<User> users = getRealm().where(User.class).equalTo("username", name).equalTo("password", password).findAll();

        if (users.size() < 1) {
            return false;
        } else {
            return true;
        }
    }

    public User getUser(long id) {
        RealmResults<User> users = getRealm().where(User.class).equalTo("id", id).findAll();

        if (users.size() == 1) {
            return users.first();
        }

        return null;
    }

    public User getUser(String name, String password) {
        RealmResults<User> users = getRealm().where(User.class).equalTo("username", name).equalTo("password", password).findAll();

        if (users.size() == 1) {
            return users.first();
        }

        return null;
    }

    public boolean addUser(String name, String surname, String username, String password) {
        if (!isHaveByUserName(username)) {
            Toast.makeText(activity, "Farklı bir kullanıcı ismi giriniz!", Toast.LENGTH_SHORT).show();
            return false;
        }

        realm.beginTransaction();
        User user = realm.createObject(User.class); // Create a new object
        user.setId(new Random().getRandomId());
        user.setName(name);
        user.setSurname(surname);
        user.setUsername(username);
        user.setPassword(password);
        realm.commitTransaction();

        return true;
    }

    public boolean deleteUser(String username) {
        final RealmResults<User> results = realm.where(User.class).equalTo("username", username).findAll();

        realm.executeTransaction(new io.realm.Realm.Transaction() {
            @Override
            public void execute(io.realm.Realm realm) {
                results.deleteAllFromRealm();
            }
        });

        return true;
    }

}
