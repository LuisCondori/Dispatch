package com.abinar.apps.android.driver.presentation.latestMessages.view

import android.view.View
import com.abinar.apps.android.driver.R
import com.abinar.apps.android.driver.databinding.ItemContainerLatestMessageBinding
import com.abinar.apps.android.driver.domain.models.Message
import com.abinar.apps.android.driver.domain.models.UserDetailsPublic
import com.squareup.picasso.Picasso
import com.xwray.groupie.viewbinding.BindableItem
import jp.wasabeef.picasso.transformations.CropCircleTransformation

class LatestMessageItem(
    val message: Message,
    val companionUser: UserDetailsPublic
) : BindableItem<ItemContainerLatestMessageBinding>() {
    override fun bind(viewBinding: ItemContainerLatestMessageBinding, position: Int) {
        viewBinding.latestMessage.text = message.englishMessage
        viewBinding.textViewUserName.text = companionUser.fullname
        if (companionUser.photoProfileUrl.isNotEmpty()) {
            Picasso.get().load(companionUser.photoProfileUrl).transform(CropCircleTransformation())
                .into(viewBinding.imageViewUserPhoto)
        }
    }

    override fun getLayout(): Int {
        return R.layout.item_container_latest_message
    }

    override fun initializeViewBinding(view: View): ItemContainerLatestMessageBinding {
        return ItemContainerLatestMessageBinding.bind(view)
    }
}