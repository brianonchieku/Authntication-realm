package com.example.authrealm

import android.app.Application
import com.example.authrealm.models.UserModel
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

class MyApp: Application() {

    companion object{
        val realm: Realm by lazy {
            Realm.open(
                configuration = RealmConfiguration.create(
                    schema = setOf(UserModel::class)
                )
            )
        }
    }

    override fun onCreate() {
        super.onCreate()

    }
}