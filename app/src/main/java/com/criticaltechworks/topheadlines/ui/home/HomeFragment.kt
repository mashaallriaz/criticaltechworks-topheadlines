package com.criticaltechworks.topheadlines.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.criticaltechworks.topheadlines.databinding.FragmentHomeBinding
import com.criticaltechworks.topheadlines.ui.FragmentWithBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : FragmentWithBinding<FragmentHomeBinding>() {

    @Inject
    internal lateinit var controller: HomeEpoxyController

    private val viewModel: HomeViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(binding: FragmentHomeBinding, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            vm = viewModel
            recyclerView.setController(controller)
        }

        registerObservers()
    }

    private fun registerObservers() {
        viewModel.liveState.observe(viewLifecycleOwner) {
            controller.state = it
        }
    }
}
