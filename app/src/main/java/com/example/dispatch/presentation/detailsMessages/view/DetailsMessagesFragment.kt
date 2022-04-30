package com.example.dispatch.presentation.detailsMessages.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.dispatch.databinding.FragmentDetailsMessagesBinding
import com.example.dispatch.domain.models.Response
import com.example.dispatch.presentation.detailsMessages.DetailsMessagesContract
import com.example.dispatch.presentation.detailsMessages.viewmodel.DetailsMessagesViewModel
import com.example.dispatch.presentation.listUsers.view.ListUsersFragment
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.picasso.transformations.CropCircleTransformation
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class DetailsMessagesFragment : Fragment(), DetailsMessagesContract.DetailsMessagesFragment {
    private lateinit var binding: FragmentDetailsMessagesBinding
    private val viewModel: DetailsMessagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getCompanionUidFromListUsersFragment()
        companionUidObserver()
        companionDetailsObserver()
    }

    override fun getCompanionUidFromListUsersFragment() {
        viewModel._companionUid.value = requireArguments().getString(ListUsersFragment.PARTNER_UID).toString()
    }

    override fun companionUidObserver() {
        viewModel.companionUid.observe(viewLifecycleOwner) { companionUid ->
            if (companionUid.isNotEmpty()) {
                getUserDetailsPublicOnUidObserver(uid = companionUid)
            }
        }
    }

    override fun getUserDetailsPublicOnUidObserver(uid: String) {
        viewModel.getUserDetailsPublicOnUid(uid = uid).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Response.Fail -> {
                    Toast.makeText(activity, "Load user info false :( ", Toast.LENGTH_SHORT).show()
                }
                is Response.Success -> {
                    viewModel._companionDetails.value = result.data
                }
            }
        }
    }

    override fun companionDetailsObserver() {
        viewModel.companionDetails.observe(viewLifecycleOwner) { companionDetails ->
            binding.textViewCompanionFullname.text = companionDetails.fullname
            Picasso.get().load(companionDetails?.photoProfileUrl)
                .transform(CropCircleTransformation())
                .into(binding.imageViewCompanionProfileImage)
        }
    }
}