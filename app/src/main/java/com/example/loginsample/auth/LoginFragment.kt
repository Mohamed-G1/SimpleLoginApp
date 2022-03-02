package com.example.loginsample.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.loginsample.R
import com.example.loginsample.auth.ui.login.AuthViewModel
import com.example.loginsample.base.BaseFragment
import com.example.loginsample.databinding.FragmentLoginBinding
import com.example.loginsample.network.AuthApi
import com.example.loginsample.network.Resource
import com.example.loginsample.repositories.AuthRepository


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // observe for our data
        viewModel.loginResponse.observe(viewLifecycleOwner , Observer {
            when(it){
                is Resource.Success ->{
                    Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure ->{
                    Toast.makeText(requireContext(), "Login Failure", Toast.LENGTH_SHORT).show()
                }
            }
        })


        binding.btnLogin.setOnClickListener {
            val userName = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            //@todo add input validations
            // here we call the api
            viewModel.login(userName , password)

        }

    }

    override fun getFragmentLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getViewModel() = AuthViewModel::class.java

    override fun getRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java))


}