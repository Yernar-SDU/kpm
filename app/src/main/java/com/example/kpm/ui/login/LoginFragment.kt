package com.example.kpm.ui.login

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.kpm.R
import com.example.kpm.databinding.FragmentLoginPageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class LoginFragment: Fragment() {
    private lateinit var binding: FragmentLoginPageBinding
    private val viewModel: LoginViewModel by viewModels()
    private var observerFirstCall = true
    private var firstEnter = true
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_page, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        setObservers()
    }

    private fun setObservers() {
        viewModel.result.observe(viewLifecycleOwner){
            if (observerFirstCall){
                observerFirstCall = false
                return@observe
            }
            if (it){
                binding.incorrectUsernamePasswdTextview.visibility = View.INVISIBLE
            }else{
                binding.incorrectUsernamePasswdTextview.visibility = View.VISIBLE
            }
        }

        viewModel.loading2.observe(viewLifecycleOwner){
            if (firstEnter){
                firstEnter = false
                return@observe
            }
            if (it){
                val action = LoginFragmentDirections.toLoadingDialogFragment()
                findNavController().navigate(action)
            }
            else{
                findNavController().popBackStack()

                if(viewModel.tokenPartner.value!!.isNotEmpty() && viewModel.userValidationResult.value!!.result){
                    viewModel.setAuthorized(true)
                    val action = LoginFragmentDirections.toProfile()
                    findNavController().navigate(action)
                }
            }
        }
    }

    private fun initView() {
        binding.loginButton.setOnClickListener{
            if (binding.editTextEmail.text.isEmpty() || binding.editTextPassword.text.isEmpty()){
                Toast.makeText(context, "Please fill all fields you see", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            viewModel.loginValidate(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
            viewModel.loginValidatePartner(binding.editTextEmail.text.toString(), binding.editTextPassword.text.toString())
        }
    }
}