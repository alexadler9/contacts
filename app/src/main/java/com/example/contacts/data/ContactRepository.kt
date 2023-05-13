package com.example.contacts.data

import com.example.contacts.domain.ContactModel

interface ContactRepository {

    fun add(model: ContactModel)

    fun delete(model: ContactModel)

    fun getAll(): List<ContactModel>
}