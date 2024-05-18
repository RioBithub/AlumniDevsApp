package com.devalvv.alumnidevsapp

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailBeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val judulTextView = findViewById<TextView>(R.id.judulDetailBerita)
        val deskripsiTextView = findViewById<TextView>(R.id.deskripsiDetailBerita)

        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")

        judulTextView.text = judul
        deskripsiTextView.text = deskripsi
    }
}
