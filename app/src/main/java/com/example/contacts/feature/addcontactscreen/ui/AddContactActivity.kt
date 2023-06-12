package com.example.contacts.feature.addcontactscreen.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.base.focusAndShowKeyboard
import com.example.contacts.base.setOnFocusChangeListenerWithCursorAtEnd
import com.example.contacts.databinding.ActivityAddContactBinding
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.domain.ContactModel
import com.example.contacts.feature.mainscreen.ui.MainActivity
import org.koin.android.ext.android.inject

class AddContactActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddContactBinding

    private val contactInteractor: ContactInteractor by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            etName.setOnFocusChangeListenerWithCursorAtEnd()
            etSurname.setOnFocusChangeListenerWithCursorAtEnd()
            etPhone.setOnFocusChangeListenerWithCursorAtEnd()

            etName.focusAndShowKeyboard()

            btnAdd.setOnClickListener {
                contactInteractor.add(
                    ContactModel(
                        id = "",
                        name = etName.text.toString(),
                        surname = etSurname.text.toString(),
                        phone = etPhone.text.toString()
                    )
                )
                startActivity(Intent(this@AddContactActivity, MainActivity::class.java))
                finish()
            }
        }
    }
}