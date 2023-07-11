import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.accers.Constants.RECEIVE_ID
import com.example.accers.Constants.SEND_ID
import com.example.accers.Message
import com.example.accers.databinding.MessageItemBinding

@Suppress("DEPRECATION")
class MessagingAdapter : RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {

    var messagesList = mutableListOf<Message>()

    inner class MessageViewHolder(private val binding: MessageItemBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {

                // Remove message on the item clicked
                messagesList.removeAt(adapterPosition)
                notifyItemRemoved(adapterPosition)
            }
        }

        fun bind(message: Message) {
            when (message.id) {
                SEND_ID -> {
                    binding.tvMessage.apply {
                        text = message.message
                        visibility = android.view.View.VISIBLE
                    }
                    binding.tvBotMessage.visibility = android.view.View.GONE
                }
                RECEIVE_ID -> {
                    binding.tvBotMessage.apply {
                        text = message.message
                        visibility = android.view.View.VISIBLE
                    }
                    binding.tvMessage.visibility = android.view.View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MessageItemBinding.inflate(inflater, parent, false)
        return MessageViewHolder(binding)
    }

    override fun getItemCount(): Int = messagesList.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.bind(messagesList[position])
    }

    fun insertMessage(message: Message) {
        messagesList.add(message)
        notifyItemInserted(messagesList.size - 1)
    }

}
