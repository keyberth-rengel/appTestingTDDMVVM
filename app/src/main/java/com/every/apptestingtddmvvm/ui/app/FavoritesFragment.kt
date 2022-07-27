package com.every.apptestingtddmvvm.ui.app

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.every.apptestingtddmvvm.R
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.data.local.FavoritePostsDataSourceLocal
import com.every.apptestingtddmvvm.databinding.FragmentFavoritesBinding
import com.every.apptestingtddmvvm.domain.FavoritePostsUseCase
import com.every.apptestingtddmvvm.presentation.FavoritePostViewModel
import com.every.apptestingtddmvvm.presentation.FavoritePostViewModelFactory
import com.every.apptestingtddmvvm.ui.adapter.TableRowAdapter
import com.every.apptestingtddmvvm.ui.listener.OnClickListener

class FavoritesFragment : Fragment(R.layout.fragment_favorites), OnClickListener {

    private lateinit var mBinding: FragmentFavoritesBinding
    private lateinit var mNavController: NavController

    //adapter
    private lateinit var mAdapter: TableRowAdapter
    private lateinit var mLinearLayoutManager: RecyclerView.LayoutManager

    //viewModel
    private val mFavoritePostViewModel by viewModels<FavoritePostViewModel> {
        FavoritePostViewModelFactory(
            FavoritePostsUseCase(FavoritePostsDataSourceLocal())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mBinding = FragmentFavoritesBinding.bind(view)

        mNavController = Navigation.findNavController(view)
        getPosts()
        mBinding.goBack.setOnClickListener {
            navigationToGoBack()
        }
    }

    private fun getPosts() {
        mFavoritePostViewModel.getFavoritePosts().observe(viewLifecycleOwner) { result ->
            when (result) {
                is Resource.Failure -> {
                    mBinding.indicator.loading.visibility = View.GONE
                    createToast("Error get posts")

                }
                is Resource.Loading -> {
                    mBinding.indicator.loading.visibility = View.VISIBLE

                }
                is Resource.Success -> {
                    mBinding.indicator.loading.visibility = View.GONE
                    val convert: List<PostEntity> = result.data.map {
                        return@map PostEntity(
                            adult = it.adult,
                            backdropPath = it.backdropPath,
                            id = it.id,
                            originalTitle = it.originalTitle,
                            overview = it.overview,
                            posterPath = it.posterPath,
                            releaseDate = it.releaseDate,
                            voteAverage = it.voteAverage,
                        )
                    }
                    configAdapterTableRow(convert)
                }
            }
        }

    }

    private fun configAdapterTableRow(
        listItems: List<PostEntity>
    ) {
        if (listItems.isNotEmpty()) {
            mAdapter = TableRowAdapter(listItems, this)
            mLinearLayoutManager = LinearLayoutManager(requireContext())

            mBinding.includeTable.listUser.apply {
                adapter = mAdapter
                layoutManager = mLinearLayoutManager
            }
        }
    }

    private fun createToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT)
            .show()
    }

    private fun navigationToDetail(postDetail: PostEntity) {
        val action = FavoritesFragmentDirections.actionFavoritesToDetails(postDetail)
        mNavController.navigate(action)
    }

    private fun navigationToGoBack() {
        requireActivity().onBackPressedDispatcher.onBackPressed()
    }

    override fun onClickListener(post: PostEntity) {
        navigationToDetail(post)
    }
}