package com.eneselcuk.foodapp.ui.detail.webDetailFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.navArgs
import com.eneselcuk.foodapp.R
import com.eneselcuk.foodapp.databinding.FragmentDetailBinding
import com.eneselcuk.foodapp.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebDetailFragment : BaseFragment<FragmentDetailBinding>() {


    private val webViewArgs: WebDetailFragmentArgs by navArgs()

    override fun setData() {
        val food: String = webViewArgs.webView

        if (food.equals(null)) {
            Toast.makeText(requireContext(), "OOPS", Toast.LENGTH_SHORT).show()
        } else {
            binding?.foodWebView.apply {
                this?.let {
                    webViewClient = WebViewClient()
                    loadUrl(food)
                }
            }
        }
    }

    override fun getDataBinding() = FragmentDetailBinding.inflate(layoutInflater)
}
