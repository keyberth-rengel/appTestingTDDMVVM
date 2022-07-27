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
import com.every.apptestingtddmvvm.application.ApplicationDataBase
import com.every.apptestingtddmvvm.core.Resource
import com.every.apptestingtddmvvm.core.SharedPrefe
import com.every.apptestingtddmvvm.data.local.DataBase.Entities.PostEntity
import com.every.apptestingtddmvvm.data.local.PostsDataSourceLocal
import com.every.apptestingtddmvvm.data.remote.PostsDataSourceRemote
import com.every.apptestingtddmvvm.databinding.FragmentHomeBinding
import com.every.apptestingtddmvvm.domain.PostsUseCase
import com.every.apptestingtddmvvm.presentation.PostViewModel
import com.every.apptestingtddmvvm.presentation.PostViewModelFactory
import com.every.apptestingtddmvvm.ui.adapter.TableRowAdapter
import com.every.apptestingtddmvvm.ui.listener.OnClickListener

class HomeFragment : Fragment(R.layout.fragment_home), OnClickListener {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mNavController: NavController
    private lateinit var mSharedPrefe: SharedPrefe

    //adapter
    private lateinit var mAdapter: TableRowAdapter
    private lateinit var mLinearLayoutManager: RecyclerView.LayoutManager

    //viewModel
    private val mFeedPostViewModel by viewModels<PostViewModel> {
        PostViewModelFactory(
            PostsUseCase(PostsDataSourceRemote(), PostsDataSourceLocal())
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding = FragmentHomeBinding.bind(view)
        mNavController = Navigation.findNavController(view)
        mSharedPrefe = ApplicationDataBase.appPrefs
        getPosts()

        mBinding.signOff.setOnClickListener {
            signOff()
        }

        mBinding.favorites.setOnClickListener {
            navigationToFavorite()
        }

    }

    private fun signOff() {
        mSharedPrefe.wipe()
        navigationToGoBack()

    }

    private fun getPosts() {
        mFeedPostViewModel.getFeedPosts().observe(viewLifecycleOwner) { result ->
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
                    configAdapterTableRow(result.data)
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
        val action = HomeFragmentDirections.actionHomeToDetails(postDetail)
        mNavController.navigate(action)
    }

    private fun navigationToFavorite() {
        mNavController.navigate(R.id.action_home_to_favorites)
    }

    private fun navigationToGoBack() {
        mNavController.navigate(R.id.action_home_to_login)
    }

    override fun onClickListener(post: PostEntity) {
        navigationToDetail(post)
    }

}