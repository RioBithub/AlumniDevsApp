package com.devalvv.alumnidevsapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val emailEditText = findViewById<EditText>(R.id.email)
        val passwordEditText = findViewById<EditText>(R.id.password)
        val loginButton = findViewById<Button>(R.id.loginButton)

        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email == "luvsyifa@devalv.in" && password == "lovesyifa") {
                // Simpan data ke SharedPreferences
                val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
                val editor = sharedPreferences.edit()
                editor.putString("email", email)
                editor.putString("nim", "2207411050")  // NIM kamu
                editor.putString("nama", "Deva Alvyn Budinugraha")  // Nama kamu
                editor.putString("kelas", "TI 4B")  // Kelas kamu
                editor.apply()

                // Login berhasil, navigasi ke DashboardActivity
                val intent = Intent(this, dashboard::class.java)
                startActivity(intent)
                finish()
            } else {
                // Login gagal, tampilkan pesan
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
