package com.eneselcuk.foodapp.ui.detailCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.databinding.FragmentDetailCategoriesBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import com.eneselcuk.foodapp.ui.detailCategories.adapter.CategoriesDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCategories : BaseFragment<FragmentDetailCategoriesBinding>() {

    private val args: DetailCategoriesArgs by navArgs()
    private val viewModelCategories: ViewModelDetailCategories by activityViewModels()
    private lateinit var categoriesAdapter: CategoriesDetailAdapter

    override fun setObserver() {
        binding?.viewModelCategories = viewModelCategories
        categoriesAdapter = CategoriesDetailAdapter(CategoriesDetailAdapter.DetailCategoriesClick {
            viewModelCategories.navigation(it)
        })

        binding?.recyclerViewDetail?.layoutManager = GridLayoutManager(requireContext(), 3)
        binding?.recyclerViewDetail?.adapter = categoriesAdapter

        val name = args.categoires.strCategory
        name?.let {
            viewModelCategories.getCategories(it)
        }

        viewModelCategories.detailCategories.observe(viewLifecycleOwner, {
            categoriesAdapter.submitList(it.meals)
        })

        viewModelCategories.setNavigation.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    DetailCategoriesDirections.actionDetailCategoriesToDetailBottomFragment(it)
                findNavController().navigate(action)
            }
            viewModelCategories.navigationDisplay()
        })
    }

    override fun getDataBinding() = FragmentDetailCategoriesBinding.inflate(layoutInflater)
}