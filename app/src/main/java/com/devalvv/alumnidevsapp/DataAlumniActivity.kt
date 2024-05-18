package com.devalvv.alumnidevsapp

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class DataAlumniActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_alumni)

        val dbHelper = DatabaseHelper(this)
        val listViewAlumni = findViewById<ListView>(R.id.listViewAlumni)

        val alumniList = dbHelper.getAllAlumni()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, alumniList.map { "Nim: ${it[DatabaseHelper.COLUMN_NIM]}\nNama: ${it[DatabaseHelper.COLUMN_NAMA]}" })
        listViewAlumni.adapter = adapter

        listViewAlumni.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            val selectedAlumni = alumniList[position]
            val intent = Intent(this, DetailAlumniActivity::class.java)
            intent.putExtra("id", selectedAlumni[DatabaseHelper.COLUMN_ID])
            startActivity(intent)
        }
    }
}
