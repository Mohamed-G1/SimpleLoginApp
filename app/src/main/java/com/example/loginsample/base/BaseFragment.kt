package com.example.loginsample.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.loginsample.data.UserPreferences
import com.example.loginsample.data.network.RemoteDataSource

abstract class BaseFragment<VM : ViewModel, B : ViewBinding, R : BaseRepository> : Fragment() {
    protected lateinit var binding: B
    protected lateinit var viewModel: VM
    protected lateinit var baseRepository: R
    protected var remoteDataSource = RemoteDataSource()
    protected lateinit var userPreferences : UserPreferences


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        userPreferences = UserPreferences(requireContext())

        binding = getFragmentLayout(inflater, container)
        val baseViewModel = BaseViewModel(getRepository())
        viewModel = ViewModelProvider(this, baseViewModel).get(getViewModel())
        return binding.root
    }

    abstract fun getFragmentLayout(inflater: LayoutInflater, container: ViewGroup?): B
    abstract fun getViewModel(): Class<VM>
    abstract fun getRepository(): R
}