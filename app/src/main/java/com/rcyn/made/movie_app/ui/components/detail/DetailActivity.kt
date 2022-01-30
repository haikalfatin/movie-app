package com.rcyn.made.movie_app.ui.components.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import com.rcyn.made.movie_app.MyApplication
import com.rcyn.made.movie_app.R
import com.rcyn.made.movie_app.databinding.ActivityDetailBinding
import com.rcyn.made.movie_app.ui.ViewModelFactory
import com.rcyn.made.core.R.drawable
import com.rcyn.made.core.ui.base.BaseActivity
import com.rcyn.made.core.utils.ext.observe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

class DetailActivity : BaseActivity<ActivityDetailBinding>({ ActivityDetailBinding.inflate(it) }) {

    @Inject
    lateinit var factory: ViewModelFactory

    private val viewModel: DetailViewModel by viewModels { factory }
    private val args: DetailActivityArgs by navArgs()

    @ExperimentalCoroutinesApi
    override fun ActivityDetailBinding.onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this@DetailActivity)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setDisplayShowTitleEnabled(false)
        }

        val movieTv = args.item
        movieTv.let {
            viewModel.setSelectedItem(it)
        }
    }

    override fun observeViewModel() {
        observe(viewModel.movieTvItem) { binding.item = it }
        observe(viewModel.isFavorite, ::setFavoriteState)
    }

    private fun setFavoriteState(isFavoriteBefore: Boolean) {
        binding.apply {
            fabFav.setOnClickListener {
                viewModel.setToFavorite(isFavoriteBefore)

                Snackbar.make(
                    coordinatorLayout,
                    getString(if (isFavoriteBefore) R.string.deleted_remove_favorite else R.string.added_to_favorite),
                    Snackbar.LENGTH_LONG
                )
                    .setBackgroundTint(
                        ContextCompat.getColor(
                            this@DetailActivity,
                            if (isFavoriteBefore) R.color.red else R.color.green
                        )
                    )
                    .show()
            }

            fabFav.setImageDrawable(
                ContextCompat.getDrawable(
                    this@DetailActivity,
                    if (isFavoriteBefore) drawable.ic_favorite_filled else drawable.ic_favorite_border
                )
            )
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}