package com.marcospb.justocallenge.ui.user_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager

import com.marcospb.justocallenge.databinding.FragmentUserListBinding
import com.marcospb.justocallenge.ui.user_list.adapter.UserListAdapter
import com.marcospb.justocallenge.utils.ResourceState
import com.marcospb.justocallenge.utils.hide
import com.marcospb.justocallenge.utils.show
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class UserListFragment : Fragment() {

    private var _binding: FragmentUserListBinding? = null


    private val binding get() = _binding!!


    private val viewModel by viewModels<UserListViewModel>()
    private lateinit var userAdapter: UserListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUserListBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        viewModel.userListLivedata.observe(viewLifecycleOwner, Observer { status ->

            when (status) {
                is ResourceState.Success -> {
                    binding.progressbar.hide()
                    userAdapter.submitList(status.data)
                }

                is ResourceState.Error -> {
                    binding.progressbar.hide()
                }
                is ResourceState.Loading -> {
                    binding.progressbar.show()
                }

            }

        })
    }

    private fun init() {
        userAdapter = UserListAdapter { userClick ->
            val dir = UserListFragmentDirections.actionNavigationHomeToNavigationDetail(userClick)
            findNavController().navigate(dir)
        }
        binding.rvUsers.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvUsers.adapter = userAdapter
        viewModel.getUserList()

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}