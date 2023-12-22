package com.example.parentschooler.ui.parents.quiz.pie

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.parentschooler.R
import com.example.parentschooler.data.helper.ViewModelFactory
import com.example.parentschooler.databinding.ActivityDiagramBinding
import com.example.parentschooler.ui.parents.chatbot.ChatbotActivity
import com.example.parentschooler.ui.parents.dashboard.DashboardActivity
import com.example.parentschooler.ui.parents.homepage.MainActivity
import com.example.parentschooler.ui.parents.quiz.pie.DiagramViewModel
import com.example.parentschooler.ui.parents.quiz.soal.GayaBelajarActivity
import com.example.parentschooler.ui.parents.quiz.soal.KarakteristikActivity
import com.example.parentschooler.ui.parents.quiz.soal.ParStyleActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry

class DiagramActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDiagramBinding
    private val diagViewModel by viewModels<DiagramViewModel> {
        ViewModelFactory.getInstance(application)
    }
    private lateinit var soalChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiagramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setPage()
        setViewModel()

        bottomNav()

    }

    private fun setPage() {
        val intentSoal = intent.getIntExtra("intentSoal", 1)
        when (intentSoal){
            1->{
                binding.tvTittle.text = "Cara Perenting"
                binding.tvSaran.text = getString(R.string.stringParStyle)
                binding.buttonTake.setOnClickListener {
                    val intent = Intent(this, ParStyleActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            2->{
                binding.tvTittle.text = "Gaya Belajar"
                binding.tvSaran.text = getString(R.string.stringGayaBelajar)
                binding.buttonTake.setOnClickListener {
                    val intent = Intent(this, GayaBelajarActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
            else->{
                binding.tvTittle.text = "Karakteristik"
                binding.tvSaran.text = getString(R.string.stringKarakteristik)
                binding.buttonTake.setOnClickListener {
                    val intent = Intent(this, KarakteristikActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setViewModel() {
        val intentSoal = intent.getIntExtra("intentSoal", 1)
        var type  = "ParStyle"
        when (intentSoal){
            1 -> type = "ParStyle"
            2 -> type = "GayaBelajar"
            3 -> type = "Karakteristik"
        }
        diagViewModel.getFirst(type).observe(this){
            if (it == null){
                binding.buttonTake.text = "TAKE"
                binding.tvResultTitle.visibility = View.GONE
                binding.tvTittle.visibility = View.GONE
                binding.cvSaran.visibility = View.GONE
                binding.pcProgressChart.visibility = View.GONE
            } else {
                binding.buttonTake.text = "RE-TAKE"
                binding.tvBelumKuis.visibility = View.GONE
                if (it.soal13 != null){
                    var countSoal = Array(14){0}
                    countSoal[0] = it.soal1!!
                    countSoal[1] = it.soal2!!
                    countSoal[2] = it.soal3!!
                    countSoal[3] = it.soal4!!
                    countSoal[4] = it.soal5!!
                    countSoal[5] = it.soal6!!
                    countSoal[6] = it.soal7!!
                    countSoal[7] = it.soal8!!
                    countSoal[8] = it.soal9!!
                    countSoal[9] = it.soal10!!
                    countSoal[10] = it.soal11!!
                    countSoal[11] = it.soal12!!
                    countSoal[12] = it.soal13!!
                    countSoal[13] = it.soal14!!

                    val persen1 = countSoal.count { it == 1 }.toFloat().div(14.0).times(100.0).toFloat()
                    val persen2 = countSoal.count { it == 2 }.toFloat().div(14.0).times(100.0).toFloat()
                    val persen3 = countSoal.count { it == 3 }.toFloat().div(14.0).times(100.0).toFloat()

                    val dataPie = ArrayList<PieEntry>()
                    if (persen1.toDouble() != 0.00){
                        dataPie.add(PieEntry(persen1,"Visual"))
                    }
                    if (persen2.toDouble() != 0.00){
                        dataPie.add(PieEntry(persen2,"Auditorial"))
                    }
                    if (persen3.toDouble() != 0.00){
                        dataPie.add(PieEntry(persen3,"Kinestik"))
                    }

                    val colorList = ArrayList<Int>()
                    colorList.add(Color.parseColor("#304567"))
                    colorList.add(Color.parseColor("#309967"))
                    colorList.add(Color.parseColor("#476567"))

                    soalChart = binding.pcProgressChart
                    val pieDataSet = PieDataSet(dataPie,"")
                    pieDataSet.colors = colorList
                    var pieData = PieData(pieDataSet)
                    soalChart.data = pieData
                    val pieDesc = Description()
                    pieDesc.text = "Gaya Belajar"
                    soalChart.description = pieDesc
                    soalChart.invalidate()

                }else{
                    var countSoal = Array(12){0}
                    countSoal[0] = it.soal1!!
                    countSoal[1] = it.soal2!!
                    countSoal[2] = it.soal3!!
                    countSoal[3] = it.soal4!!
                    countSoal[4] = it.soal5!!
                    countSoal[5] = it.soal6!!
                    countSoal[6] = it.soal7!!
                    countSoal[7] = it.soal8!!
                    countSoal[8] = it.soal9!!
                    countSoal[9] = it.soal10!!
                    countSoal[10] = it.soal11!!
                    countSoal[11] = it.soal12!!

                    val persen1 = countSoal.count { it == 1 }.toFloat().div(12.0).times(100.0).toFloat()
                    val persen2 = countSoal.count { it == 2 }.toFloat().div(12.0).times(100.0).toFloat()
                    val persen3 = countSoal.count { it == 3 }.toFloat().div(12.0).times(100.0).toFloat()
                    val persen4 = countSoal.count { it == 4 }.toFloat().div(12.0).times(100.0).toFloat()

                    val colorList = ArrayList<Int>()
                    colorList.add(Color.parseColor("#304567"))
                    colorList.add(Color.parseColor("#309967"))
                    colorList.add(Color.parseColor("#476567"))
                    colorList.add(Color.parseColor("#890567"))

                    when (it.type){
                        "ParStyle" -> {
                            val dataPie = ArrayList<PieEntry>()
                            if (persen1.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen1,"Berwibawa"))
                            }
                            if (persen2.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen2,"Otoriter"))
                            }
                            if (persen3.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen3,"Serba Membolehkan"))
                            }
                            if (persen4.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen4,"Lalai"))
                            }

                            soalChart = binding.pcProgressChart
                            val pieDataSet = PieDataSet(dataPie,"")
                            pieDataSet.colors = colorList
                            var pieData = PieData(pieDataSet)
                            soalChart.data = pieData
                            val pieDesc = Description()
                            pieDesc.text = "Cara Parenting"
                            soalChart.description = pieDesc
                            soalChart.invalidate()

                        }
                        "Karakteristik" -> {
                            val dataPie = ArrayList<PieEntry>()
                            if (persen1.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen1,"Sanguinis"))
                            }
                            if (persen2.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen2,"Koleris"))
                            }
                            if (persen3.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen3,"Plegmatis"))
                            }
                            if (persen4.toDouble() != 0.00){
                                dataPie.add(PieEntry(persen4,"Melankolis"))
                            }

                            soalChart = binding.pcProgressChart
                            val pieDataSet = PieDataSet(dataPie,"")
                            pieDataSet.colors = colorList
                            var pieData = PieData(pieDataSet)
                            soalChart.data = pieData
                            val pieDesc = Description()
                            pieDesc.text = "Karakteristik"
                            soalChart.description = pieDesc
                            soalChart.invalidate()
                        }
                        else -> {
                            val dataPie = ArrayList<PieEntry>()
                            dataPie.add(PieEntry(persen1,"Berwibawa"))
                            dataPie.add(PieEntry(persen2,"Otoriter"))
                            dataPie.add(PieEntry(persen3,"Serba Membolehkan"))
                            dataPie.add(PieEntry(persen3,"Lalai"))

                            soalChart = binding.pcProgressChart
                            val pieDataSet = PieDataSet(dataPie,"")
                            pieDataSet.colors = colorList
                            var pieData = PieData(pieDataSet)
                            soalChart.data = pieData
                            val pieDesc = Description()
                            pieDesc.text = "Cara Parenting"
                            soalChart.description = pieDesc
                            soalChart.invalidate()
                        }
                    }}
            }
        }

    }

    private fun bottomNav(){
        binding.navBottom.selectedItemId = R.id.bnmQuiz
        binding.navBottom.isItemHorizontalTranslationEnabled = true
        binding.navBottom.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bnmHome -> {
                    val intent = Intent(this@DiagramActivity, MainActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.bnmDashboard -> {
                    val intent = Intent(this@DiagramActivity, DashboardActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.bnmChatbot -> {
                    val intent = Intent(this@DiagramActivity, ChatbotActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
