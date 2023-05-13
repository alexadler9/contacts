package com.example.contacts.feature.mainscreen.ui

import androidx.lifecycle.ViewModel
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.domain.ContactModel

class MainScreenViewModel(private val interactor: ContactInteractor) : ViewModel() {

    val contacts: ContactLiveData = getAllContacts()

    fun deleteContact(contact: ContactModel) {
        interactor.delete(contact)
        contacts.value = interactor.getAll().toMutableList()
    }

    private fun getAllContacts(): ContactLiveData {
        return ContactLiveData().apply {
            value = interactor.getAll().toMutableList()
        }
    }
}