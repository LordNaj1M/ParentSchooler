package com.example.parentschooler.ui.teacher

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.parentschooler.R
import com.example.parentschooler.data.helper.MyApplication
import com.example.parentschooler.data.helper.UserViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.SetOptions
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.InputStream

class TeacherActivity : AppCompatActivity() {

    private var db = Firebase.firestore
    private lateinit var userViewModel: UserViewModel
    private lateinit var spinnerKelas: Spinner
    private lateinit var spinnerMapel: Spinner
    private lateinit var spinnerTugas: Spinner
    private lateinit var pickCSV: EditText
    private lateinit var buttonSubmit: Button
    private var kelas = arrayOf("Pilih Kelas", "dummyClass", "Kelas A", "Kelas B")
    private var mapel = arrayOf("Pilih Mapel", "Pendidikan Agama", "Bahasa Indonesia", "Bahasa Inggris", "Matematika", "Ilmu Pengetahuan Alam dan Sosial", "Seni Budaya", "Penjaskes")
    private var tugas = arrayOf("Pilih Tugas", "Tugas 1", "Tugas 2", "UTS", "UAS")
    private var selectedKelas = ""
    private var selectedMapel = ""
    private var selectedTugas = ""
    private lateinit var selectedFileUri: Uri

    private val filePickerLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data: Intent? = result.data
            data?.data?.let { fileUri ->
                selectedFileUri = fileUri
                val fileName = getFileName(fileUri)
                pickCSV.setText(fileName)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_teacher)

        userViewModel = (application as MyApplication).userViewModel

        spinnerKelas = findViewById(R.id.spinnerKelas)
        spinnerMapel = findViewById(R.id.spinnerMapel)
        spinnerTugas = findViewById(R.id.spinnerTugas)
        pickCSV = findViewById(R.id.pickCSV)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        val adapterKelas = ArrayAdapter(this, android.R.layout.simple_spinner_item, kelas)
        spinnerKelas.adapter = adapterKelas
        spinnerKelas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, i: Int, l: Long) {
                selectedKelas = kelas[i]
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        val adapterMapel = ArrayAdapter(this, android.R.layout.simple_spinner_item, mapel)
        spinnerMapel.adapter = adapterMapel
        spinnerMapel.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, i: Int, l: Long) {
                selectedMapel = mapel[i]
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        val adapterTugas = ArrayAdapter(this, android.R.layout.simple_spinner_item, tugas)
        spinnerTugas.adapter = adapterTugas
        spinnerTugas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, i: Int, l: Long) {
                selectedTugas = tugas[i]
            }
            override fun onNothingSelected(adapterView: AdapterView<*>?) {}
        }

        pickCSV.setOnClickListener {
            openFilePicker()
        }

        buttonSubmit.setOnClickListener {
            // Proses CSV hanya jika selectedFileUri sudah terisi
            if (::selectedFileUri.isInitialized) {
                processXLSX(selectedFileUri)
            } else {
                // Minta pengguna untuk memilih file CSV
                pickCSV.error = "Tolong Pilih XLSX nya"
            }
        }
    }

    private fun openFilePicker() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" // Hanya menerima file .xlsx
        }

        filePickerLauncher.launch(intent)
    }

    @SuppressLint("Range")
    private fun getFileName(uri: Uri): String {
        var result = ""
        if (uri.scheme == "content") {
            contentResolver.query(uri, null, null, null, null)?.apply {
                moveToFirst()
                result = getString(getColumnIndex("_display_name"))
                close()
            }
        }
        return result
    }

    private fun processXLSX(fileUri: Uri) {
        val inputStream: InputStream? = contentResolver.openInputStream(fileUri)
        val school = userViewModel.school
        Log.d("MyTag", "userViewModel.school from App is ${userViewModel.school}")
        Log.d("MyTag", "'school' variable now is $school")

        inputStream?.use { stream ->
            val workbook = WorkbookFactory.create(stream)
            val sheet = workbook.getSheetAt(0) // Mengambil sheet pertama

            val headersRow = sheet.getRow(0) // Baris pertama sebagai header
            val nomorIndukIndex = findColumnIndex(headersRow, "NISN")
            val namaAnakIndex = findColumnIndex(headersRow, "Nama")
            val nilaiTugasIndex = findColumnIndex(headersRow, selectedTugas)

            for (i in 1 until sheet.physicalNumberOfRows) { // Mulai dari baris kedua (setelah header)
                val row = sheet.getRow(i)
                val nomorInduk = row.getCell(nomorIndukIndex)?.stringCellValue
                val namaAnak = row.getCell(namaAnakIndex)?.stringCellValue
                // Mengambil nilai tugas dan mengonversi ke string
                val nilaiTugasCell = row.getCell(nilaiTugasIndex)
                val nilaiTugas = when (nilaiTugasCell.cellType) {
                    CellType.STRING -> nilaiTugasCell.stringCellValue
                    CellType.NUMERIC -> nilaiTugasCell.numericCellValue.toString()
                    CellType.BOOLEAN -> nilaiTugasCell.booleanCellValue.toString()
                    CellType.BLANK -> ""
                    else -> ""
                }

                // Cek apakah data yang diperlukan tidak null
                if (nomorInduk != null && namaAnak != null) {
                    val dataMap = mutableMapOf<String, Any>()

                    // Tambahkan nilaiTugas ke dalam map
                    val nilaiMap = mutableMapOf<String, Any>()
                    nilaiMap["childName"] = namaAnak
                    nilaiMap["score"] = nilaiTugas

                    dataMap[nomorInduk] = nilaiMap
                    // Masukkan dataMap ke dalam Firestore
                    val academic = db.collection(school)
                    val destinationDb = academic.document(selectedKelas).collection(selectedMapel).document(selectedTugas)
                    destinationDb.set(dataMap, SetOptions.merge())
                        .addOnSuccessListener {
                            Log.d("MyTag", "Data berhasil ditambahkan ke Firestore")
                        }
                        .addOnFailureListener { exception ->
                            Log.d("MyTag", "Kesalahan saat menambahkan data ke Firestore: ${exception.message}")
                        }
                }
            }
        }
    }

    private fun findColumnIndex(row: Row?, columnName: String): Int {
        if (row != null) {
            for (i in 0 until row.physicalNumberOfCells) {
                val cell = row.getCell(i)
                if (cell != null && cell.stringCellValue == columnName) {
                    return cell.columnIndex
                }
            }
        }
        return -1 // Jika tidak ditemukan
    }
}