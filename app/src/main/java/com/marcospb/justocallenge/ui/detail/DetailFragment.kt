package com.marcospb.justocallenge.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.marcospb.justocallenge.R
import com.marcospb.justocallenge.databinding.FragmentDetailBinding
import com.marcospb.justocallenge.domain.models.UserDomain
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    lateinit var user: UserDomain

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
        user = DetailFragmentArgs.fromBundle(requireArguments()).userDetail

        setUserInfo()
    }

    private fun setUserInfo() {
        Glide.with(requireContext())
            .load(user.photo)
            .into(binding.shapeableImageView2)


        binding.username.text = user.username
        binding.tvCity.text = getString(R.string.city_string_place, user.city)
        binding.tvState.text = getString(R.string.state_string_place, user.state)
        binding.tvEmail.text = getString(R.string.email_string_place, user.email)
        binding.tvPhone.text = getString(R.string.phone_string_place, user.phone)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        //_binding = null
    }
}