package com.example.authrealm.models

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class UserModel: RealmObject {

    @PrimaryKey var id: ObjectId = ObjectId()
    var userName: String = ""
    var password: String = ""
}