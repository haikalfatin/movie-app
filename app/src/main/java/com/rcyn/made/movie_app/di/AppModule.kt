package com.rcyn.made.movie_app.di

import dagger.Binds
import dagger.Module
import com.rcyn.made.core.domain.usecase.MovieTvInteractor
import com.rcyn.made.core.domain.usecase.MovieTvUseCase

@Module
abstract class AppModule {

    @Suppress("unused")
    @Binds
    abstract fun provideMovieUseCase(movieInteractor: MovieTvInteractor): MovieTvUseCase
}