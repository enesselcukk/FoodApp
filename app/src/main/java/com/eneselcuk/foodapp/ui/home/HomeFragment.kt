package com.eneselcuk.foodapp.ui.home


import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.eneselcuk.foodapp.databinding.FragmentHomeBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import com.eneselcuk.foodapp.ui.home.adapter.categories.CategoriesAdapter
import com.eneselcuk.foodapp.ui.home.adapter.country.CountryAdapter
import com.eneselcuk.foodapp.ui.home.adapter.recommended.RecommendedAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private val homeViewModel: HomeViewModel by activityViewModels()
    private lateinit var categoriesAdapter: CategoriesAdapter
    private lateinit var recommendedAdapter: RecommendedAdapter
    private lateinit var countryAdapter: CountryAdapter

    override fun definition() {
        getDataBinding().searchOnClickListener = this@HomeFragment

        categoriesAdapter = CategoriesAdapter(CategoriesAdapter.Click {
            homeViewModel.setCategories(it)
        })

        recommendedAdapter = RecommendedAdapter(RecommendedAdapter.OnClick {
            homeViewModel.setNavigation(it)
        })

        countryAdapter = CountryAdapter(CountryAdapter.Click {
            homeViewModel.setNavigation(it)
        })

        binding?.apply {

            // country adapter
            lifecycleOwner = viewLifecycleOwner

            recyclerCountry.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerCountry.adapter = countryAdapter

            // categories adapter
            recyclerViewCategories.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerViewCategories.adapter = categoriesAdapter

            // recommended adapter
            recyclerViewRecommended.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            recyclerViewRecommended.adapter = recommendedAdapter

            setHasOptionsMenu(true)
        }
    }

    override fun setObserver() {
        homeViewModel.foodCategories.observe(viewLifecycleOwner, { listCategories ->
            categoriesAdapter.submitList(listCategories.categories)
        })

        homeViewModel.foodRandom.observe(viewLifecycleOwner, {
            it?.let {
                recommendedAdapter.submitList(it.meals)
            }
        })

        homeViewModel.navigation.observe(viewLifecycleOwner, {
            it?.let {
                homeViewModel.getId(it.idMeal)
            }
        })

        homeViewModel.foodId.observe(viewLifecycleOwner, {
            it?.let {
                val action =
                    HomeFragmentDirections.actionNavigationHomeToDetailBottomFragment(it)
                view?.findNavController()?.navigate(action)
            }
            homeViewModel.displayComplated()
        })

        homeViewModel.navigationCategories.observe(viewLifecycleOwner, {
            it?.let {
                val action = HomeFragmentDirections.actionNavigationHomeToDetailCategories(it)
                findNavController().navigate(action)
            }
            homeViewModel.setCategoriesDisplay()
        })

        homeViewModel.countryList.observe(viewLifecycleOwner, {
            countryAdapter.submitList(it.meals)
        })

        homeViewModel.countryNavigation.observe(viewLifecycleOwner, {
            it?.let {
                val action = HomeFragmentDirections.actionNavigationHomeToCountryFragment(it)
                findNavController().navigate(action)
            }
            homeViewModel.setCountryDisplay()
        })
    }

    override fun getDataBinding() = FragmentHomeBinding.inflate(layoutInflater)

}
