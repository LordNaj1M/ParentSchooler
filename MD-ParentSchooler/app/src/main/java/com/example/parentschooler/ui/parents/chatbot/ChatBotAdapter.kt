import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.parentschooler.R
import com.example.parentschooler.data.chatbot.ChatBotResponse
import com.example.parentschooler.data.chatbot.OutputResponse

class ChatBotAdapter(private val chatBotResponses: MutableList<ChatBotResponse>) :
    RecyclerView.Adapter<ChatBotAdapter.ChatBotViewHolder>() {

    class ChatBotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvSummary: TextView = itemView.findViewById(R.id.tv_summary)
        val tvCitation: TextView = itemView.findViewById(R.id.tv_citation)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatBotViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_summary, parent, false)
        return ChatBotViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatBotViewHolder, position: Int) {
        val chatBotResponse = chatBotResponses[position]
        holder.tvSummary.text = chatBotResponse.output.summary
        holder.tvCitation.text = chatBotResponse.output.citation
    }

    override fun getItemCount(): Int {
        return chatBotResponses.size
    }

    fun addChatBotResponse(chatBotResponse: OutputResponse) {
        chatBotResponses.add(chatBotResponse)
        notifyDataSetChanged()
    }
}

private fun <E> MutableList<E>.add(element: OutputResponse) {

}
