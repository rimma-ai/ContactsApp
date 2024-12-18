package com.example.contactsapp

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var btnAddContact: Button
    private val contacts = mutableListOf<Contact>()
    private lateinit var adapter: ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Найдите представления
        recyclerView = findViewById(R.id.recyclerViewContacts)
        btnAddContact = findViewById(R.id.btnAddContact)

        // Настройте адаптер
        adapter = ContactAdapter(contacts) { position ->
            contacts.removeAt(position)
            adapter.notifyItemRemoved(position)
        }

        // Установите LayoutManager и адаптер для RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        // Установите обработчик нажатия для кнопки "Добавить контакт"
        btnAddContact.setOnClickListener {
            showAddContactDialog()
        }
    }

    // Показать диалог добавления контакта
    private fun showAddContactDialog() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_contact, null)
        val etName = dialogView.findViewById<TextInputEditText>(R.id.etName)
        val etPhone = dialogView.findViewById<TextInputEditText>(R.id.etPhone)

        AlertDialog.Builder(this)
            .setTitle("Добавить контакт")
            .setView(dialogView)
            .setPositiveButton("Добавить") { _, _ ->
                val name = etName.text.toString()
                val phone = etPhone.text.toString()
                if (name.isNotEmpty() && phone.isNotEmpty()) {
                    contacts.add(Contact(name, phone))
                    adapter.notifyItemInserted(contacts.size - 1)
                }
            }
            .setNegativeButton("Отмена", null)
            .create()
            .show()
    }
}
