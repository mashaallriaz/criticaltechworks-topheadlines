package com.criticaltechworks.topheadlines.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class FragmentWithBinding<V : ViewDataBinding> : Fragment() {
    var binding: V? = null
        private set

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return createBinding(inflater, container, savedInstanceState).also { binding = it }.root
    }

    final override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(requireBinding(), savedInstanceState)
    }

    abstract fun onViewCreated(binding: V, savedInstanceState: Bundle?)

    protected fun requireBinding(): V = requireNotNull(binding)

    protected abstract fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): V

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}