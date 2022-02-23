package com.example.ebroapp.lowertoolbar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.ebroapp.R
import com.example.ebroapp.base.BaseFragment
import com.example.ebroapp.databinding.FragmentLowerToolbarPlaceholderBinding
import com.example.ebroapp.databinding.FragmentUserInfoBinding

class LowerToolbarFragment : BaseFragment<FragmentLowerToolbarPlaceholderBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_lower_toolbar_placeholder, container, false)
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentLowerToolbarPlaceholderBinding
            = FragmentLowerToolbarPlaceholderBinding::inflate
}