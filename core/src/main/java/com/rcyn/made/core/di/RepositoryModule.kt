package com.rcyn.made.core.di

import dagger.Binds
import dagger.Module
import com.rcyn.made.core.data.source.TMDBRepository
import com.rcyn.made.core.domain.repository.IMovieTvRepository

@Module(includes = [NetworkModule::class, DatabaseModule::class])
abstract class RepositoryModule {

  @Suppress("unused")
  @Binds
  abstract fun provideRepository(tmdbRepository: TMDBRepository): IMovieTvRepository
}