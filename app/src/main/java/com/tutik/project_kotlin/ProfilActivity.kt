package com.tutik.project_kotlin

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_profil.*

class ProfilActivity : AppCompatActivity() {
    companion object {
        //set variabel REQUEST_CODE = 100
        val REQUEST_CODE = 100
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profil)
    //memanggil fungsi ambilData
        ambilData()
    //memberi click listener ke tombol edit.
    //jika tombol edit name ditekan, maka akan menjalankan method navigasiKeEditProfil()
        btnEditName.setOnClickListener { navigasiKeEditProfil() }
    //memberi click listener ke tomboll call
        btnCall.setOnClickListener {
            dialPhoneNumber(txtTelp.text.toString()) }
    }
    private fun ambilData() {
        val bundle = intent.extras
    //menangkap data yang dikirimkan oleh MainActivity lalu menyimpannya ke sebuah variabel
        val nama = bundle?.getString("nama")
        val gender = bundle?.getString("gender")
        val email = bundle?.getString("email")
        val telp = bundle?.getString("telp")
        val alamat = bundle?.getString("alamat")
    //set tulisan di tiap TextView dengan data yang kita dapatkan sebelumnya.
        txtName.text = nama
        txtGender.text = gender
        txtEmail.text = email
        txtTelp.text = telp
        txtAddress.text = alamat
    }
    //fungsi untuk berpindah ke EditProfilActivity
    private fun navigasiKeEditProfil() {
        val intent = Intent(this, EditProfilActivity::class.java)
    // mengirimkan data dengan keyName "nama"
        val namaUser = txtName.text.toString()
        intent.putExtra("nama", namaUser)
        startActivityForResult(intent, REQUEST_CODE)
    }
    //fungsi untuk menangkap hasil dari proses startActivityForResult
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE){
            if (resultCode == Activity.RESULT_OK) {
    //jika sukses maka data hasil edit kita tampikan ke TextView txtName
                val result = data?.getStringExtra("nama")
                txtName.text = result
            }else{
    //jika gagal maka tampilkan toast
                Toast.makeText(this, "Edit failed",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
    //fungsi untuk melakukan dial
    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL).apply {
            data = Uri.parse("tel:$phoneNumber")
        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }
}