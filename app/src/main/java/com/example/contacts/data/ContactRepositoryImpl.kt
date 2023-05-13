package com.example.contacts.data

import com.example.contacts.data.local.ContactLocalSource
import com.example.contacts.domain.ContactModel

class ContactRepositoryImpl(private val contactLocalSource: ContactLocalSource) :
    ContactRepository {

    override fun add(model: ContactModel) {
        contactLocalSource.add(model.toEntity())
    }

    override fun delete(model: ContactModel) {
        contactLocalSource.delete(model.toEntity())
    }

    override fun getAll(): List<ContactModel> {
        return contactLocalSource.getAll().map { it.toDomain() }
    }
}