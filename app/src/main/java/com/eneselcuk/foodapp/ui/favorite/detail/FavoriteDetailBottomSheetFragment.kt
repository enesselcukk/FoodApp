package com.eneselcuk.foodapp.ui.favorite.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.databinding.FragmentFavoriteDetailBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FavoriteDetailBottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentFavoriteDetailBottomSheetBinding
    private val args: FavoriteDetailBottomSheetFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_favorite_detail_bottom_sheet,
            container,
            false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        binding.foodFavoriteSet = args.foodEntity
        binding.foodOnClick = this
        binding.videoOnClick = this
    }

    override fun getTheme(): Int {
        return R.style.CustomBottomSheetDialog
    }

    fun foodClick() {
        val foodSource: String? = args.foodEntity.details
        foodSource?.let {
            val action =
                FavoriteDetailBottomSheetFragmentDirections
                    .actionFavoriteDetailBottomSheetFragmentToDetailFragment(foodSource)
            findNavController().navigate(action)
        }
    }

    fun videoClick() {
        val videoSource: String? = args.foodEntity.youtube
        videoSource?.let {
            if (it.isNotEmpty()) {
                val action =
                    FavoriteDetailBottomSheetFragmentDirections
                        .actionFavoriteDetailBottomSheetFragmentToVideoExoplayerFragment(videoSource)
                findNavController().navigate(action)
            } else {
                Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
            }
        }
    }
}