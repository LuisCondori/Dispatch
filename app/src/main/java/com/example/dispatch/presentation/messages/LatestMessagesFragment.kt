package com.example.dispatch.presentation.messages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.dispatch.databinding.FragmentLatestMessagesBinding
import com.example.dispatch.domain.models.FbResponse
import com.example.dispatch.domain.models.UserDetails
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi


@AndroidEntryPoint
@ExperimentalCoroutinesApi
class LatestMessagesFragment : Fragment() {
    private lateinit var binding: FragmentLatestMessagesBinding
    private val viewModel: LatestMessagesViewModel by viewModels()
    private var userDetails: UserDetails = UserDetails()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLatestMessagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getCurrentUserDetailsObserve()
    }

    private fun getCurrentUserDetailsObserve() {
        viewModel.getCurrentUserDetails().observe(viewLifecycleOwner) { result ->
            when (result) {
                is FbResponse.Loading -> {
                    showProgressBarLoadUserDetails(showOrNo = true)
                }
                is FbResponse.Fail -> {
                    Toast.makeText(activity, "Load user info false :( ", Toast.LENGTH_SHORT).show()
                    showProgressBarLoadUserDetails(showOrNo = false)
                }
                is FbResponse.Success -> {
                    userDetails = result.data

                    binding.textViewProfileFullname.text = userDetails.fullname
                    Glide.with(this)
                        .load(userDetails.photoProfileUrl)
                        .fitCenter()
                        .into(binding.shapeableImageViewProfileImage)
                    showProgressBarLoadUserDetails(showOrNo = false)
                }
            }
        }
    }

    private fun showProgressBarLoadUserDetails(showOrNo: Boolean) {
        if (showOrNo) {
            binding.textViewProfileFullname.visibility = View.INVISIBLE
            binding.progressBarUserDetailsLoad.visibility = View.VISIBLE
        } else {
            binding.progressBarUserDetailsLoad.visibility = View.INVISIBLE
            binding.textViewProfileFullname.visibility = View.VISIBLE
        }
    }
}