package com.example.contacts.data.local

import com.example.contacts.data.local.model.ContactEntity
import io.realm.Realm
import java.util.*

class ContactLocalSource(private val realm: Realm) {

    fun add(entity: ContactEntity) {
        realm.executeTransaction {
            it.createObject(ContactEntity::class.java, UUID.randomUUID().toString()).apply {
                this.name = entity.name
                this.surname = entity.surname
                this.phone = entity.phone
            }
        }
    }

    fun update(entity: ContactEntity) {
        realm.executeTransaction {
            it.insertOrUpdate(entity)
        }
    }

    fun delete(entity: ContactEntity) {
        realm.executeTransaction {
            val result = realm.where(ContactEntity::class.java).equalTo("id", entity.id).findAll()
            result.deleteAllFromRealm()
        }
    }

    fun getAll(): List<ContactEntity> {
        return realm.where(ContactEntity::class.java).findAll()
    }
}