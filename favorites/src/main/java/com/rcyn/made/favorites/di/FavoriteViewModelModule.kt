package com.rcyn.made.favorites.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import com.rcyn.made.movie_app.di.ViewModelKey
import com.rcyn.made.movie_app.ui.ViewModelFactory
import com.rcyn.made.favorites.ui.FavoriteViewModel

@Suppress("unused")
@Module
abstract class FavoriteViewModelModule {

    @Binds
    abstract fun bindFavoriteViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(FavoriteViewModel::class)
    abstract fun bindFavoriteViewModel(viewModel: FavoriteViewModel): ViewModel
}