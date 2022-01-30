package com.rcyn.made.movie_app.ui.components.tv

import android.content.Context
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.rcyn.made.movie_app.MyApplication
import com.rcyn.made.movie_app.R
import com.rcyn.made.movie_app.databinding.FragmentTvBinding
import com.rcyn.made.movie_app.ui.ViewModelFactory
import com.rcyn.made.core.data.Resource
import com.rcyn.made.core.domain.model.MovieTv
import com.rcyn.made.core.ui.base.BaseFragment
import com.rcyn.made.core.utils.ext.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import javax.inject.Inject

class TvFragment : BaseFragment<FragmentTvBinding>({ FragmentTvBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: TvViewModel by viewModels { factory }
    private val tvAdapter by lazy { TvAdapter() }

    override fun FragmentTvBinding.onViewCreated(savedInstanceState: Bundle?) {
        binding?.apply {
            rvTv.adapter = tvAdapter
            rvTv.hasFixedSize()
            rvTv.addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    appbar.isSelected = recyclerView.canScrollVertically(-1)
                }
            })
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    lifecycleScope.launch {
                        viewModel.queryChannel.send(p0.toString())
                    }
                    return true
                }
            })
        }
        tvAdapter.listener = { _, _, item ->
            findNavController().navigate(
                TvFragmentDirections.actionTvFragmentToDetailActivity(item)
            )
        }
        tvAdapter.shareListener = { requireActivity().shareMovieTv(it) }
    }

    override fun observeViewModel() {
        observe(viewModel.tvShows, ::handleTvShows)
        observe(viewModel.search) { searchResult ->
            observe(searchResult?.asLiveData(), ::handleSearch)
        }
    }

    private fun handleTvShows(tvShows: Resource<List<MovieTv>>) {
        binding?.apply {
            when (tvShows) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    tvAdapter.submitList(tvShows.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (tvShows.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            tvShows.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        tvAdapter.submitList(tvShows.data)
                    }
                }
            }
        }
    }

    private fun handleSearch(movies: Resource<List<MovieTv>>) {
        binding?.apply {
            when (movies) {
                is Resource.Loading -> {
                    errorLayout.gone()
                    loading.root.visible()
                }
                is Resource.Success -> {
                    loading.root.gone()
                    errorLayout.gone()
                    tvAdapter.submitList(movies.data)
                }
                is Resource.Error -> {
                    loading.root.gone()
                    if (movies.data.isNullOrEmpty()) {
                        errorLayout.visible()
                        error.message.text =
                            movies.message ?: getString(R.string.default_error_message)
                    } else {
                        requireContext().showToast(getString(R.string.default_error_message))
                        tvAdapter.submitList(movies.data)
                    }
                }
            }
        }
    }

    @ExperimentalCoroutinesApi
    override fun onAttach(context: Context) {
        super.onAttach(context)
        (requireActivity().application as MyApplication).appComponent.inject(this)
    }

    override fun onDestroyView() {
        binding?.rvTv?.adapter = null
        super.onDestroyView()
    }
}