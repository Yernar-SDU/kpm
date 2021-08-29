package com.example.kpm.ui.archive

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpm.R
import com.example.kpm.databinding.FragmentArchivesBinding
import com.example.kpm.databinding.FragmentPromoBinding
import com.example.kpm.ui.archive.adapter.ArchiveAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArchiveFragment: Fragment() {
    private val TAG = javaClass.simpleName.toString()
    private lateinit var binding: FragmentArchivesBinding
    private val viewModel: ArchiveViewModel by viewModels()
    private val archiveAdapter = ArchiveAdapter()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_archives, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (!viewModel.isAuthorized()){
            val action = ArchiveFragmentDirections.toLoginDialogFragment()
            findNavController().navigate(action)
            return
        }
        initView()
        setObservers()
    }

    private fun setObservers() {
        viewModel.promos.observe(viewLifecycleOwner){
            archiveAdapter.setData(it)
        }
        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                val action = ArchiveFragmentDirections.toLoadingDialogFragment()
                findNavController().navigate(action)
            }else{
                findNavController().popBackStack(R.id.loading_dialog_fragment,true)
            }

        }
    }

    private fun initView() {
        binding.archiveRecycler.adapter = archiveAdapter
        binding.archiveRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        binding.showButton.setOnClickListener{
            Log.i("authyernar", "setupBottomNavigationBar show button clicked: ${viewModel.isAuthorized()}")
            if (!viewModel.isAuthorized()){
                val action = ArchiveFragmentDirections.toLoginDialogFragment()
                findNavController().navigate(action)
                return@setOnClickListener
            }
            val from = binding.actualTimeEditText1.text.toString()
            val to = binding.actualTimeEditText2.text.toString()
            val pairs = binding.valuta1.selectedItem.toString() + "," + binding.valuta2.selectedItem.toString()
            viewModel.getArchives(pairs,from, to)
        }
    }
}