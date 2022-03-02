package com.example.loginsample.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.loginsample.auth.ui.HomeActivity
import com.example.loginsample.auth.ui.login.AuthViewModel
import com.example.loginsample.base.BaseFragment
import com.example.loginsample.databinding.FragmentLoginBinding
import com.example.loginsample.data.network.AuthApi
import com.example.loginsample.data.network.Resource
import com.example.loginsample.data.repositories.AuthRepository
import com.example.loginsample.utils.enable
import com.example.loginsample.utils.startNewActivity
import com.example.loginsample.utils.visible
import kotlinx.coroutines.launch


class LoginFragment : BaseFragment<AuthViewModel, FragmentLoginBinding, AuthRepository>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.visible(false)
        binding.btnLogin.enable(false)

        // observe for our data
        viewModel.loginResponse.observe(viewLifecycleOwner , Observer {
            binding.progressBar.visible(false)
            when(it){

                is Resource.Success ->{
                    //Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                        viewModel.saveToken(it.value.Obj.Token)

                        requireActivity().startNewActivity(HomeActivity::class.java)
                        Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
                }
                is Resource.Failure ->{

                        Toast.makeText(requireContext(), it.errorBody.toString(), Toast.LENGTH_SHORT).show()


                }
            }
        })


        // this to validate if fields of user name and password are empty or not
        // if empty the value of boolean will revalued to false(to disable button) and
        // if are not empty will enable the button
        binding.password.addTextChangedListener {
            val userName = binding.username.text.toString().trim()
            binding.btnLogin.enable(userName.isNotEmpty() && it.toString().trim().isNotEmpty())
        }

        binding.btnLogin.setOnClickListener {
            val userName = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

                binding.progressBar.visible(true)
                // here we call the api
                viewModel.login(userName, password)

        }

    }

    override fun getFragmentLayout(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = FragmentLoginBinding.inflate(inflater,container,false)

    override fun getViewModel() = AuthViewModel::class.java

    override fun getRepository() = AuthRepository(remoteDataSource.buildApi(AuthApi::class.java), userPreferences)


}