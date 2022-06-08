package com.eneselcuk.foodapp.ui.detail.detailBottomSheet


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.data.local.FoodEntity
import com.eneselcuk.foodapp.data.remote.model.foodId.Meal
import com.eneselcuk.foodapp.databinding.FragmentDetailBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailBottomSheetFragment : BottomSheetDialogFragment() {
    private val dataArgs: DetailBottomSheetFragmentArgs by navArgs()
    private lateinit var binding: FragmentDetailBottomSheetBinding
    private val viewModelDetailBottomSheet: ViewModelDetailBottom by activityViewModels()
    private var situation = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater,
                R.layout.fragment_detail_bottom_sheet,
                container,
                false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelDetailBottomSheet.getAllFood()

        binding.apply {
            detailClick = this@DetailBottomSheetFragment

        }
        binding.foodText = dataArgs.searchId.meals[0]
        setArgs()
        saveFoodClick()
        getObserver()
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    private fun setArgs() {
        dataArgs.searchId.meals.forEach { mealData ->
            viewModelDetailBottomSheet.addRemoteData(mealData)
            setRemote(mealData)
        }
    }

    private fun saveFoodClick() {
        binding.imageAddFavorite.setOnClickListener {
            dataArgs.searchId.meals.forEach { meal ->
                situation = if (situation) {
                    viewModelDetailBottomSheet.addFood(situation)
                    binding.imageAddFavorite.setImageResource(R.drawable.ic_baseline_saved)
                    false
                } else {
                    viewModelDetailBottomSheet.deleteFood(meal.idMeal?.toInt())
                    binding.imageAddFavorite.setImageResource(R.drawable.ic_baselina_save)
                    true
                }
            }
        }
    }

    private fun getObserver() {
        viewModelDetailBottomSheet.getFood.observe(viewLifecycleOwner, {
            it.forEach { foodEntity ->
                dataArgs.searchId.meals.forEach { meal ->
                    meal.strMeal?.let { id ->
                        if (foodEntity.liked == true) {
                            if (foodEntity.food_name.equals(id)) {
                                binding.imageAddFavorite.setImageResource(R.drawable.ic_baseline_saved)
                                setDatabase(foodEntity)
                            }
                        } else {
                            setRemote(meal)
                            binding.imageAddFavorite.setImageResource(R.drawable.ic_baselina_save)
                        }
                    }
                }
            }
        })
    }

    fun detailOnCLick() {
        dataArgs.searchId.meals.forEach {
            if (it.strSource?.isNotEmpty() == true) {
                val action =
                    DetailBottomSheetFragmentDirections.actionDetailBottomFragmentToDetailFragment(
                        it.strSource)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "not url", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setRemote(meal: Meal) {
        binding.textBottomName.text = meal.strMeal
        Glide.with(requireActivity())
            .load(meal.strMealThumb)
            .fitCenter()
            .into(binding.imageDetailFood)
    }

    private fun setDatabase(entity: FoodEntity) {
        binding.textBottomName.text = entity.food_name
        Glide.with(requireActivity())
            .load(entity.image)
            .fitCenter()
            .into(binding.imageDetailFood)
    }

    fun videOnCLick() {
        dataArgs.searchId.meals.forEach {
            it.strYoutube?.let { source ->

                if (source.isNotEmpty()) {
                    val action =
                        DetailBottomSheetFragmentDirections
                            .actionDetailBottomFragmentToVideoExoplayerFragment(source)
                    findNavController().navigate(action)
                } else {
                    Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        viewModelDetailBottomSheet.getAllFood()
    }
}
