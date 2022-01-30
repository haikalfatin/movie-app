package com.rcyn.made.core.domain.usecase

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.rcyn.made.core.data.Resource
import com.rcyn.made.core.domain.model.MovieTv
import com.rcyn.made.core.domain.repository.IMovieTvRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieTvInteractor @Inject constructor(private val tourismRepository: IMovieTvRepository):
    MovieTvUseCase {

    override fun getMovies(): Flow<Resource<List<MovieTv>>> = tourismRepository.getMovies()

    override fun getTvShows(): Flow<Resource<List<MovieTv>>> = tourismRepository.getTvShows()

    override fun getFavoriteMovies(): LiveData<PagedList<MovieTv>> =
        tourismRepository.getFavoriteMovies()

    override fun getFavoriteTvShows(): LiveData<PagedList<MovieTv>> =
        tourismRepository.getFavoriteTvShows()

    override fun searchMovies(query: String): Flow<Resource<List<MovieTv>>> =
        tourismRepository.searchMovies(query)

    override fun searchTvShows(query: String): Flow<Resource<List<MovieTv>>> =
        tourismRepository.searchTvShows(query)

    override fun setFavoriteMovieTv(movieTv: MovieTv, saved: Boolean) =
        tourismRepository.setFavoriteMovieTv(movieTv, saved)

    override fun isFavorite(movieTv: MovieTv) = tourismRepository.isFavorite(movieTv)
}