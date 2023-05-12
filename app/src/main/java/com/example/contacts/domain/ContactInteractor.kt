package com.example.contacts.domain

import com.example.contacts.data.ContactRepository

class ContactInteractor(private val contactRepository: ContactRepository) {

    fun add(model: ContactModel) {
        contactRepository.add(model)
    }

    fun getAll(): List<ContactModel> {
        return contactRepository.getAll()
    }
}