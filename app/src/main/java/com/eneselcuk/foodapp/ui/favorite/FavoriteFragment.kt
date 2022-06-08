package com.eneselcuk.foodapp.ui.favorite


import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.eneselcuk.foodapp.databinding.FragmentFavoriBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import com.eneselcuk.foodapp.ui.favorite.adapter.favorite.FavoriteAdapter
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriBinding>() {

    private val favoriteViewModel: FavoriteViewModel by activityViewModels()
    private lateinit var favoriteAdapter: FavoriteAdapter

    override fun setObserver() {

        favoriteAdapter = FavoriteAdapter(FavoriteAdapter.FoodOnClick {
            favoriteViewModel.setNavigation(it)
        })

       binding?.recyclerViewFavorite.apply {
           this?.let {
               layoutManager = LinearLayoutManager(requireContext())
               adapter = favoriteAdapter
           }
        }

       binding?.onClick = this
        favoriteViewModel.getLiveDatabase.observe(viewLifecycleOwner, {
            favoriteAdapter.submitList(it)
        })

        favoriteViewModel.favoriteNavigation.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    FavoriteFragmentDirections
                        .actionNavigationFavoriToFavoriteDetailBottomSheetFragment(it)
                findNavController().navigate(action)
            }
            favoriteViewModel.playDish()
        })

    }

    override fun swipe() {
        val swipe = object :
            ItemTouchHelper.SimpleCallback(
                0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder,
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.bindingAdapterPosition
                val noteData = favoriteAdapter.currentList[position]
                noteData?.id.let {
                    it?.let { it1 ->
                        favoriteViewModel.deleteFoodID(it1)
                        favoriteViewModel.getAllDatabase()
                    }
                }
                view?.let {
                    Snackbar.make(it, "deleted", Snackbar.LENGTH_SHORT).show()
                }
            }
        }
        ItemTouchHelper(swipe).apply {
            attachToRecyclerView(binding?.recyclerViewFavorite)
        }
    }

    fun searchOnClick() {
        val action =
            FavoriteFragmentDirections.actionNavigationFavoriToSearchFragment()
        view?.findNavController()?.navigate(action)
    }

    override fun onStart() {
        super.onStart()
        favoriteViewModel.getAllDatabase()
    }

    override fun getDataBinding() = FragmentFavoriBinding.inflate(layoutInflater)
}