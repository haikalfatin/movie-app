package com.rcyn.made.favorites.ui

import androidx.lifecycle.ViewModel
import com.rcyn.made.core.domain.usecase.MovieTvUseCase
import javax.inject.Inject

class FavoriteViewModel @Inject constructor(useCase: MovieTvUseCase?) : ViewModel() {
    val favoriteMovies = useCase?.getFavoriteMovies()
    val favoriteTvShows = useCase?.getFavoriteTvShows()
}