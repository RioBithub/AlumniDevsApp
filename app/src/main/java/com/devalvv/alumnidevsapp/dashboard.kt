package com.devalvv.alumnidevsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class dashboard : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.fragment_container)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        val topMenu = findViewById<LinearLayout>(R.id.top_menu)

        // Set default fragment
        loadFragment(HomeFragment())

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.navigation_berita -> {
                    loadFragment(BeritaFragment())
                    true
                }
                R.id.navigation_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }

        // Handle top menu buttons
        findViewById<Button>(R.id.btnTambahData).setOnClickListener {
            startActivity(Intent(this, TambahDataActivity::class.java))
        }

        findViewById<Button>(R.id.btnDataAlumni).setOnClickListener {
            startActivity(Intent(this, DataAlumniActivity::class.java))
        }

        findViewById<Button>(R.id.btnLogout).setOnClickListener {
            // Handle Logout
            Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    class HomeFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            return inflater.inflate(R.layout.fragment_home, container, false)
        }
    }

    class BeritaFragment : Fragment() {

        private lateinit var beritaList: List<Berita>
        private lateinit var listView: ListView

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_berita, container, false)

            listView = view.findViewById(R.id.listViewBerita)

            // Data dummy untuk berita
            beritaList = listOf(
                Berita("Judul Berita 1", "Deskripsi Pendek 1", R.mipmap.ic_launcher),
                Berita("Judul Berita 2", "Deskripsi Pendek 2", R.mipmap.ic_launcher),
                Berita("Judul Berita 3", "Deskripsi Pendek 3", R.mipmap.ic_launcher),
                Berita("Judul Berita 4", "Deskripsi Pendek 4", R.mipmap.ic_launcher),
                Berita("Judul Berita 5", "Deskripsi Pendek 5", R.mipmap.ic_launcher),
                Berita("Judul Berita 6", "Deskripsi Pendek 6", R.mipmap.ic_launcher),
                Berita("Judul Berita 7", "Deskripsi Pendek 7", R.mipmap.ic_launcher),
                Berita("Judul Berita 8", "Deskripsi Pendek 8", R.mipmap.ic_launcher),
                Berita("Judul Berita 9", "Deskripsi Pendek 9", R.mipmap.ic_launcher),
                Berita("Judul Berita 10", "Deskripsi Pendek 10", R.mipmap.ic_launcher)
            )

            val adapter = BeritaAdapter(requireContext(), beritaList)
            listView.adapter = adapter

            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(activity, DetailBeritaActivity::class.java)
                intent.putExtra("judul", beritaList[position].judul)
                intent.putExtra("deskripsi", beritaList[position].deskripsi)
                startActivity(intent)
            }

            return view
        }
    }


    class ProfileFragment : Fragment() {
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_profile, container, false)

            val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
            val email = sharedPreferences?.getString("email", "Email tidak ditemukan")
            val nim = sharedPreferences?.getString("nim", "NIM tidak ditemukan")
            val nama = sharedPreferences?.getString("nama", "Nama tidak ditemukan")
            val kelas = sharedPreferences?.getString("kelas", "Kelas tidak ditemukan")

            val emailTextView = view.findViewById<TextView>(R.id.email)
            val nimTextView = view.findViewById<TextView>(R.id.nim)
            val namaTextView = view.findViewById<TextView>(R.id.nama)
            val kelasTextView = view.findViewById<TextView>(R.id.kelas)

            emailTextView.text = email
            nimTextView.text = nim
            namaTextView.text = nama
            kelasTextView.text = kelas

            val logoutButton = view.findViewById<Button>(R.id.logoutButton)
            logoutButton.setOnClickListener {
                // Handle Logout
                sharedPreferences?.edit()?.clear()?.apply()
                Toast.makeText(activity, "Logged out", Toast.LENGTH_SHORT).show()
                activity?.finish()
            }

            return view
        }
    }
}
