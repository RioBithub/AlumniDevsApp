package com.devalvv.alumnidevsapp

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AlertDialog
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
            // Show logout confirmation dialog
            showLogoutConfirmationDialog()
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun showLogoutConfirmationDialog() {
        AlertDialog.Builder(this)
            .setTitle("Logout")
            .setMessage("Anda yakin ingin logout?")
            .setPositiveButton("Ya") { dialog, which ->
                Toast.makeText(this, "Logged out", Toast.LENGTH_SHORT).show()
                finish()
            }
            .setNegativeButton("Tidak", null)
            .show()
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
            val beritaList = listOf(
                Berita(
                    "Harga BBM Pertamina Tetap Stabil",
                    "Pertamina tidak menaikkan harga BBM nonsubsidi untuk Mei 2024.",
                    "PT Pertamina Patra Niaga mengumumkan bahwa harga BBM nonsubsidi seperti Pertamax dan Dex Series tidak mengalami perubahan harga per 1 Mei 2024. Harga Pertamax tetap Rp 12.950 per liter. Keputusan ini bertujuan menjaga stabilitas harga di tengah situasi ekonomi yang menantang, dan ini merupakan bagian dari upaya pemerintah untuk menjaga daya beli masyarakat. Menteri BUMN Erick Thohir menyatakan bahwa langkah ini adalah peran BUMN untuk masyarakat.",
                    R.drawable.berita1
                ),
                Berita(
                    "Pemilu 2024: Tahapan dan Persiapan",
                    "Proses Pemilu 2024 di Indonesia sedang berlangsung dengan persiapan yang matang.",
                    "Pemilu 2024 di Indonesia memasuki tahap krusial dengan berbagai persiapan dilakukan oleh KPU dan partai politik. Isu-isu seperti keamanan, logistik, dan transparansi menjadi fokus utama untuk memastikan Pemilu berjalan lancar dan adil. Berbagai debat dan kampanye terus berlangsung, menarik perhatian publik.",
                    R.drawable.berita2
                ),
                Berita(
                    "Kenaikan Kasus DBD di Indonesia",
                    "Kasus Demam Berdarah Dengue (DBD) di Indonesia meningkat signifikan.",
                    "Kementerian Kesehatan melaporkan lonjakan kasus DBD di beberapa wilayah Indonesia. Penyebab utama peningkatan ini adalah perubahan cuaca dan lingkungan yang mendukung perkembangan nyamuk Aedes aegypti. Pemerintah mengimbau masyarakat untuk melakukan langkah-langkah pencegahan seperti 3M (Menguras, Menutup, dan Mendaur ulang).",
                    R.drawable.berita3
                ),
                Berita(
                    "Debat Capres dan Cawapres Semakin Panas",
                    "Debat calon presiden dan wakil presiden semakin memanas menjelang Pemilu 2024.",
                    "Debat calon presiden dan wakil presiden untuk Pemilu 2024 semakin intens dengan berbagai isu yang diperdebatkan, termasuk ekonomi, keamanan, dan kesejahteraan sosial. Para kandidat berusaha menarik perhatian pemilih dengan memaparkan visi dan misi mereka secara detail.",
                    R.drawable.berita4
                ),
                Berita(
                    "Progres Pembangunan IKN Nusantara",
                    "Pembangunan Ibu Kota Nusantara di Kalimantan Timur terus berlanjut.",
                    "Proyek pembangunan Ibu Kota Nusantara (IKN) di Kalimantan Timur menunjukkan progres signifikan. Presiden Jokowi direncanakan mulai bekerja di IKN pada Juli 2024. Pembangunan ini meliputi infrastruktur dasar dan sistem pertahanan cerdas untuk mendukung operasional kota baru tersebut.",
                    R.drawable.berita5
                ),
                Berita(
                    "Kenaikan Harga Pangan di Pasar Tradisional",
                    "Harga pangan di pasar tradisional mengalami kenaikan.",
                    "Sejumlah pasar tradisional di Indonesia melaporkan kenaikan harga bahan pangan seperti beras, sayur-mayur, dan daging. Penyebab utama kenaikan ini adalah gangguan distribusi dan perubahan cuaca yang mempengaruhi produksi pangan. Pemerintah sedang mencari solusi untuk menstabilkan harga.",
                    R.drawable.berita6
                ),
                Berita(
                    "Pencapaian Timnas Indonesia di Kompetisi Internasional",
                    "Timnas Indonesia meraih kemenangan penting di kompetisi internasional.",
                    "Tim Nasional Sepak Bola Indonesia berhasil meraih kemenangan dalam pertandingan penting melawan Timnas Irak di kompetisi internasional. Kemenangan ini meningkatkan semangat tim dan dukungan dari para penggemar sepak bola di tanah air.",
                    R.drawable.berita7
                ),
                Berita(
                    "Peluncuran Film Baru di Bioskop Indonesia",
                    "Beberapa film baru tayang di bioskop Indonesia pada Mei 2024.",
                    "Mei 2024 menghadirkan berbagai film baru yang tayang di bioskop Indonesia, termasuk film lokal dan internasional. Film-film tersebut mencakup berbagai genre seperti horor, aksi, drama, dan komedi, menawarkan pilihan hiburan yang beragam bagi penonton.",
                    R.drawable.berita8
                ),
                Berita(
                    "Isu Lingkungan dan Perubahan Iklim",
                    "Perubahan iklim menjadi perhatian utama di Indonesia.",
                    "Perubahan iklim terus menjadi isu penting di Indonesia, dengan dampaknya yang semakin nyata seperti peningkatan suhu ekstrem dan gangguan cuaca. Pemerintah dan berbagai organisasi lingkungan hidup terus berupaya mengedukasi masyarakat dan mengimplementasikan kebijakan untuk mitigasi dan adaptasi perubahan iklim.",
                    R.drawable.berita9
                ),
                Berita(
                    "Perkembangan Teknologi di Sektor Pertanian",
                    "Inovasi teknologi mulai diterapkan di sektor pertanian Indonesia.",
                    "Sektor pertanian Indonesia mulai mengadopsi teknologi canggih seperti drone dan IoT untuk meningkatkan efisiensi dan produktivitas. Langkah ini diharapkan dapat membantu petani dalam mengatasi tantangan seperti perubahan iklim dan kekurangan tenaga kerja, serta meningkatkan hasil panen.",
                    R.drawable.berita10
                ),
                Berita(
                    "Dilaporkan Deva mencintai Syifa",
                    "Bagaimana Deva TI 4B bisa mencintai Syifa TI 4A?",
                    "Dalam dunia kampus Politeknik Negeri Jakarta, ada cerita cinta yang begitu manis dan menginspirasi. Berawal dari dua mahasiswa Informatika, Deva Alvyn Budinugraha dari kelas TI 4B dan Syifa Azzahirah dari kelas TI 4A. Keduanya seperti dua bagian puzzle yang akhirnya menemukan tempatnya. Berita tentang cinta mereka menggemparkan dunia maya dan nyata. Bagaimana bisa seorang Deva yang tampan dan berotot, yang hobi workout dan gaming, jatuh hati pada Syifa yang manis dan pintar? ",
                    R.drawable.berita11
                )
            )

            val adapter = BeritaAdapter(requireContext(), beritaList)
            listView.adapter = adapter

            listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                val intent = Intent(activity, DetailBeritaActivity::class.java)
                intent.putExtra("judul", beritaList[position].judul)
                intent.putExtra("deskripsiPanjang", beritaList[position].deskripsiPanjang)
                intent.putExtra("imageResId", beritaList[position].imageResId)
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
                // Show logout confirmation dialog
                showLogoutConfirmationDialog()
            }

            return view
        }

        private fun showLogoutConfirmationDialog() {
            AlertDialog.Builder(requireContext())
                .setTitle("Logout")
                .setMessage("Anda yakin ingin logout?")
                .setPositiveButton("Ya") { dialog, which ->
                    val sharedPreferences = activity?.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                    sharedPreferences?.edit()?.clear()?.apply()
                    Toast.makeText(activity, "Logged out", Toast.LENGTH_SHORT).show()
                    activity?.finish()
                }
                .setNegativeButton("Tidak", null)
                .show()
        }
    }
}
