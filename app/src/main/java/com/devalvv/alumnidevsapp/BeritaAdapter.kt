package com.devalvv.alumnidevsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

data class Berita(val judul: String, val deskripsi: String, val imageResId: Int)

class BeritaAdapter(private val context: Context, private val dataSource: List<Berita>) : BaseAdapter() {

    private val inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(position: Int): Any {
        return dataSource[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val rowView = inflater.inflate(R.layout.list_item_berita, parent, false)
        val judulTextView = rowView.findViewById<TextView>(R.id.judulBerita)
        val deskripsiTextView = rowView.findViewById<TextView>(R.id.deskripsiBerita)
        val imageView = rowView.findViewById<ImageView>(R.id.imageBerita)

        val berita = getItem(position) as Berita
        judulTextView.text = berita.judul
        deskripsiTextView.text = berita.deskripsi
        imageView.setImageResource(berita.imageResId)

        return rowView
    }
}
