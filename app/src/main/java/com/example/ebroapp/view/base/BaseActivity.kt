package com.example.ebroapp.view.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import com.example.ebroapp.di.Injector

abstract class BaseActivity<Binding : ViewBinding, ViewModel : androidx.lifecycle.ViewModel>(
    private val viewModelType: Class<ViewModel>
) : AppCompatActivity() {

    private var _binding: Binding? = null
    abstract val bindingInflater: (LayoutInflater) -> Binding

    protected val viewModel: ViewModel by lazy {
        Injector.provideViewModel(this, viewModelType)
    }
    protected val binding: Binding
        get() = _binding as Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(requireNotNull(_binding).root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, binding.root).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
        setupUI()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun setupUI()
}