package com.example.contacts.constants

import com.example.contacts.domain.ContactModel

class ContactConstants {

    companion object {
        val CONTACT_1 = ContactModel(
            id = "1",
            name = "name1",
            surname = "surname1",
            phone = "79999999999"
        )

        val CONTACT_2 = ContactModel(
            id = "2",
            name = "name2",
            surname = "surname2",
            phone = "78888888888"
        )

        val CONTACT_UNKNOWN = ContactModel(
            id = "666",
            name = "name666",
            surname = "surname666",
            phone = "76666666666"
        )

        val LIST_CONTACTS: List<ContactModel> = listOf(
            CONTACT_1,
            CONTACT_2
        )
    }
}





