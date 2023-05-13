package com.example.contacts.data.local

import com.example.contacts.data.local.model.ContactEntity
import io.realm.Realm
import java.util.*

class ContactLocalSource(private val realm: Realm) {

    fun add(model: ContactEntity) {
        realm.executeTransaction {
            it.createObject(ContactEntity::class.java, UUID.randomUUID().toString()).apply {
                this.name = model.name
                this.surname = model.surname
                this.phone = model.phone
            }
        }
    }

    fun delete(model: ContactEntity) {
        realm.executeTransaction {
            val result = realm.where(ContactEntity::class.java).equalTo("id", model.id).findAll()
            result.deleteAllFromRealm()
        }
    }

    fun getAll(): List<ContactEntity> {
        return realm.where(ContactEntity::class.java).findAll()
    }
}