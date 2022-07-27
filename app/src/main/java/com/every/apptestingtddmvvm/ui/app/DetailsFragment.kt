package com.every.apptestingtddmvvm.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import coil.load
import com.every.apptestingtddmvvm.R
import com.every.apptestingtddmvvm.application.AppConstants
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.FavoriteEntity
import com.every.apptestingtddmvvm.data.local.FavoritePostsDataSourceLocal
import com.every.apptestingtddmvvm.databinding.FragmentDetailsBinding
import com.every.apptestingtddmvvm.domain.FavoritePostsUseCase
import com.every.apptestingtddmvvm.presentation.FavoritePostViewModel
import com.every.apptestingtddmvvm.presentation.FavoritePostViewModelFactory

class DetailsFragment : Fragment(R.layout.fragment_details) {

    private lateinit var mBinding: FragmentDetailsBinding
    private lateinit var mNavController: NavController

    //args
    private val args by navArgs<DetailsFragmentArgs>()

    //viewModel
    private val mFavoritePostViewModel by viewModels<FavoritePostViewModel> {
        FavoritePostViewModelFactory(
            FavoritePostsUseCase(FavoritePostsDataSourceLocal())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentDetailsBinding.bind(view)
        mNavController = Navigation.findNavController(view)
        loadValue()

        mBinding.favorites.setOnClickListener {
            navigationToFavorite()
        }

        mBinding.containerDetails.addFavorites.setOnClickListener {
            addFavorite()
        }
        mBinding.goBack.setOnClickListener {
            navigationToGoBack()
        }
    }

    private fun loadValue() {
        args.detail?.let {
            mBinding.containerDetails.picture.load("${AppConstants.BASE_IMAGE_URL}${it.posterPath}") {
                crossfade(true)
                placeholder(R.drawable.ic_hide_image)
            }
            mBinding.containerDetails.title.text = it.originalTitle
            mBinding.containerDetails.voteAverage.text = it.voteAverage.toString()
            mBinding.containerDetails.description.text = it.overview
        }
    }

    private fun addFavorite() {
        args.detail?.let {

            val post = FavoriteEntity(
                adult = it.adult,
                backdropPath = it.backdropPath,
                id = it.id,
                originalTitle = it.originalTitle,
                overview = it.overview,
                posterPath = it.posterPath,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
            )

            mFavoritePostViewModel.insertFavoritePost(post).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is Resource.Failure -> {
                        createToast("Error add posts to favorite")
                    }
                    is Resource.Loading -> {
                    }
                    is Resource.Success -> {
                        createToast("Added post to favorite")
                    }
                }
            }
        }
    }

    private fun navigationToFavorite() {
        mNavController.navigate(R.id.action_details_to_favorites)
    }

    private fun createToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun navigationToGoBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

}