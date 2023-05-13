package com.example.contacts.domain

import java.io.Serializable

data class ContactModel(
    val id: String,
    val name: String,
    val surname: String,
    val phone: String
) : Serializable