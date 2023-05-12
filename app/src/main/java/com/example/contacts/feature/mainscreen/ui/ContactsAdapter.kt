package com.example.contacts.feature.mainscreen.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contacts.R
import com.example.contacts.databinding.ItemContactBinding
import com.example.contacts.domain.ContactModel

class ContactsAdapter() : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    private var contacts: List<ContactModel> = emptyList()

    inner class ViewHolder(private val binding: ItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(contact: ContactModel) {
            binding.tvName.text =
                binding.root.context.getString(R.string.contact_name, contact.surname, contact.name)
            binding.tvPhone.text = contact.phone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(contacts[position])
    }

    override fun getItemCount() = contacts.size

    fun setData(contacts: List<ContactModel>) {
        this.contacts = contacts
        notifyDataSetChanged()
    }
}