package com.eneselcuk.foodapp.ui.country.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.databinding.FragmentDetailBottomCountryBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailCountryBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: FragmentDetailBottomCountryBinding

    private val args: DetailCountryBottomSheetFragmentArgs by navArgs()
    private val viewModelDetailBottom: ViewModelBottomSheet by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bottomSheetBinding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_detail_bottom_country,
                container,
                false)
        return bottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetBinding.detailClick = this
        setObserver()
    }

    private fun setObserver() {
        val id = args.detailCountry.idMeal
        id?.let {
            viewModelDetailBottom.getDetailId(id)
        }
        viewModelDetailBottom.countryId.observe(viewLifecycleOwner, {
            it.meals.forEach { meal ->
                bottomSheetBinding.country = meal
            }
        })
    }

    fun click() {
        viewModelDetailBottom.countryId.observe(viewLifecycleOwner, {
            it?.meals?.forEach { meail ->
                if (meail.strSource?.isNotEmpty() == true) {
                    val action =
                        DetailCountryBottomSheetFragmentDirections.actionDetailCountryBottomSheetFragmentToDetailFragment(
                            meail.strSource)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "not url", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    fun videOnCLick() {
        viewModelDetailBottom.countryId.observe(viewLifecycleOwner, {
            it.meals.forEach { meal ->
                meal.strSource?.let { source ->
                    if (source.isNotEmpty()) {
                        val action =
                            DetailCountryBottomSheetFragmentDirections
                                .actionDetailCountryBottomSheetFragmentToVideoExoplayerFragment(
                                    source)
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }
}

