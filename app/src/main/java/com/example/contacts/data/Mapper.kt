package com.example.contacts.data

import com.example.contacts.data.local.model.ContactEntity
import com.example.contacts.domain.ContactModel

fun ContactEntity.toDomain() = ContactModel(
    id = id,
    name = name,
    surname = surname,
    phone = phone
)

fun ContactModel.toEntity() = ContactEntity().apply {
    this.id = this@toEntity.id
    this.name = this@toEntity.name
    this.surname = this@toEntity.surname
    this.phone = this@toEntity.phone
}