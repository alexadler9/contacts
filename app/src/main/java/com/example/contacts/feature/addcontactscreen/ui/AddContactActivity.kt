package com.example.contacts.feature.addcontactscreen.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.btnAdd.setOnClickListener {
            with(binding) {
                contactInteractor.add(
                    ContactModel(
                        id = "",
                        name = etName.text.toString(),
                        surname = etSurname.text.toString(),
                        phone = etPhone.text.toString()
                    )
                )
            }
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}