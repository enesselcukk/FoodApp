package com.eneselcuk.foodapp.ui.search

import androidx.appcompat.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneselcuk.foodapp.databinding.FragmentSearchBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import com.eneselcuk.foodapp.ui.search.search.SearchAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {


    private lateinit var searchAdapter: SearchAdapter
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun setObserver() {
        searchAdapter = SearchAdapter()
       binding?.apply {
            searchView.isActivated = true
            recyclerViewSearch.layoutManager = LinearLayoutManager(requireContext())
            recyclerViewSearch.adapter = searchAdapter
        }
    }

    override fun search() {
       binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.isNotEmpty() == true) {
                    getObserver(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.isNotEmpty() == true) {
                    getObserver(newText)
                }
                return true
            }
        })
    }


    private fun getObserver(nameSearch: String) {
        searchViewModel.getSearchData("%$nameSearch%").observe(viewLifecycleOwner, {
            searchAdapter.submitList(it)
        })
    }

    override fun getDataBinding() = FragmentSearchBinding.inflate(layoutInflater)

}