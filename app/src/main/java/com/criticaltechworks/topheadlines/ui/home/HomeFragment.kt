package com.criticaltechworks.topheadlines.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.criticaltechworks.topheadlines.core.delegate.observeEvent
import com.criticaltechworks.topheadlines.data.models.Article
import com.criticaltechworks.topheadlines.databinding.FragmentSimpleListBinding
import com.criticaltechworks.topheadlines.ui.FragmentWithBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : FragmentWithBinding<FragmentSimpleListBinding>() {

    @Inject
    internal lateinit var controller: HomeEpoxyController

    private val viewModel: HomeViewModel by viewModels()

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

        controller.callbacks = object : HomeEpoxyController.Callbacks {
            override fun onClickArticle(article: Article) {
                viewModel.navigateToHeadlineDetail(article)
            }
        }

        registerObservers()
    }

    private fun registerObservers() {
        viewModel.liveState.observe(viewLifecycleOwner) {
            controller.state = it
        }

        viewModel.error.observeEvent(viewLifecycleOwner) {
            Snackbar.make(requireBinding().root, it, Snackbar.LENGTH_LONG).show()
        }

        viewModel.navigation.observeEvent(viewLifecycleOwner) { nav ->
            when (nav) {
                is HomeViewModel.HomeNavigation.HeadlineDetail -> {
                    findNavController().navigate(
                        HomeFragmentDirections.actionHeadlineDetailFragment(nav.article)
                    )
                }
            }
        }
    }
}
