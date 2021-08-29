package com.example.kpm.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kpm.R
import com.example.kpm.databinding.FragmentProfileLoggedInBinding
import com.example.kpm.databinding.FragmentProfileNotLoggedInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {
    private lateinit var bindingLoggedIn: FragmentProfileLoggedInBinding
    private lateinit var bindingNotLoggedIn: FragmentProfileNotLoggedInBinding
    private val viewModel: ProfileViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bindingLoggedIn = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile_logged_in,
            container,
            false)
        bindingNotLoggedIn = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_profile_not_logged_in,
            container,
            false
        )
        bindingLoggedIn.lifecycleOwner = this
        bindingNotLoggedIn.lifecycleOwner = this
        return if (viewModel.isAuthorized()){
            bindingLoggedIn.root
        }else{
            bindingNotLoggedIn.root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setObservers()
        if(viewModel.isAuthorized()){
            viewModel.getUserData()
            viewModel.getLastFourNumbersPhone()
        }
    }

    private fun setObservers() {
        if (viewModel.isAuthorized()){
            viewModel.userData.observe(viewLifecycleOwner){
                bindingLoggedIn.user = it
            }
            viewModel.phoneNumber.observe(viewLifecycleOwner){
                bindingLoggedIn.phoneNumber = it
            }

        }
    }

    private fun initView() {
        bindingNotLoggedIn.loginButton.setOnClickListener{
            Log.i("yernar", "initView: asdasd")

            val action = ProfileFragmentDirections.toLogin()
            findNavController().navigate(action)
        }
        bindingLoggedIn.logoutButton.setOnClickListener{
            viewModel.logout()
            val action = ProfileFragmentDirections.toProfile()
            findNavController().navigate(action)
        }

    }
}