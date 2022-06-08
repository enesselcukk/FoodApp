package com.eneselcuk.foodapp.ui.detailCategories.detailCategoriesBottomSheet

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
import com.eneselcuk.foodapp.databinding.FragmentDetailCategoriesBattomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailCategoriesBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: FragmentDetailCategoriesBattomSheetBinding
    private val args: DetailCategoriesBottomSheetFragmentArgs by navArgs()
    private val viewModelDetailBottom: ViewModelDetailCatBottomSheet by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        bottomSheetBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_detail_categories_battom_sheet,
            container,
            false)
        return bottomSheetBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        define()
        setObserver()
    }

    private fun define() {

        bottomSheetBinding.click = this@DetailCategoriesBottomSheetFragment

        val args = args.categoriesBottom
        args.idMeal?.let {
            viewModelDetailBottom.getFood(it)

        }
        viewModelDetailBottom.setCategories(args)

        viewModelDetailBottom.getFood.observe(viewLifecycleOwner, {
            bottomSheetBinding.foodText = it.meals[0]
        })
    }

    fun categoriesClick() {
        viewModelDetailBottom.getFood.observe(viewLifecycleOwner, {


            it?.meals?.forEach { meal ->
                if (meal.strSource?.isNotEmpty() == true) {
                    val action =
                        DetailCategoriesBottomSheetFragmentDirections.actionDetailCategoriesBottomSheetFragmentToDetailFragment(
                            meal.strSource)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setObserver() {
        viewModelDetailBottom.bottomSheet.observe(viewLifecycleOwner, {
            bottomSheetBinding.categories = it
        })
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    fun videOnCLick() {
        viewModelDetailBottom.getFood.observe(viewLifecycleOwner, {
            it.meals.forEach { meal ->
                meal.strYoutube?.let { source ->

                    if (source.isNotEmpty()) {
                        val action =
                            DetailCategoriesBottomSheetFragmentDirections
                                .actionDetailCategoriesBottomSheetFragmentToVideoExoplayerFragment(
                                    source)
                        findNavController().navigate(action)
                    } else {
                        Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }
}