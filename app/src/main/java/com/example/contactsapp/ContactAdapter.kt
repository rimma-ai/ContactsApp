package com.example.contactsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter(
    private val contacts: MutableList<Contact>,
    private val onDeleteClick: (Int) -> Unit
) : RecyclerView.Adapter<ContactAdapter.ContactViewHolder>() {

    // ViewHolder для элемента списка
    inner class ContactViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvPhone: TextView = itemView.findViewById(R.id.tvPhone)
        val btnDelete: Button = itemView.findViewById(R.id.btnDeleteContact)
    }

    // Создание ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.contact_item, parent, false)
        return ContactViewHolder(view)
    }

    // Привязка данных к ViewHolder
    override fun onBindViewHolder(holder: ContactViewHolder, position: Int) {
        val contact = contacts[position]
        holder.tvName.text = contact.name
        holder.tvPhone.text = contact.phone

        // Обработчик нажатия кнопки "Удалить"
        holder.btnDelete.setOnClickListener {
            onDeleteClick(position)
        }
    }

    // Количество элементов в списке
    override fun getItemCount(): Int = contacts.size
}
