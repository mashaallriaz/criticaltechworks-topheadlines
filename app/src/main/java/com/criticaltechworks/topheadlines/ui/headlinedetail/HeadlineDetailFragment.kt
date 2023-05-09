package com.criticaltechworks.topheadlines.ui.headlinedetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.criticaltechworks.topheadlines.databinding.FragmentSimpleListBinding
import com.criticaltechworks.topheadlines.ui.FragmentWithBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HeadlineDetailFragment : FragmentWithBinding<FragmentSimpleListBinding>() {

    @Inject
    internal lateinit var controller: HeadlineDetailEpoxyController

    private val viewModel: HeadlineDetailViewModel by viewModels()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): FragmentSimpleListBinding {
        return FragmentSimpleListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(binding: FragmentSimpleListBinding, savedInstanceState: Bundle?) {
        binding.apply {
            lifecycleOwner = viewLifecycleOwner
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
