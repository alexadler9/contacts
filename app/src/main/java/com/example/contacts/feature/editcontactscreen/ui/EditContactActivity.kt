package com.example.contacts.feature.editcontactscreen.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.base.focusAndShowKeyboard
import com.example.contacts.base.serializable
import com.example.contacts.base.setOnFocusChangeListenerWithCursorAtEnd
import com.example.contacts.base.toEditable
import com.example.contacts.databinding.ActivityEditContactBinding
import com.example.contacts.domain.ContactInteractor
import com.example.contacts.domain.ContactModel
import com.example.contacts.feature.mainscreen.ui.MainActivity
import org.koin.android.ext.android.inject

class EditContactActivity : AppCompatActivity() {

    companion object {
        const val CONTACT_INTENT_KEY_ID = "contact"
    }

    private lateinit var binding: ActivityEditContactBinding

    private val contactInteractor: ContactInteractor by inject()
    private val contact: ContactModel? by lazy {
        intent.extras?.serializable(CONTACT_INTENT_KEY_ID)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (contact == null) {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        } else {
            with(binding) {
                etName.text = contact!!.name.toEditable()
                etSurname.text = contact!!.surname.toEditable()
                etPhone.text = contact!!.phone.toEditable()

                etName.setOnFocusChangeListenerWithCursorAtEnd()
                etSurname.setOnFocusChangeListenerWithCursorAtEnd()
                etPhone.setOnFocusChangeListenerWithCursorAtEnd()

                etName.focusAndShowKeyboard()

                btnEdit.setOnClickListener {
                    contactInteractor.update(
                        ContactModel(
                            id = contact!!.id,
                            name = etName.text.toString(),
                            surname = etSurname.text.toString(),
                            phone = etPhone.text.toString()
                        )
                    )
                    startActivity(Intent(this@EditContactActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
    }
}