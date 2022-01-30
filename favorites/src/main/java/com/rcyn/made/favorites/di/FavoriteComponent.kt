package com.rcyn.made.favorites.di

import dagger.Component
import com.rcyn.made.movie_app.di.AppModule
import com.rcyn.made.core.di.CoreComponent
import com.rcyn.made.favorites.ui.FavoriteFragment
import com.rcyn.made.favorites.ui.FavoriteMovieFragment
import com.rcyn.made.favorites.ui.FavoriteTvFragment

@FavoriteAppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, FavoriteViewModelModule::class]
)
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)
    fun inject(fragment: FavoriteMovieFragment)
    fun inject(fragment: FavoriteTvFragment)
}