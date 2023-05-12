package com.example.contacts.feature.mainscreen.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.domain.ContactModel

class MainScreenViewModel(private val interactor: ContactInteractor) : ViewModel() {

    val contacts: ContactLiveData
        get() = getAllContacts() as ContactLiveData

    fun addContact(contact: ContactModel) {
        interactor.add(contact)
    }

    fun getAllContacts(): MutableLiveData<List<ContactModel>> {
        val contacts = interactor.getAll()
        return ContactLiveData().apply {
            value = contacts.subList(0, contacts.size)
        }
    }
}