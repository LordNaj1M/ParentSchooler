package com.example.parentschooler.ui.parents.quiz.soal

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.example.parentschooler.R
import com.example.parentschooler.data.helper.DateHelper
import com.example.parentschooler.data.helper.ViewModelFactory
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.databinding.ActivityKarakteristikBinding
import com.example.parentschooler.ui.parents.quiz.pie.DiagramActivity

class KarakteristikActivity : AppCompatActivity() {

    private lateinit var binding: ActivityKarakteristikBinding
    private val soalViewModel by viewModels<KuesionerViewModel> {
        ViewModelFactory.getInstance(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityKarakteristikBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButton()
    }

    private fun setButton() {

        var Soal1 : Int = 0
        var Soal2 : Int = 0
        var Soal3 : Int = 0
        var Soal4 : Int = 0
        var Soal5 : Int = 0
        var Soal6 : Int = 0
        var Soal7 : Int = 0
        var Soal8 : Int = 0
        var Soal9 : Int = 0
        var Soal10 : Int = 0
        var Soal11 : Int = 0
        var Soal12 : Int = 0

        binding.cvSoal1Opsi1.setOnClickListener{
            Soal1 = 1
            binding.cvSoal1Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal1Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal1Opsi2.setOnClickListener{
            Soal1 = 2
            binding.cvSoal1Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal1Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal1Opsi3.setOnClickListener{
            Soal1 = 3
            binding.cvSoal1Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal1Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal1Opsi4.setOnClickListener {
            Soal1 = 4
            binding.cvSoal1Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal1Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal2Opsi1.setOnClickListener{
            Soal2 = 1
            binding.cvSoal2Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal2Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal2Opsi2.setOnClickListener{
            Soal2 = 2
            binding.cvSoal2Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal2Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal2Opsi3.setOnClickListener{
            Soal2 = 3
            binding.cvSoal2Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal2Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal2Opsi4.setOnClickListener {
            Soal2 = 4
            binding.cvSoal2Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal2Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal3Opsi1.setOnClickListener{
            Soal3 = 1
            binding.cvSoal3Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal3Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal3Opsi2.setOnClickListener{
            Soal3 = 2
            binding.cvSoal3Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal3Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal3Opsi3.setOnClickListener{
            Soal3 = 3
            binding.cvSoal3Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal3Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal3Opsi4.setOnClickListener {
            Soal3 = 4
            binding.cvSoal3Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal3Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal4Opsi1.setOnClickListener{
            Soal4 = 1
            binding.cvSoal4Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal4Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal4Opsi2.setOnClickListener{
            Soal4 = 2
            binding.cvSoal4Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal4Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal4Opsi3.setOnClickListener{
            Soal4 = 3
            binding.cvSoal4Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal4Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal4Opsi4.setOnClickListener {
            Soal4 = 4
            binding.cvSoal4Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal4Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal5Opsi1.setOnClickListener{
            Soal5 = 1
            binding.cvSoal5Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal5Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal5Opsi2.setOnClickListener{
            Soal5 = 2
            binding.cvSoal5Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal5Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal5Opsi3.setOnClickListener{
            Soal5 = 3
            binding.cvSoal5Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal5Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal5Opsi4.setOnClickListener {
            Soal5 = 4
            binding.cvSoal5Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal5Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal6Opsi1.setOnClickListener{
            Soal6 = 1
            binding.cvSoal6Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal6Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal6Opsi2.setOnClickListener{
            Soal6 = 2
            binding.cvSoal6Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal6Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal6Opsi3.setOnClickListener{
            Soal6 = 3
            binding.cvSoal6Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal6Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal6Opsi4.setOnClickListener {
            Soal6 = 4
            binding.cvSoal6Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal6Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal7Opsi1.setOnClickListener{
            Soal7 = 1
            binding.cvSoal7Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal7Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal7Opsi2.setOnClickListener{
            Soal7 = 2
            binding.cvSoal7Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal7Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal7Opsi3.setOnClickListener{
            Soal7 = 3
            binding.cvSoal7Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal7Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal7Opsi4.setOnClickListener {
            Soal7 = 4
            binding.cvSoal7Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal7Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal8Opsi1.setOnClickListener{
            Soal8 = 1
            binding.cvSoal8Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal8Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal8Opsi2.setOnClickListener{
            Soal8 = 2
            binding.cvSoal8Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal8Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal8Opsi3.setOnClickListener{
            Soal8 = 3
            binding.cvSoal8Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal8Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal8Opsi4.setOnClickListener {
            Soal8 = 4
            binding.cvSoal8Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal8Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal9Opsi1.setOnClickListener{
            Soal9 = 1
            binding.cvSoal9Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal9Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal9Opsi2.setOnClickListener{
            Soal9 = 2
            binding.cvSoal9Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal9Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal9Opsi3.setOnClickListener{
            Soal9 = 3
            binding.cvSoal9Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal9Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal9Opsi4.setOnClickListener {
            Soal9 = 4
            binding.cvSoal9Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal9Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal10Opsi1.setOnClickListener{
            Soal10 = 1
            binding.cvSoal10Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal10Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal10Opsi2.setOnClickListener{
            Soal10 = 2
            binding.cvSoal10Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal10Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal10Opsi3.setOnClickListener{
            Soal10 = 3
            binding.cvSoal10Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal10Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal10Opsi4.setOnClickListener {
            Soal10 = 4
            binding.cvSoal10Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal10Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal11Opsi1.setOnClickListener{
            Soal11 = 1
            binding.cvSoal11Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal11Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal11Opsi2.setOnClickListener{
            Soal11 = 2
            binding.cvSoal11Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal11Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal11Opsi3.setOnClickListener{
            Soal11 = 3
            binding.cvSoal11Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal11Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal11Opsi4.setOnClickListener {
            Soal11 = 4
            binding.cvSoal11Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal11Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.cvSoal12Opsi1.setOnClickListener{
            Soal12 = 1
            binding.cvSoal12Opsi1.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal12Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal12Opsi2.setOnClickListener{
            Soal12 = 2
            binding.cvSoal12Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi2.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal12Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal12Opsi3.setOnClickListener{
            Soal12 = 3
            binding.cvSoal12Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi3.setCardBackgroundColor(Color.YELLOW)
            binding.cvSoal12Opsi4.setCardBackgroundColor(Color.WHITE)
        }
        binding.cvSoal12Opsi4.setOnClickListener {
            Soal12 = 4
            binding.cvSoal12Opsi1.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi2.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi3.setCardBackgroundColor(Color.WHITE)
            binding.cvSoal12Opsi4.setCardBackgroundColor(Color.YELLOW)
        }

        binding.buttonSubmit.setOnClickListener {
            if (Soal1 == 0 || Soal2 == 0 ||Soal3 == 0 ||Soal4 == 0 ||Soal5 == 0 ||Soal6 == 0 || Soal7 == 0 || Soal8 == 0 ||Soal9 == 0 ||Soal10 == 0 ||Soal11 == 0 ||Soal12 == 0 ){
                Toast.makeText(this, "Mohon untuk mengisi semua pertanyaan yang disediakan.", Toast.LENGTH_SHORT).show()
            } else{
                val data = DataSoal()
                data.let {
                    it.soal1 = Soal1
                    it.soal2 = Soal2
                    it.soal3 = Soal3
                    it.soal4 = Soal4
                    it.soal5 = Soal5
                    it.soal6 = Soal6
                    it.soal7 = Soal7
                    it.soal8 = Soal8
                    it.soal9 = Soal9
                    it.soal10 = Soal10
                    it.soal11 = Soal11
                    it.soal12 = Soal12
                    it.date = DateHelper.getCurrentDate()
                    it.type = "Karakteristik"
                }
                soalViewModel.insert(data)

                val intent = Intent(this, DiagramActivity::class.java )
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                intent.putExtra("intentSoal",3)
                startActivity(intent)
                finish()
            }
        }
    }
}