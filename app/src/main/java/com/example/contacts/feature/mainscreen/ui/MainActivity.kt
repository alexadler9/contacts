package com.example.contacts.feature.mainscreen.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.contacts.databinding.ActivityMainBinding
import com.example.contacts.feature.addcontactscreen.ui.AddContactActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainScreenViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = ContactsAdapter()
        binding.rvContacts.adapter = adapter

        viewModel.contacts.observe(this) {
            adapter.setData(it)
        }

        binding.fabAddContact.setOnClickListener {
            startActivity(Intent(this, AddContactActivity::class.java))
        }
    }
}