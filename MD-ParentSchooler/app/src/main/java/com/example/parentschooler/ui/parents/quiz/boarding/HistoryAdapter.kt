package com.example.parentschooler.ui.parents.quiz.boarding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parentschooler.data.soal.DataSoal
import com.example.parentschooler.databinding.ItemHistoryBinding

class HistoryAdapter (private val listHistory : List<DataSoal>) : RecyclerView.Adapter<HistoryAdapter.SoalViewHolder>(){
    class SoalViewHolder (private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind (item: DataSoal){
            if (item.type?.isNotEmpty()!!){
                binding.tvTitleHistory.text = item.type
            }else{
                binding.tvTitleHistory.text = "nA"
            }

            if (item.date?.isNotEmpty()!!){
                binding.tvDateHistory.text = item.date
            }else{
                binding.tvDateHistory.text = "nA"
            }


            if (item.soal13 != null){
                var countSoal = Array(14){0}
                countSoal[0] = item.soal1!!
                countSoal[1] = item.soal2!!
                countSoal[2] = item.soal3!!
                countSoal[3] = item.soal4!!
                countSoal[4] = item.soal5!!
                countSoal[5] = item.soal6!!
                countSoal[6] = item.soal7!!
                countSoal[7] = item.soal8!!
                countSoal[8] = item.soal9!!
                countSoal[9] = item.soal10!!
                countSoal[10] = item.soal11!!
                countSoal[11] = item.soal12!!
                countSoal[12] = item.soal13!!
                countSoal[13] = item.soal14!!

                val persen1 = countSoal.count { it == 1 }.toFloat().div(14.0).times(100.0).toInt()
                val persen2 = countSoal.count { it == 2 }.toFloat().div(14.0).times(100.0).toInt()
                val persen3 = countSoal.count { it == 3 }.toFloat().div(14.0).times(100.0).toInt()

                binding.tvCtp1History.text = "Visual ${persen1}% • Auditorial $persen2% • Kinestik $persen3%"
                binding.tvTitleHistory.text = "Gaya Belajar"

            }else{
                var countSoal = Array(12){0}
                countSoal[0] = item.soal1!!
                countSoal[1] = item.soal2!!
                countSoal[2] = item.soal3!!
                countSoal[3] = item.soal4!!
                countSoal[4] = item.soal5!!
                countSoal[5] = item.soal6!!
                countSoal[6] = item.soal7!!
                countSoal[7] = item.soal8!!
                countSoal[8] = item.soal9!!
                countSoal[9] = item.soal10!!
                countSoal[10] = item.soal11!!
                countSoal[11] = item.soal12!!

                val percent = countSoal.groupingBy { it }.eachCount()
                val persen1 = countSoal.count { it == 1 }.toFloat().div(12.0).times(100.0).toInt()
                val persen2 = countSoal.count { it == 2 }.toFloat().div(12.0).times(100.0).toInt()
                val persen3 = countSoal.count { it == 3 }.toFloat().div(12.0).times(100.0).toInt()
                val persen4 = countSoal.count { it == 4 }.toFloat().div(12.0).times(100.0).toInt()

                when (item.type){
                    "ParStyle" -> {
                        binding.tvCtp1History.text =
                            "Berwibawa $persen1% • Otoriter $persen2% • Serba Membolehkan $persen3% • Lalai $persen4%"
                        binding.tvTitleHistory.text = "Cara Parenting"
                    }
                    "Karakteristik" -> {
                        binding.tvCtp1History.text =
                            "Sanguinis $persen1% • Koleris $persen2% • Plegmatis $persen3% • Melankolis $persen4%"
                        binding.tvTitleHistory.text = "Karakteristik"
                    }
                    else -> binding.tvCtp1History.text = "nA"
                }

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SoalViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SoalViewHolder(binding)
    }

    override fun getItemCount() = listHistory.size

    override fun onBindViewHolder(holder: SoalViewHolder, position: Int) {
        holder.bind(listHistory[position])

    }

}