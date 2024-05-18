package com.devalvv.alumnidevsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView

class DetailBeritaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_berita)

        val imageViewBerita = findViewById<ImageView>(R.id.imageViewBerita)
        val textViewJudul = findViewById<TextView>(R.id.textViewJudul)
        val textViewDeskripsi = findViewById<TextView>(R.id.textViewDeskripsi)

        // Ambil data dari Intent
        val judul = intent.getStringExtra("judul")
        val deskripsi = intent.getStringExtra("deskripsi")
        val imageResId = intent.getIntExtra("imageResId", R.mipmap.ic_launcher)

        // Set data ke view
        textViewJudul.text = judul
        textViewDeskripsi.text = deskripsi
        imageViewBerita.setImageResource(imageResId)
    }
}
