package com.devalvv.alumnidevsapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "alumni.db"
        private const val DATABASE_VERSION = 1
        const val TABLE_NAME = "alumni"
        const val COLUMN_ID = "id"
        const val COLUMN_NIM = "nim"
        const val COLUMN_NAMA = "nama"
        const val COLUMN_TEMPAT_LAHIR = "tempat_lahir"
        const val COLUMN_TANGGAL_LAHIR = "tanggal_lahir"
        const val COLUMN_ALAMAT = "alamat"
        const val COLUMN_AGAMA = "agama"
        const val COLUMN_TLP_HP = "tlp_hp"
        const val COLUMN_TAHUN_MASUK = "tahun_masuk"
        const val COLUMN_TAHUN_LULUS = "tahun_lulus"
        const val COLUMN_PEKERJAAN = "pekerjaan"
        const val COLUMN_JABATAN = "jabatan"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NIM TEXT," +
                "$COLUMN_NAMA TEXT," +
                "$COLUMN_TEMPAT_LAHIR TEXT," +
                "$COLUMN_TANGGAL_LAHIR TEXT," +
                "$COLUMN_ALAMAT TEXT," +
                "$COLUMN_AGAMA TEXT," +
                "$COLUMN_TLP_HP TEXT," +
                "$COLUMN_TAHUN_MASUK TEXT," +
                "$COLUMN_TAHUN_LULUS TEXT," +
                "$COLUMN_PEKERJAAN TEXT," +
                "$COLUMN_JABATAN TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addAlumni(nim: String, nama: String, tempatLahir: String, tanggalLahir: String, alamat: String, agama: String, tlpHp: String, tahunMasuk: String, tahunLulus: String, pekerjaan: String, jabatan: String): Long {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NIM, nim)
        values.put(COLUMN_NAMA, nama)
        values.put(COLUMN_TEMPAT_LAHIR, tempatLahir)
        values.put(COLUMN_TANGGAL_LAHIR, tanggalLahir)
        values.put(COLUMN_ALAMAT, alamat)
        values.put(COLUMN_AGAMA, agama)
        values.put(COLUMN_TLP_HP, tlpHp)
        values.put(COLUMN_TAHUN_MASUK, tahunMasuk)
        values.put(COLUMN_TAHUN_LULUS, tahunLulus)
        values.put(COLUMN_PEKERJAAN, pekerjaan)
        values.put(COLUMN_JABATAN, jabatan)
        return db.insert(TABLE_NAME, null, values)
    }

    fun getAllAlumni(): ArrayList<HashMap<String, String>> {
        val db = this.readableDatabase
        val list = ArrayList<HashMap<String, String>>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.moveToFirst()) {
            do {
                val map = HashMap<String, String>()
                map[COLUMN_ID] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
                map[COLUMN_NIM] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM))
                map[COLUMN_NAMA] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA))
                list.add(map)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return list
    }

    fun getAlumni(id: String): HashMap<String, String> {
        val db = this.readableDatabase
        val map = HashMap<String, String>()
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID=?", arrayOf(id))
        if (cursor.moveToFirst()) {
            map[COLUMN_ID] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ID))
            map[COLUMN_NIM] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NIM))
            map[COLUMN_NAMA] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAMA))
            map[COLUMN_TEMPAT_LAHIR] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TEMPAT_LAHIR))
            map[COLUMN_TANGGAL_LAHIR] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TANGGAL_LAHIR))
            map[COLUMN_ALAMAT] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ALAMAT))
            map[COLUMN_AGAMA] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AGAMA))
            map[COLUMN_TLP_HP] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TLP_HP))
            map[COLUMN_TAHUN_MASUK] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_MASUK))
            map[COLUMN_TAHUN_LULUS] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TAHUN_LULUS))
            map[COLUMN_PEKERJAAN] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_PEKERJAAN))
            map[COLUMN_JABATAN] = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_JABATAN))
        }
        cursor.close()
        return map
    }

    fun updateAlumni(id: String, nim: String, nama: String, tempatLahir: String, tanggalLahir: String, alamat: String, agama: String, tlpHp: String, tahunMasuk: String, tahunLulus: String, pekerjaan: String, jabatan: String): Int {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(COLUMN_NIM, nim)
        values.put(COLUMN_NAMA, nama)
        values.put(COLUMN_TEMPAT_LAHIR, tempatLahir)
        values.put(COLUMN_TANGGAL_LAHIR, tanggalLahir)
        values.put(COLUMN_ALAMAT, alamat)
        values.put(COLUMN_AGAMA, agama)
        values.put(COLUMN_TLP_HP, tlpHp)
        values.put(COLUMN_TAHUN_MASUK, tahunMasuk)
        values.put(COLUMN_TAHUN_LULUS, tahunLulus)
        values.put(COLUMN_PEKERJAAN, pekerjaan)
        values.put(COLUMN_JABATAN, jabatan)
        return db.update(TABLE_NAME, values, "$COLUMN_ID=?", arrayOf(id))
    }

    fun deleteAlumni(id: String): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_ID=?", arrayOf(id))
    }
}
