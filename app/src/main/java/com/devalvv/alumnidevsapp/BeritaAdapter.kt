package com.devalvv.alumnidevsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import android.widget.ArrayAdapter

class BeritaAdapter(context: Context, private val beritaList: List<Berita>) :
    ArrayAdapter<Berita>(context, 0, beritaList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_berita, parent, false)
        val berita = beritaList[position]

        val judulTextView = view.findViewById<TextView>(R.id.judulBerita)
        val deskripsiTextView = view.findViewById<TextView>(R.id.deskripsiPendek)
        val imageView = view.findViewById<ImageView>(R.id.imageBerita)

        judulTextView.text = berita.judul
        deskripsiTextView.text = berita.deskripsi
        imageView.setImageDrawable(ContextCompat.getDrawable(context, berita.imageResId))

        return view
    }
}
