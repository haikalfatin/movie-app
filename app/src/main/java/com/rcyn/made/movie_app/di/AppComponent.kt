package com.rcyn.made.movie_app.di

import dagger.Component
import com.rcyn.made.movie_app.ui.components.MainActivity
import com.rcyn.made.movie_app.ui.components.detail.DetailActivity
import com.rcyn.made.movie_app.ui.components.movie.MovieFragment
import com.rcyn.made.movie_app.ui.components.tv.TvFragment
import com.rcyn.made.core.di.CoreComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AppScope
@Component(
    dependencies = [CoreComponent::class],
    modules = [AppModule::class, ViewModelModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(coreComponent: CoreComponent): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(activity: DetailActivity)
    fun inject(fragment: MovieFragment)
    fun inject(fragment: TvFragment)
}