package com.example.ebroapp.view.base

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.ebroapp.R
import com.example.ebroapp.di.Injector

abstract class BaseFragment<Binding : ViewBinding, ViewModel : androidx.lifecycle.ViewModel>(
    private val viewModelType: Class<ViewModel>
) : Fragment() {

    private var _binding: Binding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> Binding

    protected val binding: Binding
        get() = _binding as Binding

    protected val viewModel: ViewModel by lazy {
        Injector.provideViewModel(this, viewModelType)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        val transitionInflater = TransitionInflater.from(requireContext())
        enterTransition = transitionInflater.inflateTransition(R.transition.slide_left)
        exitTransition = transitionInflater.inflateTransition(R.transition.fade)

        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    abstract fun setupUI()
}