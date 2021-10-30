package com.example.fetch_rewards_take_home.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fetch_rewards_take_home.R
import com.example.fetch_rewards_take_home.databinding.HomeFragmentBinding
import com.example.fetch_rewards_take_home.model.User
import com.example.fetch_rewards_take_home.util.DataState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: MainViewModel by activityViewModels()
    private lateinit var adapter: UserAdapter
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = HomeFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        setupRecyclerView()
        viewModel.setStateEvent(HomeStateEvent.GetUserEvents)

        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.setStateEvent(HomeStateEvent.GetUserEvents)
        }
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    private fun subscribeObservers() {
        viewModel.dataState.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is DataState.Success<List<User>> -> {
                    displayLoading(false)
                    populateRecyclerView(dataState.data)
                }
                is DataState.Loading -> {
                    displayLoading(true)
                }
                is DataState.Error -> {
                    displayLoading(false)
                    displayError(dataState.exception.message)
                }
            }
        })
    }

    private fun displayLoading(isLoading: Boolean) {
        binding.swipeRefreshLayout.isRefreshing = isLoading
    }

    private fun displayError(message: String?) {
        if (message.isNullOrEmpty()) {
            Toast.makeText(context, "Unknown error", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        }
    }

    private fun populateRecyclerView(users: List<User>) {
        if (users.isNotEmpty()) adapter.setItems(ArrayList(users))
    }
}