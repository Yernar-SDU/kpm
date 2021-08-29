package com.example.kpm.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.kpm.R
import com.example.kpm.databinding.FragmentDialogLoginBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class LoginDialogFragment: DialogFragment() {
    private lateinit var binding: FragmentDialogLoginBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dialog_login, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    private fun initView() {
        binding.loginButton.setOnClickListener{
            val navigationView = requireActivity().findViewById<View>(R.id.nav_view) as BottomNavigationView
            navigationView.selectedItemId = R.id.profile_tab
            this.dismiss()
        }
    }
}