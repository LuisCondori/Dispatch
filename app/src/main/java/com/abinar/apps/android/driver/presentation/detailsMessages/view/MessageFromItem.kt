package com.abinar.apps.android.driver.presentation.detailsMessages.view

import android.view.View
import com.abinar.apps.android.driver.R
import com.abinar.apps.android.driver.databinding.ItemContainerSenderMessageBinding
import com.abinar.apps.android.driver.domain.models.Message
import com.xwray.groupie.viewbinding.BindableItem
import java.sql.Date
import java.sql.Timestamp
import java.text.SimpleDateFormat

class MessageFromItem(private val message: Message) :
    BindableItem<ItemContainerSenderMessageBinding>() {
    override fun bind(viewBinding: ItemContainerSenderMessageBinding, position: Int) {
        viewBinding.textViewMessage.text = message.englishMessage

        val netDate = Date(Timestamp(message.timestamp).time)
        val date = SimpleDateFormat("dd/MM/yy hh:mm a").format(netDate)
        viewBinding.textViewDateTime.text = date

        var textEnglish: Boolean = true
        viewBinding.imageviewTranslated.setOnClickListener {
            if (textEnglish) {
                viewBinding.textViewMessage.text = message.russianMessage
                textEnglish = false
            } else {
                viewBinding.textViewMessage.text = message.englishMessage
                textEnglish = true
            }
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_container_sender_message
    }

    override fun initializeViewBinding(view: View): ItemContainerSenderMessageBinding {
        return ItemContainerSenderMessageBinding.bind(view)
    }
}