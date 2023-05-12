package com.example.contacts.data.local.model

import io.realm.RealmModel
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass
import io.realm.annotations.Required

@RealmClass
open class ContactEntity : RealmModel {

    @PrimaryKey
    var id: String = ""

    @Required
    var name: String = ""

    @Required
    var surname: String = ""

    @Required
    var phone: String = ""
}