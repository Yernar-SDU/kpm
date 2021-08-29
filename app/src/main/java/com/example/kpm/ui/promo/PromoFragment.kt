package com.example.kpm.ui.promo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kpm.R
import com.example.kpm.databinding.FragmentPromoBinding
import com.example.kpm.ui.promo.adapter.PromoAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PromoFragment: Fragment(), PromoAdapter.PromoClickListener {
    private lateinit var binding: FragmentPromoBinding
    private val promoAdapter = PromoAdapter(this)
    private val viewModel: PromoViewModel by  viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_promo, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.i("yernar", "onViewCreated: ")
        setObservers()
        initView()
        viewModel.soap()

    }

    private fun initView() {
        binding.promosRecycler.adapter = promoAdapter
        binding.promosRecycler.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

    }

    private fun setObservers() {
        viewModel.tokenIsValid.observe(viewLifecycleOwner){
            Log.i("yernar", "setObservers: $it")
        }
        viewModel.promos.observe(viewLifecycleOwner){
            promoAdapter.setData(it)
        }
    }

    override fun onPromoButtonClicked(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(link))
        startActivity(intent)
    }
}