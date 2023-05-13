package com.example.contacts.feature.mainscreen.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.feature.addcontactscreen.ui.AddContactActivity
import com.example.contacts.feature.editcontactscreen.ui.EditContactActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainScreenViewModel by viewModel()

    private val contactsAdapter: ContactsAdapter by lazy {
        ContactsAdapter(
            onItemEditClick = {
                val intent = Intent(this, EditContactActivity::class.java)
                intent.putExtra(EditContactActivity.CONTACT_INTENT_KEY_ID, it)
                startActivity(intent)
            },
            onItemDeleteClick = {
                viewModel.deleteContact(it)
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvContacts.adapter = contactsAdapter

        viewModel.contacts.observe(this) {
            contactsAdapter.setData(it)
        }

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}