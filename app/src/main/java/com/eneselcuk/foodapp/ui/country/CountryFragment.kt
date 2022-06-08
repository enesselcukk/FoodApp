package com.eneselcuk.foodapp.ui.country


import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.eneselcuk.foodapp.databinding.FragmentCountryBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import com.eneselcuk.foodapp.ui.country.adapter.CountryDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CountryFragment : BaseFragment<FragmentCountryBinding>() {


    private val viewModelCountry: ViewModelCountry by activityViewModels()
    private val args: CountryFragmentArgs by navArgs()
    private lateinit var adapterCountry: CountryDetailAdapter


    override fun setObserver() {
        adapterCountry = CountryDetailAdapter(CountryDetailAdapter.DetailClick {
            viewModelCountry.setDetailNavigation(it)
        })

        viewModelCountry.getDetailCountry(args.countryDetail.strArea)
       binding?.recyclerViewDetail?.layoutManager =
            GridLayoutManager(requireContext(), 3)
       binding?.recyclerViewDetail?.adapter = adapterCountry

        viewModelCountry.country.observe(viewLifecycleOwner, {
            adapterCountry.submitList(it.meals)
        })
    }

    override fun setClick() {
        viewModelCountry.navigation.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    CountryFragmentDirections.actionCountryFragmentToDetailCountryBottomSheetFragment(
                        it)
                findNavController().navigate(action)
            }
            viewModelCountry.disPlay()
        })
    }

    override fun getDataBinding() = FragmentCountryBinding.inflate(layoutInflater)
}